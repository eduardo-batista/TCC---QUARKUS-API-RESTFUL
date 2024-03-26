package io.github.eduardobatista.rest.service;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.RecipeBook;
import io.github.eduardobatista.domain.repository.RecipeBookRepository;
import io.github.eduardobatista.rest.dto.RecipeBookResponse;

public class RecipeBookService extends BaseService<RecipeBook, RecipeBookResponse, RecipeBookRepository> {

    public RecipeBookService() {
        super(new RecipeBookRepository());
    }

    @Override
    public RecipeBookResponse load(Long id) {
        return new RecipeBookResponse(repository.load(id));
    }

    @Override
    public Collection<RecipeBookResponse> loadAll() {
        return RecipeBookResponse.collectionResponseProducer(repository.loadAll(), RecipeBookResponse.class);
    }

    @Override
    public Collection<RecipeBookResponse> loadAllBy(Long userId) {
        return RecipeBookResponse.collectionResponseProducer(repository.loadAllBy(userId), RecipeBookResponse.class);
    }

    @Override
    public RecipeBookResponse save(Long userId, RecipeBook object) {
        return new RecipeBookResponse(repository.save(userId, object));
    }

    @Override
    public RecipeBookResponse update(Long userId, Long id, RecipeBook object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
