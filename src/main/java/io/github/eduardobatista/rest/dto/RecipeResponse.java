package io.github.eduardobatista.rest.dto;

import io.github.eduardobatista.domain.entity.Recipe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecipeResponse extends BaseResponse<RecipeResponse, Recipe>  {
    private String name;
    
    public RecipeResponse(Recipe object) {
        super(object.getId());
        this.name = object.getName();
    }
}
