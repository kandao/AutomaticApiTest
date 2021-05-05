package com.kandao.apiapplication.Client;

import com.fasterxml.jackson.core.util.JacksonFeature;
import com.kandao.apiapplication.utils.LoggerUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@ApplicationScoped
public class APIClient {

    private static final int TIMEOUT_MILLISECONDS = 10000;

    public Response CallGetAPI(String path) {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT_MILLISECONDS);
        clientConfig.property(ClientProperties.READ_TIMEOUT, TIMEOUT_MILLISECONDS);
        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget webTarget = client
                .target(path);

        Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = request.get();
        if (response.getStatus() != 200) {
            LoggerUtils.getLogger().log(Level.SEVERE, "Status is " + response.getStatus());
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
                LoggerUtils.getLogger().log(Level.SEVERE, "Status is " + response.getStatus());
            }
        } catch (Exception e) {
            LoggerUtils.getLogger().log(Level.SEVERE, "Got exception");
            LoggerUtils.getLogger().log(Level.SEVERE, e.toString());
        }

        return response;
    }
}
