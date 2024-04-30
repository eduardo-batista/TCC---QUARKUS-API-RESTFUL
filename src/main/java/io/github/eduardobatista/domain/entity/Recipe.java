package io.github.eduardobatista.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Relationship.Direction;

import io.github.eduardobatista.domain.repository.TagRepository;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@NodeEntity("recipe")
public class Recipe extends AbstractRecipe {
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

    @Relationship(type = "LIKED_BY", direction = Direction.OUTGOING)
    private Collection<User> listlikers;

    public Collection<RecipeBook> getListRecipeBooks() {
        if (this.listRecipeBooks == null)
            this.listRecipeBooks = new ArrayList<RecipeBook>();
        return listRecipeBooks;
    }

    public Collection<Tag> getListTags() {
        if (this.listTags == null)
            this.listTags = new ArrayList<Tag>();
        return listTags;
    }

    public Recipe(String name, String description, String ingredients, String instructions, String image,
            Collection<Tag> listTags) {
        super();
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.image = image;
        getListTags().addAll(listTags);
    }

    public static Recipe fromJson(JsonObject object) {
        TagRepository tagRepository = new TagRepository();
        Collection<Tag> listTags = new ArrayList<Tag>();
        for (JsonValue tag : object.getJsonArray("listTags")) {
            if (tag.getValueType() == ValueType.NUMBER) {
                listTags.add(tagRepository.load(((JsonNumber) tag).longValue()));
            }
        }
        return new Recipe(object.getString("name"), object.getString("description"), object.getString("ingredients"),
                object.getString("instructions"), object.containsKey("image") ? object.getString("image") : null,
                listTags);
    }
}
