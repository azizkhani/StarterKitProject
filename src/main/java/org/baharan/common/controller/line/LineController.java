package org.baharan.common.controller.line;

import org.baharan.core.QueryResult;
import org.baharan.model.line.Line;
import org.baharan.service.line.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baseinfo/line")
public class LineController {
	
	@Autowired(required=true)
	private ILineService lineService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<Line> list(String searchFilter, String order, int pageNumber, int pageSize) {
		return lineService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  Line load(@PathVariable int Id) {
		return lineService.loadByEntityId(Id);
	}
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		lineService.deleteByEntityId(Id);
		return true;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean save(@RequestBody Line entity) {		
		lineService.save(entity);
		return true;
	}
	
}
