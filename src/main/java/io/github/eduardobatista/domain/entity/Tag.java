package io.github.eduardobatista.domain.entity;

import java.util.Collection;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Relationship.Direction;

import jakarta.json.JsonObject;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@NodeEntity("tag")
@ToString
public class Tag extends BaseEntity{
    @Property("label")
    private String label;
    
    @JsonbTransient
    @Relationship(type = "TAGGED_BY", direction = Direction.INCOMING)
    private Collection<Recipe> listRecipes;
    
    @JsonbTransient
    @Relationship(type = "TAGGED_BY", direction = Direction.INCOMING)
    private Collection<RecipeBook> listRecipeBooks;

    public Tag(String label) {
        this.label = label;
    }

    public Tag(Long id, String label) {
        this.setId(id);
        this.label = label;
    }

    public static Tag fromJson(JsonObject object) {
        return new Tag(object.getJsonNumber("id").longValue(), object.getString("label"));
    }
}
