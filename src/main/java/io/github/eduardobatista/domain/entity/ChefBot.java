package io.github.eduardobatista.domain.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Relationship.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChefBot {
    @Id
    @GeneratedValue()
    private Long id;

    @Property
    private String name = "CHEF BOT";

    @Relationship(value = "LIKENESS_REPRESENTATION", direction = Direction.OUTGOING)
    private AbstractRecipeForLikenessRepresentation abstractRecipeForRecomendation;

    public ChefBot() {

    }

    public AbstractRecipeForLikenessRepresentation getAbstractRecipeForRecomendation() {
        if (this.abstractRecipeForRecomendation == null) {
            this.abstractRecipeForRecomendation = new AbstractRecipeForLikenessRepresentation();
        }
        return this.abstractRecipeForRecomendation;
    }
}
