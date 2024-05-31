package io.github.eduardobatista.rest.resource;

import io.github.eduardobatista.domain.entity.User;
import io.github.eduardobatista.service.UserService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource extends BaseResource<User, UserService> {

    public UserResource() {
        super(new UserService());
    }

    @GET
    @Path("/{id}/feed")
    public Response loadFeed(@PathParam("id") Long id) {
        return Response.ok(service.loadFeed(id)).build();
    }

    @GET
    @Override
    @Path("/{id}")
    public Response load(@PathParam("id") Long id) {
        return Response.ok(service.load(id)).build();
    }

    @GET
    @Override
    @Path("/all")
    public Response loadAll() {
        return Response.ok(service.loadAll()).build();
    }

    @POST
    public Response save(User object) {
        return Response.ok(service.save(object)).build();
    }

    @PUT
    @Path("/{id}/likes")
    public Response likes(@PathParam("id") Long id, @QueryParam("recipeId") Long recipeId) {
        return Response.ok(service.likes(id, recipeId)).build();
    }

    @Override
    public Response loadAllBy(Long id) {
        throw new UnsupportedOperationException("Use other method 'loadAll'");
    }

    @Override
    public Response save(Long id, User object) {
        throw new UnsupportedOperationException("use other method 'save'");
    }

    @Override
    public Response update(Long userId, Long id, User object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Response delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
