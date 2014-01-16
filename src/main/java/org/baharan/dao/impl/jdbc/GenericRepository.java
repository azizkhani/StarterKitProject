package org.baharan.dao.impl.jdbc;

import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;


@Repository
public abstract class GenericRepository<T> extends JdbcDaoSupport  implements IGenericRepository<T> {
	protected Class<T> domainClass = getDomainClass();
	protected abstract Class<T> getDomainClass();
	
	
	@Autowired
	public void init(DataSource dataSource) {
	    setDataSource(dataSource);
	}
	
	@Override
	public List<T> getAll() {
		return null;
	}
	
	@Override
	public List<T> getAll(String where, String order, int pageNumber,int pageSize) {
		return null;
	}

	@Override
	public T loadByEntityId(int entityId) {
		return null;
	}

	@Override
	public T single(String where) {
		return null;
	}

	@Override
	public void add(T entity) {
		
	}

	@Override
	public void delete(T entity) {
		
	}

	@Override
	public void deleteByEntityId(int entityId) {
		
	}

	@Override
	public void update(T entity) {
		
	}

	@Override
	public int count() {
		return 0;
	}
}
