package com.kandao.apiapplication.client;

import com.fasterxml.jackson.core.util.JacksonFeature;
import com.kandao.apiapplication.utils.LoggerUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ApiClient {

    private static final int TIMEOUT_MILLISECONDS = 2000;

    @Inject
    private LoggerUtils loggerUtils;

    public Response CallGetAPI(String path) {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT_MILLISECONDS);
        clientConfig.property(ClientProperties.READ_TIMEOUT, TIMEOUT_MILLISECONDS);
        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget webTarget = client
                .target(path);

        Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = null;
        try {
            response = request.get();
            if (response.getStatus() != 200) {
                loggerUtils.error("Status is " + response.getStatus());
            }
        } catch (Exception e) {
            loggerUtils.error("Got exception");
            loggerUtils.error(e.getMessage());
            throw e;
        }
        return response;
    }

    public Response CallPostAPI(String path, String jsonString) {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT_MILLISECONDS);
        clientConfig.property(ClientProperties.READ_TIMEOUT, TIMEOUT_MILLISECONDS);
        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget webTarget = client
                .target(path);

        Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = null;
        try {
            response = request.post(Entity.json(jsonString));
            if (response.getStatus() != 200) {
                loggerUtils.error("Status is " + response.getStatus());
            }
        } catch (Exception e) {
            loggerUtils.error("Got exception");
            loggerUtils.error(e.getMessage());
            throw e;
        }

        return response;
    }
}
