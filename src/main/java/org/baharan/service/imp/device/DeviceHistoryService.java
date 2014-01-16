package org.baharan.service.imp.device;

import java.util.Date;

import org.baharan.core.QueryResult;
import org.baharan.dao.IGenericRepository;
import org.baharan.dao.device.IDeviceHistoryRepository;
import org.baharan.dao.device.IDeviceRepository;
import org.baharan.model.device.DeviceHistory;
import org.baharan.service.device.IDeviceHistoryService;
import org.baharan.service.imp.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DeviceHistoryService extends GenericService<DeviceHistory> implements IDeviceHistoryService {
	
	@Autowired(required = true)
	private IDeviceHistoryRepository deviceHistoryRepository;

	@Override
	protected IGenericRepository<DeviceHistory> getGenericRepo() {
		return deviceHistoryRepository;
	}

	@Override
	public QueryResult<DeviceHistory> getAllGrid(int deviceId, String where,String order, int pageNumber, int pageSize) {
		return deviceHistoryRepository.getAllGrid(deviceId, where, order, pageNumber, pageSize);
	}
	
	@Override
	public void save(DeviceHistory entity) {
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
