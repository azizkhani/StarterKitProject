package org.baharan.dao.personel;

import java.util.List;
import java.util.Set;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.organization.OrganizationStructure;
import org.baharan.model.personel.Personel;
import org.springframework.security.access.annotation.Secured;

public interface IPersonelRepository extends IGenericRepository<Personel> {
	public Personel findByPersonelCode(String personelCode);
	public boolean validatePersonel(int personelId,String personelCode);
	public List<Personel> find(String personelCode,String lastName,int resultCount);
	public List<Personel> find(String personelCode,String lastName,int resultCount,Set<OrganizationStructure> orgLimitation);
	
}
