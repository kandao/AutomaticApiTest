package com.kandao.apiapplication.resource;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
@RequestScoped
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg() {
        return Response.ok("{\"var\":\"Hello!\"}").build();
    }
}

