package org.baharan.service.vehicle;

import java.util.List;

import org.baharan.model.device.Device;
import org.baharan.model.device.DeviceHistory;
import org.baharan.model.vehicle.Vehicle;
import org.baharan.service.IGenericService;

public interface IVehicleService extends IGenericService<Vehicle> {
	public List<DeviceHistory> getDevices(int vehicleId);
	public Boolean removeLine(int vehicleId,int lineId);
	public Boolean addLine(int vehicleId,int lineId);
}
