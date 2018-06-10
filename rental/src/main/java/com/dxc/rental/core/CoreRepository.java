package com.dxc.rental.core;

public interface CoreRepository<T> {	
	T create(T entity);

	T read(Long id);
}
