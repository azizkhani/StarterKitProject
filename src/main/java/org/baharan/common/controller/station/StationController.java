package org.baharan.common.controller.station;

import org.baharan.core.QueryResult;
import org.baharan.model.station.Station;
import org.baharan.model.terminal.Terminal;
import org.baharan.service.station.IStationService;
import org.baharan.service.terminal.ITerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/baseinfo/station")
public class StationController {
	
	
	@Autowired(required=true)
	private IStationService stationService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<Station> list(String searchFilter, String order, int pageNumber, int pageSize) {
		return stationService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  Station load(@PathVariable int Id) {
		return stationService.loadByEntityId(Id);
	}
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		stationService.deleteByEntityId(Id);
		return true;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean save(@RequestBody Station entity) {		
			stationService.save(entity);
		return true;
	}
}
