package org.baharan.common.controller.terminal;

import org.baharan.core.QueryResult;
import org.baharan.model.terminal.Terminal;
import org.baharan.service.terminal.ITerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/baseinfo/terminal")
public class TerminalController {

	@Autowired(required=true)
	private ITerminalService terminalService;
	
	
	
	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<Terminal> list(String searchFilter, String order, Integer pageNumber, Integer pageSize) {
		return terminalService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  Terminal load(@PathVariable int Id) {
		return terminalService.loadByEntityId(Id);
	}
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		terminalService.deleteByEntityId(Id);
		return true;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean save(@RequestBody Terminal pers) {		
			terminalService.save(pers);
		return true;
	}
	
}
