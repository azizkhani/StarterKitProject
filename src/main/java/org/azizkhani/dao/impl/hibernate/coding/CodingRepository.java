package org.azizkhani.dao.impl.hibernate.coding;

import java.util.List;

import org.azizkhani.dao.coding.ICodingRepository;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.model.coding.CodingTitle;
import org.hibernate.Query;

public class CodingRepository extends GenericRepository<CodingTitle> implements ICodingRepository {
	
	@Override
	protected Class<CodingTitle> getDomainClass() {
		return CodingTitle.class;
	}

	@Override
	public List<CodingTitle> findByCodingId(int codingId) {
		Query query=getSession().createQuery("from CodingTitle coding Where coding.codingId=:codingId");
		query.setParameter("codingId", codingId);
		return query.list();
	}
}
