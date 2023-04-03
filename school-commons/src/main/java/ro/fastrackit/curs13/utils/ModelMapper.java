package ro.fastrackit.curs13.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ModelMapper<A, E> {
    A toApi(E entity);

    E toEntity(A api);

    default List<A> toApi(Collection<E> entities) {
        return entities.stream()
                .map(this::toApi)
                .collect(Collectors.toList());
    }

    default List<E> toEntity(Collection<A> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
