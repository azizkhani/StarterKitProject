package org.azizkhani.dao.impl.hibernate;

import java.util.List;

import org.azizkhani.common.utility.HQLUtility;
import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.baseInfo.BaseInformation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public abstract class GenericRepository<T> extends HibernateDaoSupport implements IGenericRepository<T> {
	protected Class<T> domainClass = getDomainClass();
	protected abstract Class<T> getDomainClass();
	
	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
	@Override
	public List<T> getAll() {
		Criteria criteria=getSession().createCriteria(domainClass.getName());
		return criteria.list();
	}
	
	
	public QueryResult<T> getAllGrid(String where, String order, int pageNumber,int pageSize){
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e ");
		HQLUtility.toHQL(jql, where, order);
		Query query = session.createQuery(jql.toString());
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		List<T> list=query.list();
		StringBuffer jqlCount = new StringBuffer("select count(*)  from "+domainClass.getName()+" e ");
		HQLUtility.toHQL(jqlCount, where, "");
		int count=((Long)getSession().createQuery(jqlCount.toString()).uniqueResult()).intValue();
		
		return  new QueryResult<T>(pageNumber, count, pageSize,list);
	}
	
	@Override
	public List<T> getAll(String where, String order, int pageNumber,int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e ");
		HQLUtility.toHQL(jql, where, order);
		Query query = session.createQuery(jql.toString());
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		return  query.list();
	}
	@Override
	public T loadByEntityId(int entityId) {
		return (T) getSession().load(domainClass, entityId);
	}

	@Override
	public T single(String where) {
		return null;
	}

	@Override
	public void add(T entity) {
		Session session = getSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public void deleteByEntityId(int entityId) {
		Session session = getSession();
		session.beginTransaction();
		Object obj = session.load(domainClass, entityId);
		session.delete(obj);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(T entity) {
		Session session = getSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public int count() {
		int count = ((Long)getSession().createQuery("select count(*) from " + domainClass.getName()).uniqueResult()).intValue();
		return count;
	}
}
