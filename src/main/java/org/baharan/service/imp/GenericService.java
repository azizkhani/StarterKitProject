package org.baharan.service.imp;

import java.util.Date;
import java.util.List;

import org.baharan.core.QueryResult;
import org.baharan.dao.IGenericRepository;
import org.baharan.model.BaseEntity;
import org.baharan.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public abstract class GenericService<T> implements IGenericService<T> {
	
	protected abstract IGenericRepository<T> getGenericRepo();
	
	@Override
	public List<T> getAll() {
		return getGenericRepo().getAll();
	}
	
	@Override
	public List<T> getAll(String where, String order, int pageNumber,int pageSize) {
		return getGenericRepo().getAll(where, order, pageNumber, pageSize);
	}
	
	@Override
	public QueryResult<T> getAllGrid(String where, String order,int pageNumber, int pageSize) {
		return getGenericRepo().getAllGrid(where, order, pageNumber, pageSize);
	}
	
	@Override
	public T loadByEntityId(int entityId) {
		return getGenericRepo().loadByEntityId(entityId);
	}

	@Override
	public T single(String where) {
		return null;
	}

	@Override
	public void add(T entity) {
		getGenericRepo().add(entity);
	}

	@Override
	public void delete(T entity) {
		getGenericRepo().delete(entity);
	}

	@Override
	public void deleteByEntityId(int entityId) {
		getGenericRepo().deleteByEntityId(entityId);
	}

	@Override
	public void update(T entity) {
		getGenericRepo().update(entity);
	}

	@Override
	public int count() {
		return	getGenericRepo().count();
	}
	
	
}
