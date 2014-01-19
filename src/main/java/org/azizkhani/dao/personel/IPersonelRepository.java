package org.azizkhani.dao.personel;

import java.util.List;
import java.util.Set;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.organization.OrganizationStructure;
import org.azizkhani.model.personel.Personel;
import org.springframework.security.access.annotation.Secured;

public interface IPersonelRepository extends IGenericRepository<Personel> {
	public Personel findByPersonelCode(String personelCode);
	public boolean validatePersonel(int personelId,String personelCode);
	public List<Personel> find(String personelCode,String lastName,int resultCount);
	public List<Personel> find(String personelCode,String lastName,int resultCount,Set<OrganizationStructure> orgLimitation);
	
}
