package io.github.eduardobatista.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Relationship.Direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.eduardobatista.domain.entity.relationships.AbstractRecipeForLikenessRepresentation;
import io.github.eduardobatista.domain.entity.relationships.Follow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@NodeEntity("user")
@JsonIgnoreProperties({ "follower", "followed" })
public class User extends BaseEntity {
    @Property("name")
    private String name;
    @Property("email")
    private String email;
    @Property("password")
    private String password;
    @Property("birthDate")
    private LocalDate birthDate;
    @Property("picture")
    private String picture;
    @Property("bio")
    private String bio;
    @Property("identifier")
    private String identifier;
    @Property("administrator")
    private Boolean administrator;

    @Relationship(value = "LIKENESS_REPRESENTATION", direction = Direction.OUTGOING)
    private AbstractRecipeForLikenessRepresentation abstractRecipeForLikenessRepresentation;

    @Relationship(value = "CREATED_BY", direction = Direction.INCOMING)
    private Collection<Recipe> listRecipes;

    @Relationship(value = "CREATED_BY", direction = Direction.INCOMING)
    private Collection<RecipeBook> listRecipeBooks;

    @Relationship(type = "FOLLOWED_BY", direction = Direction.INCOMING)
    private Collection<Follow> listFollowers;

    @Relationship(type = "FOLLOWED_BY", direction = Direction.OUTGOING)
    private Collection<Follow> listFolloweds;

    @Relationship(type = "LIKED_BY", direction = Direction.INCOMING)
    private Collection<Recipe> listLikes;

    public User(String name, String email, String password, LocalDate birthDate, String picture, String bio,
            String identifier, Boolean administrator) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.picture = picture;
        this.bio = bio;
        this.identifier = identifier;
        this.administrator = administrator;

        this.abstractRecipeForLikenessRepresentation = new AbstractRecipeForLikenessRepresentation();
        this.abstractRecipeForLikenessRepresentation
                .setName("Abstract Recipe for user likeness representation");
    }

    public Collection<Recipe> getListRecipes() {
        if (this.listRecipes == null)
            this.listRecipes = new ArrayList<>();
        return listRecipes;
    }

    public Collection<RecipeBook> getListRecipeBooks() {
        if (this.listRecipeBooks == null)
            this.listRecipeBooks = new ArrayList<>();
        return listRecipeBooks;
    }

    public Collection<Follow> getListFollowers() {
        if (this.listFollowers == null)
            this.listFollowers = new ArrayList<>();
        return listFollowers;
    }

    public Collection<Follow> getListFolloweds() {
        if (this.listFolloweds == null)
            this.listFolloweds = new ArrayList<>();
        return listFolloweds;
    }

    public Collection<Recipe> getListLikes() {
        if (this.listLikes == null)
            this.listLikes = new ArrayList<>();
        return listLikes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (bio == null) {
            if (other.bio != null)
                return false;
        } else if (!bio.equals(other.bio))
            return false;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (administrator == null) {
            if (other.administrator != null)
                return false;
        } else if (!administrator.equals(other.administrator))
            return false;
        if (listRecipes == null) {
            if (other.listRecipes != null)
                return false;
        } else if (!listRecipes.equals(other.listRecipes))
            return false;
        if (listRecipeBooks == null) {
            if (other.listRecipeBooks != null)
                return false;
        } else if (!listRecipeBooks.equals(other.listRecipeBooks))
            return false;
        if (listFollowers == null) {
            if (other.listFollowers != null)
                return false;
        } else if (!listFollowers.equals(other.listFollowers))
            return false;
        if (listFolloweds == null) {
            if (other.listFolloweds != null)
                return false;
        } else if (!listFolloweds.equals(other.listFolloweds))
            return false;
        return true;
    }

}
