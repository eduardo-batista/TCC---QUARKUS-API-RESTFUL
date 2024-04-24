package io.github.eduardobatista.rest.resource;

import io.github.eduardobatista.domain.entity.Tag;
import io.github.eduardobatista.rest.service.TagService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/tags")
public class TagResource extends BaseResource<Tag, TagService> {

    public TagResource() {
        super(new TagService());
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

    @POST
    public Response save(Tag object) {
        return Response.ok(service.save(object)).build();
    }

    @Override
    public Response loadAllBy(Long userId) {
        throw new UnsupportedOperationException("Use other method 'loadAllBy'");
    }

    @Override
    public Response save(Long userId, Tag object) {
        throw new UnsupportedOperationException("Use other method 'save'");
    }

    @Override
    public Response update(Long userId, Long id, Tag object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Response delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
