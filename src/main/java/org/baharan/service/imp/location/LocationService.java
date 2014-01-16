package org.baharan.service.imp.location;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.baharan.dao.IGenericRepository;
import org.baharan.dao.location.ILocationRepository;
import org.baharan.model.line.LineStation;
import org.baharan.model.location.Location;
import org.baharan.service.imp.GenericService;
import org.baharan.service.location.ILocationService;
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
