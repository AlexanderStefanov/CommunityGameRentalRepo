package com.dxc.rental.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
@Component
public class CoreRepositoryImplementation<T> implements CoreRepository<T>  {

	@PersistenceContext
	protected EntityManager context;
	
	protected Class<T> entryClass;

	@Override
	public T create(T entity) {
		T result = null;
		try {
			context.getTransaction().begin();
			context.persist(entity);
			context.getTransaction().commit();
			result = entity;
		} catch(Exception e){
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public T read(Long id) {
		T result = null;
		try {
			context.getTransaction().begin();
			result = (T) context.find(entryClass, id);
			context.getTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}
	
	
	
}
