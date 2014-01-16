package org.baharan.service.location;

import java.util.List;

import org.baharan.model.location.Location;
import org.baharan.service.IGenericService;


public interface ILocationService extends IGenericService<Location> {
	public List<Location> getAll(int parentId);
	public Location findByCode(String code);
}
