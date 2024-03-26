package io.github.eduardobatista.domain.entity;

import java.util.ArrayList;
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
@NodeEntity("recipe")
public class Recipe extends BaseEntity {
    @Property(name = "name")
    private String name;
    @Property(name = "description")
    private String description;
    @Property(name = "ingredients")
    private String ingredients;
    @Property(name = "instructions")
    private String instructions;
    @Property(name = "image")
    private String image;

    @JsonbTransient
    @Relationship(type = "CREATED_BY", direction = Direction.OUTGOING)
    private User owner;

    @JsonbTransient
    @Relationship(type = "BELONGS_TO", direction = Direction.OUTGOING)
    private Collection<RecipeBook> listRecipeBooks;

    @Relationship(type = "TAGGED_BY", direction = Direction.OUTGOING)
    private Collection<Tag> listTags;

    public Collection<RecipeBook> getListRecipeBooks() {
        if (this.listRecipeBooks == null)
            this.listRecipeBooks = new ArrayList<RecipeBook>();
        return listRecipeBooks;
    }

    public Recipe(String name, String description, String ingredients, String instructions, String image) {
        super();
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.image = image;
    }

}
