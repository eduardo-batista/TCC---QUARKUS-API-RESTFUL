package io.github.eduardobatista.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.dto.RecipeResponse;
import io.github.eduardobatista.repository.ChefBotRepository;
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
