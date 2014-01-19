package org.azizkhani.dao.impl.hibernate.line;

import java.util.List;

import org.azizkhani.common.utility.HQLUtility;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.line.ILineRepository;
import org.azizkhani.model.line.Line;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;



@Repository
public class LineRepository extends GenericRepository<Line> implements ILineRepository {

	@Override
	protected Class<Line> getDomainClass() {
		return Line.class;
	}
	
	@Override
	public Line loadByEntityId(int entityId) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("select e from "+domainClass.getName()+" e where e.id=:id");
		Query query = session.createQuery(jql.toString());
		query.setInteger("id", entityId);
		return  (Line) query.uniqueResult();
	}
	@Override
	public List<Line> getAll(String where, String order, int pageNumber,int pageSize) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("select e from "+domainClass.getName()+" e join  e.responsible join e.terminal");
		HQLUtility.toHQL(jql, where, order);
		Query query = session.createQuery(jql.toString());
		if(pageSize>0)
		{
			query.setFirstResult(pageNumber*pageSize);
			query.setMaxResults(pageSize);
		}
		return  query.list();
	}

	
}