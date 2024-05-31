package io.github.eduardobatista.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import io.github.eduardobatista.domain.entity.RecipeBook;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeBookResponse extends BaseResponse<RecipeBookResponse, RecipeBook> {

    private String name;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean active;
    private UserResponse owner;
    private Collection<RecipeResponse> listRecipes;

    public RecipeBookResponse(RecipeBook object) {
        super(object.getId());
        this.image = object.getImage();
        this.createdAt = object.getCreatedAt();
        this.updatedAt = object.getUpdatedAt();
        this.active = object.getActive();

        this.owner = new UserResponse(object.getOwner());
        this.listRecipes = RecipeResponse.collectionResponseProducer(object.getListRecipes(), RecipeResponse.class);
    }
}
