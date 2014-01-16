package org.baharan.common.controller.vehicle;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.baharan.model.line.LineStation;
import org.baharan.model.vehicle.VehicleDriver;
import org.baharan.service.line.ILineStationService;
import org.baharan.service.vehicle.IVehicleDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baseinfo/vehicleDriver")
public class VehicleDriverController {
	
	@Autowired(required = true)
	private IVehicleDriverService lineStationService;

	@RequestMapping("/list/{lineId}")
	@ResponseBody
	public List<VehicleDriver> list(@PathVariable int lineId) {

		return lineStationService.getAll(lineId);

	}

	@RequestMapping("/load/{Id}")
	@ResponseBody
	public VehicleDriver load(@PathVariable int Id) {
		return lineStationService.loadByEntityId(Id);
	}

	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean delete(@PathVariable int Id) {
		lineStationService.deleteByEntityId(Id);
		return true;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Boolean save(@RequestBody VehicleDriver entity) {
		lineStationService.save(entity);
		return true;
	}
}
