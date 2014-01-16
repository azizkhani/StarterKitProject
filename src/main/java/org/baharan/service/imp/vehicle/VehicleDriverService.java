package org.baharan.service.imp.vehicle;

import java.util.Date;
import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.vehicle.IVehicleDriverRepository;
import org.baharan.model.line.LineStation;
import org.baharan.model.vehicle.VehicleDriver;
import org.baharan.service.imp.GenericService;
import org.baharan.service.vehicle.IVehicleDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleDriverService extends GenericService<VehicleDriver> implements IVehicleDriverService{
	
	@Autowired(required = true)
	private IVehicleDriverRepository vehicleDriverRepository;

	@Override
	protected IGenericRepository<VehicleDriver> getGenericRepo() {
		return vehicleDriverRepository;
	}

	@Override
	public List<VehicleDriver> getAll(int vehicleId) {
		return vehicleDriverRepository.getAll(vehicleId);
	}
	
	
	@Override
	public void save(VehicleDriver entity) {
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
