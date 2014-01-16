package org.baharan.dao.device;

import org.baharan.core.QueryResult;
import org.baharan.dao.IGenericRepository;
import org.baharan.model.device.DeviceHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceHistoryRepository extends IGenericRepository<DeviceHistory> {
	
	public QueryResult<DeviceHistory> getAllGrid(int deviceId,String where, String order, int pageNumber,int pageSize);
	public void setAllDeviceHistoryStatusFalse(int deviceId);
}
