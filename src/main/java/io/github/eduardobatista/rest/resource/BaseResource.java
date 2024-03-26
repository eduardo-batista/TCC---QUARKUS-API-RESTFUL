package io.github.eduardobatista.rest.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class BaseResource<T, R> {
    R service;

    public BaseResource(R service) {
        this.service = service;
    }

    public abstract Response load(Long id);

    public abstract Response loadAll();

    public abstract Response loadAllBy(Long userId);

    public abstract Response save(Long userId, T object);

    public abstract Response update(Long userId, Long id, T object);

    public abstract Response delete(Long userId, Long id);
}
