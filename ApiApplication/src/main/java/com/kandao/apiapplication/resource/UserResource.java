package com.kandao.apiapplication.resource;
import com.kandao.apiapplication.model.User;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@RequestScoped
public class UserResource {

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
}

