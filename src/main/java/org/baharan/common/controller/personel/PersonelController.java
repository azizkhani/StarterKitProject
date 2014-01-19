package org.baharan.common.controller.personel;

import java.util.List;

import org.baharan.core.QueryResult;
import org.baharan.model.personel.Personel;
import org.baharan.service.personel.IPersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/personel")
public class PersonelController {
	
	@Autowired(required=true)
	private IPersonelService personelService;

	@RequestMapping("/list")
	@ResponseBody
	public  QueryResult<Personel> list(String searchFilter, String order, int pageNumber, int pageSize) {
		return personelService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}
	
	@RequestMapping("/autoComplete")
	@ResponseBody
	public  List<Personel> list(String search,int resultCount) {
		String personelCode="";
		String lastName="";
		if(!search.isEmpty())
		{
			 try  
		     {  
		        Integer.parseInt(search); 
		        personelCode=search;    
		     } 
			 catch(NumberFormatException nfe)  
		     {  
				 lastName=search;   
		     }  
		}	
		List<Personel> lst=  personelService.find(personelCode, lastName, resultCount);
		return lst;
	}
	
	@RequestMapping("/load/{Id}")
	@ResponseBody
	public  Personel load(@PathVariable int Id) {
		return personelService.loadByEntityId(Id);
	}
	
	@RequestMapping("/validatePersonel/{personelId}/{personelCode}")
	@ResponseBody
	public  Boolean validateByPersonCode(@PathVariable int personelId,@PathVariable String personelCode) {
		return  personelService.validatePersonel(personelId, personelCode);
	}
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public  Boolean delete(@PathVariable int Id) {
		personelService.deleteByEntityId(Id);
		return true;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public  Boolean save(@RequestBody Personel pers) {		
		personelService.save(pers);
		return true;
	}
	
	@RequestMapping("/load2/{Id}")
	@ResponseBody
	public  Personel load2(@PathVariable int Id) {
		return (Personel)personelService.loadByEntityId(Id);
	}
}
