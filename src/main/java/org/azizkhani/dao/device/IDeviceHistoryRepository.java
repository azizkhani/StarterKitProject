package org.azizkhani.dao.device;

import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.IGenericRepository;
import org.azizkhani.model.device.DeviceHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceHistoryRepository extends IGenericRepository<DeviceHistory> {
	
	public QueryResult<DeviceHistory> getAllGrid(int deviceId,String where, String order, int pageNumber,int pageSize);
	public void setAllDeviceHistoryStatusFalse(int deviceId);
}
