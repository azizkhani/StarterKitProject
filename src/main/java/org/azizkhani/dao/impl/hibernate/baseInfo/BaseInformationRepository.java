package org.azizkhani.dao.impl.hibernate.baseInfo;

import java.util.List;

import org.azizkhani.common.utility.HQLUtility;
import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.baseInfo.IBaseInformationRepository;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.model.baseInfo.BaseInformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class BaseInformationRepository extends GenericRepository<BaseInformation> implements IBaseInformationRepository{

	@Override
	protected Class<BaseInformation> getDomainClass() {
		return BaseInformation.class;
	}
	
	@Override
	public QueryResult<BaseInformation> getAllGrid(int parentId, String where,String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where e.parent.id=" + parentId);
		HQLUtility.toHQL(jql, where, order);
		Query query = session.createQuery(jql.toString());
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		List<BaseInformation> list=query.list();
		
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  from "+domainClass.getName()+" e where e.parent.id=" + parentId);
		HQLUtility.toHQL(jqlCount, where, "");
		int count=((Long)getSession().createQuery(jqlCount.toString()).uniqueResult()).intValue();
		return  new QueryResult<BaseInformation>(pageNumber, count, pageSize,list);
	}
	
}
