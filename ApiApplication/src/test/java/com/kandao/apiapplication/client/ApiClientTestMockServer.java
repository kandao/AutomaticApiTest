package com.kandao.apiapplication.client;

import com.kandao.apiapplication.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@RunWith(MockitoJUnitRunner.class)
public class ApiClientTestMockServer {

    private static final String PATH = "http://localhost:1080/get_json";
    private static final int TIMEOUT_MILLISECONDS = 2000;
    //    private ClientAndProxy proxy;
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);
    private MockServerClient mockServerClient;
    @InjectMocks
    private ApiClient apiClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockServerClient = startClientAndServer(1080);
    }

    @Test
    public void testGetApi() {
        mockServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/get_json")
                )
                .respond(
                        response()
                                .withHeaders(
                                        new Header(CONTENT_TYPE.toString(), MediaType.APPLICATION_JSON)
                                )
                                .withBody(getBody().toString())
                );
        Response response = apiClient.CallGetAPI(PATH);
        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(String.class), is(getBody().toString()));
    }

    private User getBody() {
        User user = new User();
        user.setDevice("Ios11");
        user.setUserId("1234567890");
        user.setUserRank("0");
        return user;
    }
}
