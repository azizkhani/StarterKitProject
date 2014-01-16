package org.baharan.dao.impl.hibernate.personel;

import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.dao.personel.IOrganizationInfoRepository;
import org.baharan.model.personel.OrganizationInfo;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizationInfoRepository extends GenericRepository<OrganizationInfo> implements IOrganizationInfoRepository{

	@Override
	protected Class<OrganizationInfo> getDomainClass() {
		return OrganizationInfo.class;
	}

	@Override
	public OrganizationInfo getByPersonelCode(String personelCode) {
		String selectQuery="from OrganizationInfo where personCode='"+personelCode+"'";
		Query query = getSession().createQuery(selectQuery);
		return (OrganizationInfo) query.uniqueResult();
	}
}
