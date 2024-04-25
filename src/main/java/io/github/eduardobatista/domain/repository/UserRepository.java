package io.github.eduardobatista.domain.repository;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository extends BaseRepository<User> {

    @Override
    public User load(Long id) {
        return getSession().load(User.class, id);
    }

    @Override
    public Collection<User> loadAll() {
        return getSession().loadAll(User.class);
    }

    @Override
    public Collection<User> loadAllBy(Long id) {
        throw new UnsupportedOperationException("Use other the method 'loadAll'");
    }

    @Override
    public User save(Long userId, User object) {
        getSession().save(object);
        return object;
    }

    public User likes(Long id, Long recipeId) {
        var recipe = getSession().load(Recipe.class, recipeId);
        var user = getSession().load(User.class, id);

        user.getListLikes().add(recipe);

        getSession().save(user);

        return user;
    }

    @Override
    public User update(Long id, User object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
