package org.azizkhani.service.vehicle;

import java.util.List;

import org.azizkhani.model.vehicle.VehicleDriver;
import org.azizkhani.service.IGenericService;

public interface IVehicleDriverService extends IGenericService<VehicleDriver> {
	public List<VehicleDriver> getAll(int vehicleId);
}
