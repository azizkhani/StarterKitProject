package org.azizkhani.common.controller.vehicle;

import java.util.List;
import java.util.Set;

import org.azizkhani.core.QueryResult;
import org.azizkhani.model.device.DeviceHistory;
import org.azizkhani.model.line.Line;
import org.azizkhani.model.vehicle.Vehicle;
import org.azizkhani.service.vehicle.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baseinfo/vehicle")
public class VehicleController {
	
	@Autowired(required=true)
	private IVehicleService vehicleService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<Vehicle> list(String searchFilter, String order, Integer pageNumber, Integer pageSize) {
		return vehicleService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/vehicleDevice/list/{vehicleId}")
	@ResponseBody
	public  List<DeviceHistory> deviceList(@PathVariable int vehicleId) {
		return vehicleService.getDevices(vehicleId);
	}
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  Vehicle load(@PathVariable int Id) {
		return vehicleService.loadByEntityId(Id);
	}
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		vehicleService.deleteByEntityId(Id);
		return true;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean save(@RequestBody Vehicle entity) {		
		vehicleService.save(entity);
		return true;
	}
	
	@RequestMapping(value = "/addLine/{vehicleId}/{lineId}", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean addLine(@PathVariable int vehicleId,@PathVariable int lineId) {	
		return vehicleService.addLine(vehicleId, lineId);
	}
	
	@RequestMapping(value = "/removeLine/{vehicleId}/{lineId}", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean removeLine(@PathVariable int vehicleId,@PathVariable int lineId) {		
		return vehicleService.removeLine(vehicleId, lineId);
	}
	@RequestMapping("/lineList/{vehicleId}")
	@ResponseBody
	public  Set<Line> lineList(@PathVariable int vehicleId) {
		return vehicleService.loadByEntityId(vehicleId).getLines();
	}
}
