package com.ftn.PreporukaOdevneKombinacije.helper;

public interface MapperInterface<T,U> {

    T toEntity(U dto);

    U toDto(T entity);
}

