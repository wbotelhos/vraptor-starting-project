package br.com.wbotelhos.blank.business.common;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.wbotelhos.blank.model.common.AbstractEntity;
import br.com.wbotelhos.blank.repository.common.GenericRepository;

public abstract class GenericBusiness<T extends AbstractEntity> implements GenericRepository<T> {

	protected final EntityManager manager;
	private final Class<T> clazz;

	protected GenericBusiness(EntityManager manager) {
		this.manager = manager;

		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		this.clazz = clazz;
	}

	public Collection<T> loadAll() {
		Query query = manager.createQuery("from " + clazz.getName());

		@SuppressWarnings("unchecked")
		Collection<T> resultList = query.getResultList();

		return resultList;
	}

	public T loadById(Long id) {
		return manager.find(clazz, id);
	}

	public void remove(T entity) {
		manager.remove(manager.find(clazz, entity.getId()));
	}

	public T save(T entity) {
		return manager.merge(entity);
	}

}