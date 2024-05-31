package io.github.eduardobatista.repository;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.Tag;

public class TagRepository extends BaseRepository<Tag> {

    @Override
    public Tag load(Long id) {
        return getSession().load(Tag.class, id);
    }

    @Override
    public Collection<Tag> loadAll() {
        return getSession().loadAll(Tag.class);
    }

    @Override
    public Collection<Tag> loadAllBy(Long userId) {
        throw new UnsupportedOperationException("Use other method 'loadAll'");
    }

    @Override
    public Tag save(Long userId, Tag object) {
        getSession().save(object);
        return object;
    }

    @Override
    public Tag update(Long id, Tag object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
