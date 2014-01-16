package org.baharan.service.device;

import org.baharan.core.QueryResult;
import org.baharan.model.device.DeviceHistory;
import org.baharan.service.IGenericService;

public interface IDeviceHistoryService extends IGenericService<DeviceHistory>{
	public QueryResult<DeviceHistory> getAllGrid(int deviceId,String where, String order, int pageNumber,int pageSize);
}
