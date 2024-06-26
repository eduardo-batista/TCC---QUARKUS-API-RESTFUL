package io.github.eduardobatista.service;

import java.util.ArrayList;
import java.util.Collection;

import io.github.eduardobatista.domain.entity.Recipe;
import io.github.eduardobatista.domain.entity.User;
import io.github.eduardobatista.dto.RecipeResponse;
import io.github.eduardobatista.dto.UserResponse;
import io.github.eduardobatista.repository.UserRepository;

public class UserService extends BaseService<User, UserResponse, UserRepository> {

    public UserService() {
        super(new UserRepository());
    }

    public Collection<RecipeResponse> loadFeed(Long id) {
        Collection<RecipeResponse> recipes = new ArrayList<RecipeResponse>();

        var feed = repository.loadFeed(id);

        feed.forEach(element -> {
            if (element.get("Recipe2").getClass() == Recipe.class) {
                recipes.add(new RecipeResponse((Recipe) element.get("Recipe2")));
            }
        });

        return recipes;
    }

    @Override
    public UserResponse load(Long id) {
        return new UserResponse(repository.load(id));
    }

    @Override
    public Collection<UserResponse> loadAll() {
        return UserResponse.collectionResponseProducer(repository.loadAll(), UserResponse.class);
    }

    public UserResponse save(User object) {
        return new UserResponse(repository.save(null, object));
    }

    public UserResponse likes(Long id, Long recipeId) {
        return new UserResponse(repository.likes(id, recipeId));
    }

    @Override
    public Collection<UserResponse> loadAllBy(Long id) {
        throw new UnsupportedOperationException("Use other method 'loadAll()'");
    }

    @Override
    public UserResponse save(Long userId, User object) {
        throw new UnsupportedOperationException("Use other method 'save'");
    }

    @Override
    public UserResponse update(Long userId, Long id, User object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
