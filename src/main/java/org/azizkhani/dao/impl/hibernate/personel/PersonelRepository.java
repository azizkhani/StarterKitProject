package org.azizkhani.dao.impl.hibernate.personel;

import java.util.List;
import java.util.Set;

import org.azizkhani.common.utility.HQLUtility;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.dao.personel.IPersonelRepository;
import org.azizkhani.model.organization.OrganizationStructure;
import org.azizkhani.model.personel.Personel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("HibernatePersonelRepository")
public class PersonelRepository extends GenericRepository<Personel> implements IPersonelRepository {

	@Override
	protected Class<Personel> getDomainClass() {
		return Personel.class;
	}

	@Override
	public Personel findByPersonelCode(String PersonelCode) {
		Session session = getSession();		
		StringBuffer hql = new StringBuffer("from "+domainClass.getName()+" e where e.personCode='"+PersonelCode+"'");
		Query query = session.createQuery(hql.toString());
		return  (Personel) query.uniqueResult();
	}

	@Override
	public List<Personel> find(String personelCode, String lastName,int resultCount) {
		Session session = getSession();		
		StringBuffer hql = new StringBuffer("from "+domainClass.getName()+" e where 1<>2");
		if(!personelCode.isEmpty())
			hql.append(" and e.personCode like '"+personelCode+"%' ");
		if(!lastName.isEmpty())
			hql.append(" and e.lastName like '%"+lastName+"%' ");
		Query query = session.createQuery(hql.toString());
		
		if(resultCount>0)
			query.setMaxResults(resultCount);
		return  query.list();
	}

	@Override
	@Deprecated
	public List<Personel> find(String personelCode, String lastName,
			int resultCount, Set<OrganizationStructure> orgLimitation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validatePersonel(int personelId, String personCode) {
		Session session = getSession();		
		StringBuffer jqlCount = new StringBuffer("select count(*)  from "+domainClass.getName()+" e where e.personCode=:personCode ");
		if(personelId>0)
			jqlCount.append(" and e.id!=:personelId");
		
		Query query = session.createQuery(jqlCount.toString());
		query.setString("personCode", personCode);
		if(personelId>0)
			query.setInteger("personelId", personelId);
		int count=((Long)query.uniqueResult()).intValue();
		if(count>0)
			return false;
		return true;
	}
}
