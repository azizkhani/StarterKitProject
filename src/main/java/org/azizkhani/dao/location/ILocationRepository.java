package org.azizkhani.dao.location;
import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.location.Location;
import org.springframework.stereotype.Repository;
@Repository
public interface ILocationRepository extends IGenericRepository<Location> {
	public List<Location> getAll(int parentId);
	public Location findByCode(String code);
}
