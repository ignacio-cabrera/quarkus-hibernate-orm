package org.acme.hibernate.orm.adapter.rest;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.acme.hibernate.orm.application.vo.VOFruit;
import org.acme.hibernate.orm.port.FruitService;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("fruits")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FruitResource {

    @Inject
    FruitService fruitService;

    private static final Logger LOGGER = Logger.getLogger(FruitResource.class.getName());

    @GET
    public List<VOFruit> get() {
        return fruitService.getAll();
    }
//
//    @GET
//    @Path("{id}")
//    public Fruit getSingle(Integer id) {
//        Fruit entity = entityManager.find(Fruit.class, id);
//        if (entity == null) {
//            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
//        }
//        return entity;
//    }
//
    @POST
    @Transactional
    public Response create(VOFruit fruit) {
        if (fruit.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        VOFruit saved = fruitService.create(fruit);
        return Response.ok(saved).status(201).build();
    }
//
//    @PUT
//    @Path("{id}")
//    @Transactional
//    public Fruit update(Integer id, Fruit fruit) {
//        if (fruit.getName() == null) {
//            throw new WebApplicationException("Fruit Name was not set on request.", 422);
//        }
//
//        Fruit entity = entityManager.find(Fruit.class, id);
//
//        if (entity == null) {
//            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
//        }
//
//        entity.setName(fruit.getName());
//
//        return entity;
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Transactional
//    public Response delete(Integer id) {
//        Fruit entity = entityManager.getReference(Fruit.class, id);
//        if (entity == null) {
//            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
//        }
//        entityManager.remove(entity);
//        return Response.status(204).build();
//    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
