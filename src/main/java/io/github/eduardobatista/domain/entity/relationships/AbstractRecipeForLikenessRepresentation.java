package io.github.eduardobatista.domain.entity.relationships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Relationship.Direction;

import io.github.eduardobatista.domain.entity.AbstractRecipe;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity("likeness_representation")
public class AbstractRecipeForLikenessRepresentation extends AbstractRecipe {
    @Property
    private String name = "ABSTRACT RECIPE";

    @Relationship(type = "TAGGED_BY", direction = Direction.OUTGOING)
    private Collection<Tagged> listTags;

    public Collection<Tagged> getListTags() {
        if (this.listTags == null) {
            listTags = new ArrayList<Tagged>();
        }
        return listTags;
    }
}
