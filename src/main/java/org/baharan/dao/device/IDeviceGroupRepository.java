package org.baharan.dao.device;
import java.util.List;
import org.baharan.dao.IGenericRepository;
import org.baharan.model.device.DeviceGroup;
import org.baharan.model.location.Location;
import org.springframework.stereotype.Repository;
@Repository
public interface IDeviceGroupRepository extends IGenericRepository <DeviceGroup>{
	public List<DeviceGroup> getAll(int parentId);
	public DeviceGroup findByCode(String code);
}
