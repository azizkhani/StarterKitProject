package org.azizkhani.service.imp.vehicle;

import java.util.Date;
import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.dao.vehicle.IVehicleRepository;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.model.line.LineStation;
import org.azizkhani.model.vehicle.Vehicle;
import org.azizkhani.service.imp.GenericService;
import org.azizkhani.service.vehicle.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService extends GenericService<Vehicle> implements IVehicleService{
	
	@Autowired(required = true)
	private IVehicleRepository vehicleRepository;

	@Override
	protected IGenericRepository<Vehicle> getGenericRepo() {
		return vehicleRepository;
	}

	@Override
	public List<DeviceHistory> getDevices(int vehicleId) {
		return vehicleRepository.getDevices(vehicleId);
	}

	@Override
	public Boolean removeLine(int vehicleId, int lineId) {
		return vehicleRepository.removeLine(vehicleId, lineId);
	}

	@Override
	public Boolean addLine(int vehicleId, int lineId) {
		return vehicleRepository.addLine(vehicleId, lineId);
	}
	
	@Override
	public void save(Vehicle entity) {
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
