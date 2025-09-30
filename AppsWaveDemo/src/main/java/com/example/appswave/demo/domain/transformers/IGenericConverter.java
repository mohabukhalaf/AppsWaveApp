package com.example.appswave.demo.domain.transformers;

import java.util.List;

public interface IGenericConverter<T, E> {

	T toTO(E e);

	List<T> toTOs(List<E> list);

	E toEntity(T t);

	List<E> toEntities(List<T> list);
}
