package org.baharan.dao.location;
import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.model.location.Location;
import org.springframework.stereotype.Repository;
@Repository
public interface ILocationRepository extends IGenericRepository<Location> {
	public List<Location> getAll(int parentId);
	public Location findByCode(String code);
}
