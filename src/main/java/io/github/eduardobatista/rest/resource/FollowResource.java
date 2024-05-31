package io.github.eduardobatista.rest.resource;

import io.github.eduardobatista.domain.entity.relationships.Follow;
import io.github.eduardobatista.service.FollowService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/users/{userId}/follows")
public class FollowResource extends BaseResource<Follow, FollowService> {

    public FollowResource() {
        super(new FollowService());
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

    @GET
    @Override
    public Response loadAllBy(@PathParam("userId") Long id) {
        return Response.ok(service.loadAllBy(id)).build();
    }

    @PUT
    public Response save(@PathParam("userId") Long followerId, @QueryParam("followedId") Long followedId) {
        return Response.ok(service.save(followerId, followedId)).build();
    }

    @Override
    public Response save(Long userId, Follow object) {
        throw new UnsupportedOperationException("Use other method 'save'");
    }

    @Override
    public Response update(Long userId, Long id, Follow object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Response delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
