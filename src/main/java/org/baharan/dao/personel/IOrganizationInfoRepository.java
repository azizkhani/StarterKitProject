package org.baharan.dao.personel;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.personel.OrganizationInfo;

public interface IOrganizationInfoRepository extends IGenericRepository<OrganizationInfo>{
	public OrganizationInfo getByPersonelCode(String personelCode);
}
