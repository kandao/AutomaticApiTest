package com.kandao.apiapplication.client;

import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.kandao.apiapplication.model.User;
import com.kandao.apiapplication.utils.LoggerUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ApiClientTestWireMockGet {

    private static final String PATH = "http://localhost:8484/get_json";
    private static final int TIMEOUT_MILLISECONDS = 2000;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8484);

    @Mock
    private LoggerUtils logger;

    @InjectMocks
    private ApiClient apiClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetApi() {
        stubFor(get(urlPathMatching("/get_json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getBody().toString())));
        Response response = apiClient.CallGetAPI(PATH);
        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(String.class), is(getBody().toString()));
    }

    @Test
    public void testGetApiBadRequest() {
        stubFor(get(urlPathMatching("/get_json"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")));
        Response response = apiClient.CallGetAPI(PATH);
        assertThat(response.getStatus(), is(500));
        assertThat(response.readEntity(String.class), not(getBody().toString()));
    }

    @Test
    public void testGetApiTimeOut() {
        stubFor(get(urlEqualTo("/get_json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withFixedDelay(TIMEOUT_MILLISECONDS)));
        Response response;
        try {
            response = apiClient.CallGetAPI(PATH);
        } catch (Exception e) {
            verify(logger, times(1)).error("Got exception");
            verify(logger, times(1)).error(e.getMessage());
        }
    }

    @Test
    public void testGetApiConnectionResetByPeer() {
        stubFor(get(urlEqualTo("/get_json"))
                .willReturn(aResponse().withFault(Fault.CONNECTION_RESET_BY_PEER)));
        Response response;
        try {
            response = apiClient.CallGetAPI(PATH);
        } catch (Exception e) {
            verify(logger, times(1)).error("Got exception");
            verify(logger, times(1)).error(e.getMessage());
        }
    }

    private User getBody() {
        User user = new User();
        user.setDevice("Ios11");
        user.setUserId("1234567890");
        user.setUserRank("0");
        return user;
    }
}
