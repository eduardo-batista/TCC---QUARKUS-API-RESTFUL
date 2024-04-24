package io.github.eduardobatista.domain.repository;

import java.util.Collection;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import io.github.eduardobatista.rest.database.graph.SessionFactoryProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public abstract class BaseRepository<T> {

    private SessionFactory sessionFactory;
    private Session session;

    @Inject
    public BaseRepository() {
        this.sessionFactory = new SessionFactoryProducer().sessionFactory;
    }

    public abstract T load(Long id);

    public abstract Collection<T> loadAll();

    public abstract Collection<T> loadAllBy(Long userId);

    public abstract T save(Long userId, T object);

    public abstract T update(Long id, T object);

    public abstract void delete(Long id);

    protected Session getSession() {
        if (this.session == null)
            session = sessionFactory.openSession();
        return this.session;
    }
}
