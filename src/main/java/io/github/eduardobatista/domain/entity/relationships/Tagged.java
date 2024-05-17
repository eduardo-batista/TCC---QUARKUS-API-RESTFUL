package io.github.eduardobatista.domain.entity.relationships;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import io.github.eduardobatista.domain.entity.AbstractRecipeForLikenessRepresentation;
import io.github.eduardobatista.domain.entity.Tag;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RelationshipEntity("TAGGED_BY")
public class Tagged extends Tag {

    @Property
    private int amount;

    @JsonbTransient
    @StartNode
    @Property(name = "recipe")
    private AbstractRecipeForLikenessRepresentation abstractRecipe;

    @JsonbTransient
    @EndNode
    @Property(name = "tag")
    private Tag tag;

    public Tagged(AbstractRecipeForLikenessRepresentation abstractRecipe, Tag tag) {
        super();
        this.abstractRecipe = abstractRecipe;
        this.tag = tag;
        this.amount = 1;
    }

    public void incrementAmount() {
        this.amount++;
    }

}
