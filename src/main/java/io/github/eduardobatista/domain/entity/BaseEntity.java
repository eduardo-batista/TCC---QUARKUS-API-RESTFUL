package io.github.eduardobatista.domain.entity;

import java.time.LocalDateTime;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public abstract class BaseEntity {
    @Id @GeneratedValue()
    private Long id;
    @Property("created_at")
    private LocalDateTime createdAt;
    @Property("updated_at")
    private LocalDateTime updatedAt;
    @Property("active")
    private Boolean active;

    public BaseEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = true;
    }
}
