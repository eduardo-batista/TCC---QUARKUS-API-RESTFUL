package io.github.eduardobatista.rest.graph.control;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SessionFactoryProducer{

    public SessionFactory sessionFactory;

    public static final String[] PACKAGES = {
        "io.github.eduardobatista.domain.entity",
    };

    public SessionFactoryProducer() {
        this.sessionFactory = new SessionFactory(configProducer(), PACKAGES);
    }   

    public Configuration configProducer() {
        return new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "Coolinary").useNativeTypes().build();
    }
}
