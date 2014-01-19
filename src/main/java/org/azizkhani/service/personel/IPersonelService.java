package org.azizkhani.service.personel;

import java.util.List;

import org.azizkhani.model.personel.Personel;
import org.azizkhani.service.IGenericService;

public interface IPersonelService extends IGenericService<Personel>{
	public Personel findByPersonelCode(String personelCode);
	public boolean validatePersonel(int personelId,String personelCode);
	public List<Personel> find(String personelCode,String lastName,int resultCount);
}
