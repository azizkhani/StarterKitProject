package org.azizkhani.service.imp.vehicle;

import java.util.Date;
import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.dao.vehicle.IVehicleDriverRepository;
import org.azizkhani.model.line.LineStation;
import org.azizkhani.model.vehicle.VehicleDriver;
import org.azizkhani.service.imp.GenericService;
import org.azizkhani.service.vehicle.IVehicleDriverService;
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
