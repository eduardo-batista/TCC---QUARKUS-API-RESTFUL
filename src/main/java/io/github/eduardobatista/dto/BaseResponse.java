package io.github.eduardobatista.dto;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class BaseResponse<T, E> {
    public Long id;

    public BaseResponse(Long id) {
        this.id = id;
    }

    public static <T, E> Collection<T> collectionResponseProducer(Collection<E> collection, Class<T> clazz) {
        Collection<T> collectionResponse = new ArrayList<T>();
        try {
            for (E object : collection) {
                T newObj = clazz.getDeclaredConstructor(object.getClass()).newInstance(object);
                collectionResponse.add(newObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return collectionResponse;
    }

}
