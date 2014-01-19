package org.azizkhani.dao.device;
import java.util.List;

import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.device.DeviceGroup;
import org.azizkhani.model.location.Location;
import org.springframework.stereotype.Repository;
@Repository
public interface IDeviceGroupRepository extends IGenericRepository <DeviceGroup>{
	public List<DeviceGroup> getAll(int parentId);
	public DeviceGroup findByCode(String code);
}
