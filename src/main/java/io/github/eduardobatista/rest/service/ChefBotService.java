package io.github.eduardobatista.rest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.repository.ChefBotRepository;
import io.github.eduardobatista.rest.dto.RecipeResponse;
import jakarta.inject.Inject;

public class ChefBotService {

    @Inject
    ChefBotRepository repository = new ChefBotRepository();

    public Collection<RecipeResponse> getRecomendations(ArrayList<Long> tagsId) {
        Collection<RecipeResponse> recipes = new ArrayList<RecipeResponse>();

        Iterable<Map<String, Object>> recomendations = repository.getRecomendations(tagsId);

        recomendations.forEach(element -> {
            if (element.get("Recipe2").getClass() == Recipe.class) {
                recipes.add(new RecipeResponse((Recipe) element.get("Recipe2")));
            }
        });

        return recipes;
    }

}
