package br.com.wbotelhos.blank.repository.common;

import java.util.Collection;

import br.com.wbotelhos.blank.model.common.AbstractEntity;

public interface GenericRepository<T extends AbstractEntity> {

	Collection<T> loadAll();

	T loadById(Long id);

	void remove(T entity);

	T save(T entity);

}