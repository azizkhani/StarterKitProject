package org.azizkhani.dao.vehicle;

import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.model.vehicle.Vehicle;

public interface IVehicleRepository extends IGenericRepository<Vehicle>{
	public List<DeviceHistory> getDevices(int vehicleId); 
	public Boolean removeLine(int vehicleId,int lineId);
	public Boolean addLine(int vehicleId,int lineId);

}
