package org.azizkhani.dao.impl.hibernate.device;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.azizkhani.dao.device.IDeviceRepository;
import org.azizkhani.dao.impl.hibernate.GenericRepository;
import org.azizkhani.model.device.Device;
import org.azizkhani.model.device.DeviceGroup;
@Repository
public class DeviceRepository extends GenericRepository<Device>  implements IDeviceRepository {
 
	@Override
	protected Class<Device> getDomainClass() {
		// TODO Auto-generated method stub
		return Device.class;
	}

}
