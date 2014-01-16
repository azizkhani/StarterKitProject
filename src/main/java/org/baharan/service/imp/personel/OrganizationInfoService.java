package org.baharan.service.imp.personel;

import java.util.Date;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.personel.IOrganizationInfoRepository;
import org.baharan.model.line.LineStation;
import org.baharan.model.personel.OrganizationInfo;
import org.baharan.service.imp.GenericService;
import org.baharan.service.personel.IOrganizationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationInfoService extends GenericService<OrganizationInfo> implements IOrganizationInfoService{

	@Autowired(required = true)
	private IOrganizationInfoRepository organizationInfoRepo;
	
	@Override
	protected IGenericRepository<OrganizationInfo> getGenericRepo() {
		return organizationInfoRepo;
	}

	@Override
	public OrganizationInfo getByPersonelCode(String personelCode) {
		return organizationInfoRepo.getByPersonelCode(personelCode);
	}
	
	@Override
	public void save(OrganizationInfo entity) {
		if (entity.getId() > 0) {
			entity.setUpdatedDate(new Date());
			this.update(entity);
		} else {
			entity.setCreatedDate(new Date());
			entity.setUpdatedDate(new Date());
			this.add(entity);
		}
	}

}
