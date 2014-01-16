package org.baharan.common.controller.location;


import org.baharan.core.QueryResult;
import org.baharan.model.location.Location;
import org.baharan.model.security.Action;
import org.baharan.service.location.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	private ILocationService locationService;
	private String returnValue="";
		
	
	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<Location> list(String searchFilter, String order, int pageNumber, int pageSize) {
		return locationService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/load/{id}")
	@ResponseBody
	public  Location load(@PathVariable int id) {
		return locationService.loadByEntityId(id);
	}
	
	@RequestMapping(value="/find/code/{code}",method = RequestMethod.GET)
	@ResponseBody
	public  Location load(@PathVariable String code) {
		return locationService.findByCode(code);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  int save(@RequestBody Location loc) {
		locationService.save(loc);
		return loc.getId();
	}
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		locationService.deleteByEntityId(Id);
		return true;
		
	}
	
	@RequestMapping("/all/{parentId}")
	@ResponseBody
	public  ResponseEntity<String> getAll(@PathVariable int parentId) {
		returnValue="";
		Location loc=new Location();
		loc.setId(locationService.loadByEntityId(parentId).getId());
		makeTree(loc);	
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	    return new ResponseEntity<String>(returnValue, responseHeaders, HttpStatus.CREATED); 
	}
	
	public void makeTree(Location  location)
	{
		for (Location loc : locationService.getAll(location.getId())) {
			returnValue=returnValue+"<item     text=\""+loc.getName()+"\" id=\""+loc.getId()+"\"    im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
			if (locationService.getAll(loc.getId()).size()>0) { 
				 returnValue=returnValue+">"; 
				 returnValue=returnValue+"<item text=\"...\" im0=\"leaf.gif\" id=\"t"+loc.getId() +"\"/>";
				 returnValue=returnValue+"</item>";
			}
			else
				returnValue=returnValue+"/>";
		}
	}
	
	@RequestMapping("/tree/{parentId}")
	@ResponseBody
	public  ResponseEntity<String> list() 
	{
		returnValue ="<?xml version='1.0' encoding=\"utf-8\"?>";
		returnValue = returnValue + "<tree id=\"0\">";	
		Location act= locationService.loadByEntityId(1);
		returnValue=returnValue+"<item text=\""+act.getName()+"\" id=\""+act.getId()+"\" open=\"1\" im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
		if (act.getChilds().size()==0) 
			returnValue=returnValue+"/>";
		else
		{
			returnValue=returnValue+">";
			makeEagTree(act);
			returnValue=returnValue+"</item>";	
		}
		returnValue	= returnValue+"</tree> ";
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	    return new ResponseEntity<String>(returnValue, responseHeaders, HttpStatus.CREATED);
	}
	public void makeEagTree(Location  loc)
	{
		for (Location act : loc.getChilds()) {
			returnValue=returnValue+"<item   text=\""+act.getName()+"\" id=\""+act.getId()+"\" open=\"1\" im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
			if (act.getChilds().size()==0 )
				returnValue=returnValue+"/>";
			else
			{
				returnValue=returnValue+">";
				makeTree(act);
				returnValue=returnValue+"</item>";
			}
		}
	}
}
