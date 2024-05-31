package io.github.eduardobatista.service;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.relationships.Follow;
import io.github.eduardobatista.dto.FollowResponse;
import io.github.eduardobatista.repository.FollowRepository;

public class FollowService extends BaseService<Follow, FollowResponse, FollowRepository> {

    public FollowService() {
        super(new FollowRepository());
    }

    @Override
    public FollowResponse load(Long id) {
        return new FollowResponse(repository.load(id));
    }

    @Override
    public Collection<FollowResponse> loadAll() {
        return FollowResponse.collectionResponseProducer(repository.loadAll(), FollowResponse.class);
    }

    @Override
    public Collection<FollowResponse> loadAllBy(Long userId) {
        return FollowResponse.collectionResponseProducer(repository.loadAllBy(userId), FollowResponse.class);
    }

    public FollowResponse save(Long followerId, Long followedId) {
        return new FollowResponse(repository.save(followerId, followedId));
    }

    @Override
    public FollowResponse save(Long userId, Follow object) {
        throw new UnsupportedOperationException("Use other method 'save'");
    }

    @Override
    public FollowResponse update(Long userId, Long id, Follow object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
