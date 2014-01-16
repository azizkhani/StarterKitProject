package org.baharan.dao.impl.hibernate.organization;

import java.util.List;

import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.dao.organization.IOrganizationStructureRepository;
import org.baharan.model.organization.OrganizationStructure;
import org.hibernate.Query;
import org.hibernate.Session;

public class OrganizationStructureRepository extends GenericRepository<OrganizationStructure>  implements IOrganizationStructureRepository{

	@Override
	public List<OrganizationStructure> getAll(String parentId) {
		
		Session session = getSession();		
		StringBuffer jql = new StringBuffer("from OrganizationStructure e where   e.correction=True  and  e.parentId='"+parentId+"'");
		Query query = session.createQuery(jql.toString());
		return  query.list();
	}
	@Override
	protected Class<OrganizationStructure> getDomainClass() {
		return OrganizationStructure.class;
	}
	public OrganizationStructure loadByEntityId(long entityId) {
		return (OrganizationStructure) getSession().load(domainClass, entityId);
	}
}
