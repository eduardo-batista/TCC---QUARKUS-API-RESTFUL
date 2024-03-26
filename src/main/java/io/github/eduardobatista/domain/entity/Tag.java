package io.github.eduardobatista.domain.entity;

import java.util.Collection;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Relationship.Direction;

import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@NodeEntity("tab")
public class Tag extends BaseEntity{
    @Property("label")
    private String label;
    
    @JsonbTransient
    @Relationship(type = "TAGGED_BY", direction = Direction.INCOMING)
    private Collection<Recipe> listRecipes;
    
    @JsonbTransient
    @Relationship(type = "TAGGED_BY", direction = Direction.INCOMING)
    private Collection<RecipeBook> listRecipeBooks;
}
