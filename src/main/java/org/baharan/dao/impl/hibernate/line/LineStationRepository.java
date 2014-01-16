package org.baharan.dao.impl.hibernate.line;

import java.util.List;


import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.dao.line.ILineStationRepository;
import org.baharan.model.line.LineStation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;



@Repository
public class LineStationRepository extends GenericRepository<LineStation> implements ILineStationRepository {

	@Override
	protected Class<LineStation> getDomainClass() {
		return LineStation.class;
	}

	@Override
	public List<LineStation> getAll(int lineId) {
		Session session = getSession();
		StringBuffer jql = new StringBuffer("select e from "+domainClass.getName()+" e  where e.line.id=:lineId");
		Query query = session.createQuery(jql.toString());
		query.setInteger("lineId", lineId);
		return  query.list();
	}
}