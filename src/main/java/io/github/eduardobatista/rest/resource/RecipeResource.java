package io.github.eduardobatista.rest.resource;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.rest.service.RecipeService;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/recipes")
public class RecipeResource extends BaseResource<Recipe, RecipeService> {

    public RecipeResource() {
        super(new RecipeService());
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

    @GET
    @Override
    public Response loadAllBy(@PathParam("userId") Long userId) {
        return Response.ok(service.loadAllBy(userId)).build();
    }

    @Override
    public Response save(@PathParam("userId") Long userId, Recipe object) {
        return Response.ok(service.save(userId, object)).build();
    }
    
    @POST
    public Response save(@PathParam("userId") Long userId, JsonObject object) {
        return Response.ok(service.save(userId, Recipe.fromJson(object))).build();
    }

    @Override
    public Response update(Long userId, Long id, Recipe object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Response delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
