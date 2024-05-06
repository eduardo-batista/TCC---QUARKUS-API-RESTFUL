package io.github.eduardobatista.rest.dto;

import io.github.eduardobatista.domain.entity.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class RecipeResponse extends BaseResponse<RecipeResponse, Recipe>  {
    private String name;
    
    public RecipeResponse(Recipe object) {
        super(object.getId());
        this.name = object.getName();
    }

    public RecipeResponse(Long id, String name) {
        super(id);
        this.name = name;
    }
}
