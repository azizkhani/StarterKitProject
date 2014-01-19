package org.azizkhani.service.personel;

import org.azizkhani.model.personel.OrganizationInfo;
import org.azizkhani.service.IGenericService;

public interface IOrganizationInfoService extends IGenericService<OrganizationInfo>{
	public OrganizationInfo getByPersonelCode(String personelCode);
}
