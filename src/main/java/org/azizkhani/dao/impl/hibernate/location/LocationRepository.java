package org.azizkhani.dao.impl.hibernate.location;
import java.util.List;

import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.location.ILocationRepository;
import org.azizkhani.model.location.Location;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
@Repository
public class LocationRepository  extends GenericRepository<Location> implements ILocationRepository{

	@Override
	protected Class<Location> getDomainClass() {
		return Location.class;
	}
	
	@Override
	public List<Location> getAll(int parentId) {
		Session session = getSession();		
		StringBuffer jql = new StringBuffer("from Location e where    e.parent.id=:parentId");
		Query query = session.createQuery(jql.toString());
		query.setInteger("parentId", parentId);
		return  query.list();
	}
	
	@Override
	public Location findByCode(String code) {
		Session session = getSession();		
		StringBuffer jql = new StringBuffer("from Location e where    e.code=:code");
		Query query = session.createQuery(jql.toString());
		query.setString("code", code);
		return  (Location) query.uniqueResult();
	}
}
