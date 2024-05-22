package io.github.eduardobatista.rest.dto;

import io.github.eduardobatista.domain.entity.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecipeResponse extends BaseResponse<RecipeResponse, Recipe> {
    private String name;
    private String image;

    public RecipeResponse(Recipe object) {
        super(object.getId());
        this.name = object.getName();
        this.image = object.getImage();
    }

    public RecipeResponse(Long id, String name, String image) {
        super(id);
        this.name = name;
        this.image = image;
    }
}
