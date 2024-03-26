package io.github.eduardobatista.domain.entity.relationships;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import io.github.eduardobatista.domain.entity.BaseEntity;
import io.github.eduardobatista.domain.entity.User;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RelationshipEntity(type = "FOLLOWED_BY")
public class Follow extends BaseEntity{

    @JsonbTransient
    @EndNode @Property(name = "follower")
    private User follower;

    @JsonbTransient
    @StartNode @Property(name = "followed")
    private User followed;

    public Follow(User follower, User followed) {
        super();
        this.follower = follower;
        this.followed = followed;
    }
}
