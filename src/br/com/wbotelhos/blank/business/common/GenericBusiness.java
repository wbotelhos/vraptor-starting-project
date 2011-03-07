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

	public Collection<T> loadAll() throws Exception {
		try {
			Query query = manager.createQuery("from " + clazz.getName());

			@SuppressWarnings("unchecked")
			Collection<T> resultList = query.getResultList();

			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível listar os registros.", e);
		}
	}

	public T loadById(Long id) throws Exception {
		try {
			return manager.find(clazz, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível carregar o registro.", e);
		}
	}

	public void remove(T entity) throws Exception {
		try {
			manager.remove(manager.find(clazz, entity.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível remover o registro.", e);
		}
	}

	public T save(T entity) throws Exception {
		try {
			return manager.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw new Exception("Não foi possível inserir o registro.", e);
		}
	}

}