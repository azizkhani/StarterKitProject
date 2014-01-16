package org.baharan.common.controller.device;

import java.util.Date;
import java.util.List;

import org.baharan.core.QueryResult;
import org.baharan.model.device.Device;
import org.baharan.service.device.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/device")
public class DeviceController {
		
	@Autowired(required=true)
	IDeviceService deviceService;
	
	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<Device> list(String searchFilter, String order, int pageNumber, int pageSize) {
		return deviceService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  Device load(@PathVariable int Id) {
		return deviceService.loadByEntityId(Id);
	}
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		deviceService.deleteByEntityId(Id);
		return true;
		
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  int saveJson(@RequestBody Device ind) {
		if (ind.getId() > 0) {
			ind.setUpdatedDate(new Date());
			deviceService.update(ind);
			return ind.getId();
		} else {
			ind.setCreatedDate(new Date());
			ind.setUpdatedDate(new Date());
			deviceService.add(ind);
			return ind.getId();
		}
	 
	}
	

	
	@RequestMapping("/getAll")
	public @ResponseBody List<Device> list() {
		return deviceService.getAll();

	}
	
}
