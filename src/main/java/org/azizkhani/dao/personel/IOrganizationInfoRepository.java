package org.azizkhani.dao.personel;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.personel.OrganizationInfo;

public interface IOrganizationInfoRepository extends IGenericRepository<OrganizationInfo>{
	public OrganizationInfo getByPersonelCode(String personelCode);
}
