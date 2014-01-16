package org.baharan.dao.impl.hibernate.device;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.baharan.dao.device.IDeviceRepository;
import org.baharan.dao.impl.hibernate.GenericRepository;
import org.baharan.model.device.Device;
import org.baharan.model.device.DeviceGroup;
@Repository
public class DeviceRepository extends GenericRepository<Device>  implements IDeviceRepository {
 
	@Override
	protected Class<Device> getDomainClass() {
		// TODO Auto-generated method stub
		return Device.class;
	}

}
