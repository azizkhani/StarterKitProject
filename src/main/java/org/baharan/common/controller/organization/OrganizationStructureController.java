package org.baharan.common.controller.organization;

import java.util.List;
import java.util.Set;

import org.baharan.core.QueryResult;
import org.baharan.dao.organization.IOrganizationStructureRepository;
import org.baharan.model.organization.OrganizationStructure;
import org.baharan.model.security.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/organization")
public class OrganizationStructureController {
	
	@Autowired
	private IOrganizationStructureRepository organizationRepo;

	private String returnValue="";
	private List<OrganizationStructure> organization;
	
	@RequestMapping("/list")
	public @ResponseBody QueryResult<OrganizationStructure> list(String searchFilter, String order, int pageNumber, int pageSize) {
		return organizationRepo.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}

	@RequestMapping("/load/{id}")
	public @ResponseBody OrganizationStructure load(@PathVariable int id) {
		return organizationRepo.loadByEntityId(id);
	}
	
	@RequestMapping("/all/{parentId}")
	public @ResponseBody ResponseEntity<String> getAll(@PathVariable long parentId) {
		returnValue="";
		OrganizationStructure org=new OrganizationStructure();
		org.setCode(organizationRepo.loadByEntityId(parentId).getCode());
		makeTree(org);	
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	    return new ResponseEntity<String>(returnValue, responseHeaders, HttpStatus.CREATED); 
	}
	public void makeTree(OrganizationStructure  organization)
	{
		for (OrganizationStructure org : organizationRepo.getAll(organization.getCode())) {
			returnValue=returnValue+"<item     text=\""+org.getTitle()+"\" id=\""+org.getId()+"\"    im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
			if (organizationRepo.getAll(org.getCode()).size()>0) {
				 returnValue=returnValue+">";
				 returnValue=returnValue+"<item text=\"...\" im0=\"leaf.gif\" id=\"t"+org.getId() +"\"/>";
				 returnValue=returnValue+"</item>";
			}
			else
				returnValue=returnValue+"/>";
		}
	}
}
