package controller;

import model.Data;
import repo.DataRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;

@Path("/data")
public class DataController {
    @Inject
    private DataRepository repository;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveData(Data data) {
        try {
            repository.persistAndFlush(data);
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
        return Response.ok(data.getId()).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") UUID id) {
        try {
            return Response.ok().entity(repository.findById(id)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("{timestamp}")
    public Response findByTimestamp(@PathParam("timestamp") Date timestamp) {
        try {
            return Response.ok().entity(repository.findByTime(timestamp)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }


}
