package org.baharan.dao.vehicle;

import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.device.DeviceHistory;
import org.baharan.model.vehicle.Vehicle;

public interface IVehicleRepository extends IGenericRepository<Vehicle>{
	public List<DeviceHistory> getDevices(int vehicleId); 
	public Boolean removeLine(int vehicleId,int lineId);
	public Boolean addLine(int vehicleId,int lineId);

}
