package br.com.wbotelhos.starting.repository.common;

import java.util.Collection;

import br.com.wbotelhos.starting.exception.CommonException;
import br.com.wbotelhos.starting.model.common.AbstractEntity;

public interface GenericRepository<T extends AbstractEntity> {

	Collection<T> all();

	T loadById(Long id);

	void remove(T entity);

	T save(T entity) throws CommonException;

}
