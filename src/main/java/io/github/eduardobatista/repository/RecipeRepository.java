package io.github.eduardobatista.repository;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecipeRepository extends BaseRepository<Recipe> {

    @Override
    public Recipe load(Long id) {
        return getSession().load(Recipe.class, id);
    }

    @Override
    public Collection<Recipe> loadAll() {
        return getSession().loadAll(Recipe.class);
    }

    @Override
    public Collection<Recipe> loadAllBy(Long userId) {
        return getSession().load(User.class, userId).getListRecipes();
    }

    @Override
    public Recipe save(Long userId, Recipe object) {
        object.setOwner(getSession().load(User.class, userId));
        getSession().save(object);
        return object;
    }

    @Override
    public Recipe update(Long id, Recipe object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
