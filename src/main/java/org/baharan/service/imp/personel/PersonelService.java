package org.baharan.service.imp.personel;

import java.util.Date;
import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.personel.IPersonelRepository;
import org.baharan.model.line.LineStation;
import org.baharan.model.personel.Personel;
import org.baharan.service.imp.GenericService;
import org.baharan.service.personel.IPersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonelService extends GenericService<Personel> implements IPersonelService {
	
	@Autowired(required = true)
	@Qualifier("HibernatePersonelRepository")
	private IPersonelRepository personelRepo;


	@Override
	protected IGenericRepository<Personel> getGenericRepo() {
		return personelRepo;
	}
	@Override
	public void save(Personel entity) {
		if (entity.getId() > 0) {
			entity.setUpdatedDate(new Date());
			this.update(entity);
		} else {
			entity.setCreatedDate(new Date());
			entity.setUpdatedDate(new Date());
			this.add(entity);
		}
	}
	
	@Override
	public List<Personel> getAll(String where, String order, int pageNumber,int pageSize) {
		List<Personel> lst= super.getAll(where, order, pageNumber, pageSize);
		return lst;
	}
	@Override
	public Personel findByPersonelCode(String personelCode) {
		return personelRepo.findByPersonelCode(personelCode);
	}
	@Override
	public List<Personel> find(String personelCode, String lastName, int resultCount) {
		return personelRepo.find(personelCode, lastName, resultCount);
	}
	@Override
	public boolean validatePersonel(int personelId, String personelCode) {
		return personelRepo.validatePersonel(personelId, personelCode);
	}
}


