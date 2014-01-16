package org.baharan.service.imp.device;

import java.util.Date;
import java.util.List;

import org.baharan.dao.IGenericRepository;
import org.baharan.dao.device.IDeviceGroupRepository;
import org.baharan.model.device.DeviceGroup;
import org.baharan.model.device.DeviceHistory;
import org.baharan.service.device.IDeviceGroupService;
import org.baharan.service.imp.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.baharan.service.location.ILocationService;

@Service
public class DeviceGroupService extends GenericService<DeviceGroup> implements IDeviceGroupService{

	@Autowired(required = true)
	private IDeviceGroupRepository deviceGroupRepository;
	
	@Override
	protected IGenericRepository<DeviceGroup> getGenericRepo() {
		return deviceGroupRepository;
	}

	@Override
	public List<DeviceGroup> getAll(int parentId) {
		return deviceGroupRepository.getAll(parentId);
	}

	@Override
	public DeviceGroup findByCode(String code) {
		return deviceGroupRepository.findByCode(code);
	}
	
	@Override
	public void save(DeviceGroup entity) {
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
