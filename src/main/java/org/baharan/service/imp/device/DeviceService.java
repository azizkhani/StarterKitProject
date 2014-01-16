package org.baharan.service.imp.device;
import java.util.Date;
import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.device.IDeviceRepository;
import org.baharan.model.device.Device;
import org.baharan.model.device.DeviceGroup;
import org.baharan.service.device.IDeviceService;
import org.baharan.service.imp.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService extends GenericService<Device> implements IDeviceService {
	@Autowired(required = true)
	private IDeviceRepository deviceRepository;

	@Override
	protected IGenericRepository<Device> getGenericRepo() {
		// TODO Auto-generated method stub
		return deviceRepository;
	}
	
	@Override
	public void save(Device entity) {
		if (entity.getId() > 0) {
			entity.setUpdatedDate(new Date());
			this.update(entity);
		} else {
			entity.setCreatedDate(new Date());
			entity.setUpdatedDate(new Date());
			this.add(entity);
		}
	}
}
