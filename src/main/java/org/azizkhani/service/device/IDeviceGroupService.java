package org.azizkhani.service.device;
import org.azizkhani.model.device.DeviceGroup;
import org.azizkhani.model.location.Location;
import org.azizkhani.service.IGenericService;

import java.util.List;
public interface IDeviceGroupService  extends IGenericService<DeviceGroup>{
	public List<DeviceGroup> getAll(int parentId);
	public DeviceGroup findByCode(String code);
}
