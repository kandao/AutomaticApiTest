package com.kandao.apiapplication.resource;

import com.kandao.apiapplication.client.ApiClient;
import com.kandao.apiapplication.model.User;
import com.kandao.apiapplication.utils.JsonConverter;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
@RequestScoped
public class UserResource {

    @Inject
    private ApiClient apiClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt() {
        return Response.ok("Got it!").build();
    }

    @Path("/msg")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(User user) {
        return Response.ok(user.toString()).build();
    }

    @Path("/getRemote")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRemoteAPI() {
        return apiClient.CallGetAPI("http://localhost:8484/json_request");
    }

    @Path("/postRemote")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postRemoteAPI(User user) {
        return apiClient.CallPostAPI("http://localhost:8484/post_json", JsonConverter.encode(user));
    }
}

