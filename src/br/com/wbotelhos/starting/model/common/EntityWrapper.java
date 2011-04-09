package br.com.wbotelhos.starting.model.common;

import java.util.Collection;

public class EntityWrapper<T extends AbstractEntity> {

	private Collection<T> entityList;
	private Integer total;

	public Collection<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(Collection<T> entityList) {
		this.entityList = entityList;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}