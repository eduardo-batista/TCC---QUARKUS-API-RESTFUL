package io.github.eduardobatista.rest.service;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.User;
import io.github.eduardobatista.domain.repository.UserRepository;
import io.github.eduardobatista.rest.dto.UserResponse;

public class UserService extends BaseService<User, UserResponse, UserRepository> {

    public UserService() {
        super(new UserRepository());
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
