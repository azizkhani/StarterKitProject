package org.azizkhani.service.imp.device;

import java.util.Date;

import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.dao.device.IDeviceHistoryRepository;
import org.azizkhani.dao.device.IDeviceRepository;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.service.device.IDeviceHistoryService;
import org.azizkhani.service.imp.GenericService;
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
