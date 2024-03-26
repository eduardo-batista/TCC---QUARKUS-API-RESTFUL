package io.github.eduardobatista.domain.repository;

import java.util.Collection;

import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;

import io.github.eduardobatista.domain.entity.User;
import io.github.eduardobatista.domain.entity.relationships.Follow;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FollowRepository extends BaseRepository<Follow> {

    @Override
    public Follow load(Long id) {
        return getSession().load(Follow.class, id);
    }

    @Override
    public Collection<Follow> loadAll() {
        return getSession().loadAll(Follow.class);
    }

    @Override
    public Collection<Follow> loadAllBy(Long userId) {
        return getSession().loadAll(
                Follow.class, new Filter("followed", ComparisonOperator.EQUALS, userId)
                        .or(new Filter("follower", ComparisonOperator.EQUALS, userId)));
    }

    public Collection<Follow> loadAllFollowedsBy(Long userId) {
        return getSession().loadAll(
                Follow.class, new Filter("follower", ComparisonOperator.EQUALS, userId));
    }

    public Collection<Follow> loadAllFollowersBy(Long userId) {
        return getSession().loadAll(
                Follow.class, new Filter("follower", ComparisonOperator.EQUALS, userId));
    }

    @Override
    public Follow save(Long userId, Follow object) {
        throw new UnsupportedOperationException("Use other method 'save'");
    }

    public Follow save(Long followerId, Long followedId) {
        User follower = getSession().load(User.class, followerId);
        User followed = getSession().load(User.class, followedId);

        Follow object = new Follow(follower, followed);

        getSession().save(object);

        return object;
    }

    @Override
    public Follow update(Long id, Follow object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
