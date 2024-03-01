package com.climate.decode.userservice.converter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class GenericMapper {

	private final Mapper mapper;

	public <FROM, TO> List<TO> convertAndMapList(List<FROM> fromList, final Class<TO> toClass,
			Function<TO, TO> customMapper) {
		return fromList.stream().map(from -> this.mapper.map(from, toClass))
				.map(customMapper != null ? customMapper : (from -> from)).collect(Collectors.toList());
	}

	public <FROM, TO> List<TO> convertAndMapList(List<FROM> fromList, final Class<TO> toClass) {
		return fromList.stream().map(from -> this.mapper.map(from, toClass)).collect(Collectors.toList());
	}

	public <FROM, TO> List<TO> mapCollectionToList(Collection<FROM> fromCollection, final Class<TO> toClass) {
		return fromCollection.stream().map(from -> this.mapper.map(from, toClass)).collect(Collectors.toList());
	}

	public <FROM, TO> Set<TO> mapCollectionToSet(Collection<FROM> fromCollection, final Class<TO> toClass) {
		return fromCollection.stream().map(from -> this.mapper.map(from, toClass)).collect(Collectors.toSet());
	}

	public <FROM, TO> List<TO> mapCollectionWithCustomMapper(Collection<FROM> fromCollection, final Class<TO> toClass,
			Function<TO, TO> customMapper) {
		return fromCollection.stream().map(from -> this.mapper.map(from, toClass))
				.map(Objects.requireNonNull(customMapper)).collect(Collectors.toList());
	}

	public <FROM, TO> Set<TO> convertAndMapSet(Set<FROM> fromSet, final Class<TO> toClass,
			Function<TO, TO> customMapper) {
		return fromSet.stream().map(from -> this.mapper.map(from, toClass)).map(customMapper)
				.collect(Collectors.toSet());
	}

	public <FROM, TO> TO convert(FROM fromObject, final Class<TO> toClass, Function<TO, TO> customMapper) {
		Assert.notNull(customMapper, "");
		return customMapper.apply(this.mapper.map(fromObject, toClass));
	}

	public <FROM, TO> TO convert(FROM fromObject, final Class<TO> toClass) {
		return this.mapper.map(fromObject, toClass);
	}
}
