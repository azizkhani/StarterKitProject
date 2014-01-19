package org.azizkhani.service;

import java.util.List;

import org.azizkhani.core.QueryResult;

public interface IGenericService <T> {
	public List<T> getAll();
	public List<T> getAll(String where, String order, int pageNumber,int pageSize);
	public QueryResult<T> getAllGrid(String where, String order, int pageNumber,int pageSize);
	public T loadByEntityId(int entityId);
	public T single(String where);
	public void add(T entity);
	public void delete(T entity);
	public void deleteByEntityId(int entityId);
	public void update(T entity);
	public void save(T entity);
	public int count();
}
