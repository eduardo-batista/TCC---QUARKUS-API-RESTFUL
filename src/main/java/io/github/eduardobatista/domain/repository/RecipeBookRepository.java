package io.github.eduardobatista.domain.repository;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.RecipeBook;
import io.github.eduardobatista.domain.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecipeBookRepository extends BaseRepository<RecipeBook> {

    @Override
    public RecipeBook load(Long id) {
        return getSession().load(RecipeBook.class, id);
    }

    @Override
    public Collection<RecipeBook> loadAll() {
        return getSession().loadAll(RecipeBook.class);
    }

    @Override
    public Collection<RecipeBook> loadAllBy(Long userId) {
        return getSession().load(User.class, userId).getListRecipeBooks();
    }

    @Override
    public RecipeBook save(Long userId, RecipeBook object) {
        object.setOwner(getSession().load(User.class, userId));
        getSession().save(object);
        return object;
    }

    @Override
    public RecipeBook update(Long id, RecipeBook object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
