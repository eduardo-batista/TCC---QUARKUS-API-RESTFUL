package io.github.eduardobatista.rest.service;

import java.util.Collection;

import io.github.eduardobatista.domain.entity.Tag;
import io.github.eduardobatista.domain.repository.TagRepository;

public class TagService extends BaseService<Tag, Tag, TagRepository> {

    public TagService() {
        super(new TagRepository());
    }

    @Override
    public Tag load(Long id) {
        return repository.load(id);
    }

    @Override
    public Collection<Tag> loadAll() {
        return repository.loadAll();
    }

    @Override
    public Collection<Tag> loadAllBy(Long userId) {
        throw new UnsupportedOperationException("Use other method 'loadAllBy'");
    }

    public Tag save(Tag object) {
        return repository.save(null, object);
    }

    @Override
    public Tag save(Long userId, Tag object) {
        throw new UnsupportedOperationException("Use other method 'save'");
    }

    @Override
    public Tag update(Long userId, Long id, Tag object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long userId, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
