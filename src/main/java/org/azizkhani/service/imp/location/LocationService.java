package org.azizkhani.service.imp.location;

import java.util.Date;
import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.dao.location.ILocationRepository;
import org.azizkhani.model.location.Location;
import org.azizkhani.service.imp.GenericService;
import org.azizkhani.service.location.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LocationService extends GenericService<Location> implements ILocationService {
	
	@Autowired(required = true)
	private ILocationRepository locationRepo;

	@Override
	protected IGenericRepository<Location> getGenericRepo() {
		return locationRepo;
	}

	@Override
	public List<Location> getAll(int parentId) {
		 return locationRepo.getAll(parentId);
	}

	@Override
	public Location findByCode(String code) {
		return locationRepo.findByCode(code);
	}
	
	@Override
	public void save(Location entity) {
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
