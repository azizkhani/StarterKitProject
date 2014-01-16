package org.baharan.service.device;
import org.baharan.model.device.DeviceGroup;
import org.baharan.model.location.Location;
import org.baharan.service.IGenericService;
import java.util.List;
public interface IDeviceGroupService  extends IGenericService<DeviceGroup>{
	public List<DeviceGroup> getAll(int parentId);
	public DeviceGroup findByCode(String code);
}
