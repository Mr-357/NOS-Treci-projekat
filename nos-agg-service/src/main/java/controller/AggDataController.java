package controller;

import model.AggData;
import repository.AggDataRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/data")
public class AggDataController {
    @Inject
    AggDataRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok(repository.findAll()).build();
    }
}
