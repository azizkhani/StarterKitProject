package org.azizkhani.dao.impl.hibernate.device;

import java.util.List;

import org.azizkhani.common.utility.HQLUtility;
import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.device.IDeviceHistoryRepository;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.model.device.DeviceHistory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceHistoryRepository extends GenericRepository<DeviceHistory> implements IDeviceHistoryRepository {

	@Override
	protected Class<DeviceHistory> getDomainClass() {
		return DeviceHistory.class;
	}
 
	@Override
	public void setAllDeviceHistoryStatusFalse(int deviceId) {
		Session session = getSession();
		Query query = session.createQuery("update "+domainClass.getName()+" e   set e.status=False  where e.device.id = :id ");
		query.setParameter("id",deviceId);
		int result = query.executeUpdate(); 
	}

	@Override
	public QueryResult<DeviceHistory> getAllGrid(int deviceId, String where,String order, int pageNumber, int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("from "+domainClass.getName()+" e where e.device.id=:deviceId ");
		HQLUtility.toHQL(jql, where, order);
		Query query = session.createQuery(jql.toString());
		query.setParameter("deviceId",deviceId);
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		List<DeviceHistory> list=query.list();
		
		StringBuffer jqlCount = new StringBuffer("select count(*)  from "+domainClass.getName()+" e where e.device.id=:deviceId ");
		HQLUtility.toHQL(jqlCount, where, "");
		Query queryCount = getSession().createQuery(jqlCount.toString());
		queryCount.setParameter("deviceId",deviceId);
		
		int count=((Long)queryCount.uniqueResult()).intValue();
		
		return  new QueryResult<DeviceHistory>(pageNumber, count, pageSize,list);
	}
}
