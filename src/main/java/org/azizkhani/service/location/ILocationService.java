package org.azizkhani.service.location;

import java.util.List;

import org.azizkhani.model.location.Location;
import org.azizkhani.service.IGenericService;


public interface ILocationService extends IGenericService<Location> {
	public List<Location> getAll(int parentId);
	public Location findByCode(String code);
}
