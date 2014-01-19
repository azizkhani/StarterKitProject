package org.azizkhani.service.vehicle;

import java.util.List;

import org.azizkhani.model.device.Device;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.model.vehicle.Vehicle;
import org.azizkhani.service.IGenericService;

public interface IVehicleService extends IGenericService<Vehicle> {
	public List<DeviceHistory> getDevices(int vehicleId);
	public Boolean removeLine(int vehicleId,int lineId);
	public Boolean addLine(int vehicleId,int lineId);
}
