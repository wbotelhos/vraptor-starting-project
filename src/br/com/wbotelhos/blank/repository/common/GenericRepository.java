package br.com.wbotelhos.blank.repository.common;

import java.util.Collection;

import br.com.wbotelhos.blank.model.common.AbstractEntity;

public interface GenericRepository<T extends AbstractEntity> {

	Collection<T> loadAll() throws Exception;

	T loadById(Long id) throws Exception;

	void remove(T entity) throws Exception;

	T save(T entity) throws Exception;

}