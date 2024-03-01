package com.climate.decode.userservice.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<E, RQ, RS> {
    E toEntity(RQ dto);

    RS toDto(E entity);

    E updateEntity(E entity, RQ dto);

    E patchEntity(E entity, RQ dto);

    default List<RS> fromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<E> fromDtos(final Collection<RQ> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
