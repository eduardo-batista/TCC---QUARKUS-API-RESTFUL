package io.github.eduardobatista.domain.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Relationship.Direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@NodeEntity("recipe_book")
public class RecipeBook {
    @Id @GeneratedValue
    private Long id;
    @Property("name")
    private String name;
    @Property("image")
    private String image;
    @Property("created_at")
    private LocalDateTime createdAt;
    @Property("updated_at")
    private LocalDateTime updatedAt;
    @Property("active")
    private Boolean active;

    @Relationship(type = "BELONGS_TO", direction = Direction.INCOMING)
    private Collection<Recipe> listRecipes;
    
    @Relationship(type = "TAGGED_BY", direction = Direction.OUTGOING)
    private Collection<Tag> listTags;

    @Relationship(type = "CREATED_BY", direction = Direction.OUTGOING)
    private User owner;
}
