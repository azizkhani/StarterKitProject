package org.baharan.common.controller.device;

import java.util.List;

import org.baharan.core.QueryResult;
import org.baharan.model.device.DeviceHistory;
import org.baharan.service.device.IDeviceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/device/deviceHistory")
public class DeviceHistoryController {
	
	@Autowired(required=true)
	IDeviceHistoryService  deviceHistoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<DeviceHistory> list(int deviceId,String searchFilter, String order, int pageNumber, int pageSize) {
		return deviceHistoryService.getAllGrid(deviceId,searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  DeviceHistory load(@PathVariable int Id) {
		return deviceHistoryService.loadByEntityId(Id);
	}
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		deviceHistoryService.deleteByEntityId(Id);
		return true;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  int save(@RequestBody DeviceHistory entity) {
		deviceHistoryService.save(entity);
		return entity.getId();
	}
	
}
