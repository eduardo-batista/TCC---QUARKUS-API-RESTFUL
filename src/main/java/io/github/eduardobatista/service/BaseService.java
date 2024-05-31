package io.github.eduardobatista.service;

import java.util.Collection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public abstract class BaseService<T, E, R> {

    R repository;

    @Inject
    public BaseService(R repository) {
        this.repository = repository;
    }

    public abstract E load(Long id);

    public abstract Collection<E> loadAll();

    public abstract Collection<E> loadAllBy(Long userId);

    public abstract E save(Long userId, T object);

    public abstract E update(Long userId, Long id, T object);

    public abstract Boolean delete(Long userId, Long id);

}
