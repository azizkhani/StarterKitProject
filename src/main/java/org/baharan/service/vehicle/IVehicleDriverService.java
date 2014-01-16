package org.baharan.service.vehicle;

import java.util.List;

import org.baharan.model.vehicle.VehicleDriver;
import org.baharan.service.IGenericService;

public interface IVehicleDriverService extends IGenericService<VehicleDriver> {
	public List<VehicleDriver> getAll(int vehicleId);
}
