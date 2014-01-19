package org.azizkhani.dao.vehicle;

import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.device.Device;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.model.vehicle.VehicleDriver;

public interface IVehicleDriverRepository extends IGenericRepository<VehicleDriver>{
	public List<VehicleDriver> getAll(int vehicleId);

}
