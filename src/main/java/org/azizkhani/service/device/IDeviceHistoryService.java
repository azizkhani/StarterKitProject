package org.azizkhani.service.device;

import org.azizkhani.core.QueryResult;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.service.IGenericService;

public interface IDeviceHistoryService extends IGenericService<DeviceHistory>{
	public QueryResult<DeviceHistory> getAllGrid(int deviceId,String where, String order, int pageNumber,int pageSize);
}
