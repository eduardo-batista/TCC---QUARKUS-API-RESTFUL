package io.github.eduardobatista.rest.service;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.repository.RecipeRepository;
import io.github.eduardobatista.rest.dto.RecipeResponse;

public class RecipeService extends BaseService<Recipe, RecipeResponse, RecipeRepository> {

    public RecipeService() {
        super(new RecipeRepository());
    }

    @Override
    public RecipeResponse load(Long id) {
        return new RecipeResponse(repository.load(id));
    }

    @Override
    public Collection<RecipeResponse> loadAll() {
        return RecipeResponse.collectionResponseProducer(repository.loadAll(), RecipeResponse.class);
    }

    @Override
    public Collection<RecipeResponse> loadAllBy(Long userId) {
        return RecipeResponse.collectionResponseProducer(repository.loadAllBy(userId), RecipeResponse.class);
    }

    @Override
    public RecipeResponse save(Long userId, Recipe object) {
        return new RecipeResponse(repository.save(userId, object));
    }

    @Override
    public RecipeResponse update(Long userId, Long id, Recipe object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
