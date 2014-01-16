package org.baharan.service.personel;

import org.baharan.model.personel.OrganizationInfo;
import org.baharan.service.IGenericService;

public interface IOrganizationInfoService extends IGenericService<OrganizationInfo>{
	public OrganizationInfo getByPersonelCode(String personelCode);
}
