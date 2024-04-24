package io.github.eduardobatista.rest.resource;

import io.github.eduardobatista.domain.entity.RecipeBook;
import io.github.eduardobatista.rest.service.RecipeBookService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/recipebooks")
public class RecipeBookResource extends BaseResource<RecipeBook, RecipeBookService> {

    public RecipeBookResource() {
        super(new RecipeBookService());
    }

    @GET
    @Override
    @Path("/{id}")
    public Response load(Long id) {
        return Response.ok(service.load(id)).build();
    }

    @GET
    @Override
    @Path("/all")
    public Response loadAll() {
        return Response.ok(service.loadAll()).build();
    }

    @Override
    public Response loadAllBy(@PathParam("userId") Long userId) {
        return Response.ok(service.loadAllBy(userId)).build();
    }

    @Override
    public Response save(Long userId, RecipeBook object) {
        return Response.ok(service.save(userId, object)).build();
    }

    @Override
    public Response update(Long userId, Long id, RecipeBook object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Response delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
