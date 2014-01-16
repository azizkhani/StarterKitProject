package org.baharan.dao.vehicle;

import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.device.Device;
import org.baharan.model.device.DeviceHistory;
import org.baharan.model.vehicle.VehicleDriver;

public interface IVehicleDriverRepository extends IGenericRepository<VehicleDriver>{
	public List<VehicleDriver> getAll(int vehicleId);

}
