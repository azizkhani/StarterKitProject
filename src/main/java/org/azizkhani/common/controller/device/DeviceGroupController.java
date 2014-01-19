package org.azizkhani.common.controller.device;
import org.azizkhani.core.QueryResult;
import org.azizkhani.model.device.DeviceGroup;
import org.azizkhani.model.location.Location;
import org.azizkhani.service.device.IDeviceGroupService;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/device/deviceGroup")
public class DeviceGroupController {
	
	@Autowired
	private IDeviceGroupService  deviceGroupService;
	
	private String returnValue="";
	private List<DeviceGroup> deviceGroup;
	
	@RequestMapping("/list")
	public @ResponseBody QueryResult<DeviceGroup> list(String searchFilter, String order, int pageNumber, int pageSize) {
		return deviceGroupService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/load/{id}")
	public @ResponseBody DeviceGroup load(@PathVariable int id) {
		return deviceGroupService.loadByEntityId(id);
	}
	
	@RequestMapping("/all/{parentId}")
	public @ResponseBody ResponseEntity<String> getAll(@PathVariable int parentId) {
		returnValue="";
		DeviceGroup loc=new DeviceGroup();
		loc.setId(deviceGroupService.loadByEntityId(parentId).getId());
		makeTree(loc);	
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	    return new ResponseEntity<String>(returnValue, responseHeaders, HttpStatus.CREATED); 
	}
	
	public void makeTree(DeviceGroup  deviceGroup)
	{
		for (DeviceGroup dg : deviceGroupService.getAll(deviceGroup.getId())) {
			returnValue=returnValue+"<item     text=\""+dg.getName()+"\" id=\""+dg.getId()+"\"    im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
			if (deviceGroupService.getAll(dg.getId()).size()>0) {
				 returnValue=returnValue+">"; 
				 returnValue=returnValue+"<item text=\"...\" im0=\"leaf.gif\" id=\"t"+dg.getId() +"\"/>";
				 returnValue=returnValue+"</item>";
			}
			else
				returnValue=returnValue+"/>";
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  int save(@RequestBody DeviceGroup dg) {
		if (dg.getId() > 0) {
			dg.setUpdatedDate(new Date());
			DeviceGroup lo =new DeviceGroup();
			lo=deviceGroupService.loadByEntityId(dg.getId());
			dg.setParent(lo.getParent());
			deviceGroupService.update(dg);
			return dg.getId();
		} else {
			dg.setCreatedDate(new Date());
			dg.setUpdatedDate(new Date());
			deviceGroupService.add(dg);
			return dg.getId();
		}
	 
	}
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		deviceGroupService.deleteByEntityId(Id);
		return true;
		
	}
	
	@RequestMapping(value="/find/code/{code}",method = RequestMethod.GET)
	@ResponseBody
	public  DeviceGroup load(@PathVariable String code) {
		return deviceGroupService.findByCode(code);
	}
}
