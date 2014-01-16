package org.baharan.common.controller.security;



import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.baharan.dao.security.*;
import org.baharan.model.security.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/security/action")
public class ActionController {
	
	@Autowired(required=true)
	private IActionRepository actionRepo;
	
	
	@Autowired(required=true)
	private IGroupRepository groupRepo;
	
	private String returnValue;
	private Set<Action> groupActions;
	
	@RequestMapping(value="/list")
	public @ResponseBody ResponseEntity<String> list() 
	{
		returnValue ="<?xml version='1.0' encoding=\"utf-8\"?>";
		returnValue = returnValue + "<tree id=\"0\">";	
		Action act= actionRepo.loadByEntityId(1);
		returnValue=returnValue+"<item text=\""+act.getActionName()+"\" id=\""+act.getId()+"\" open=\"1\" im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
		if (act.getChilds().size()==0) 
			returnValue=returnValue+"/>";
		else
		{
			returnValue=returnValue+">";
			makeTree(act);
			returnValue=returnValue+"</item>";	
		}
		returnValue	= returnValue+"</tree> ";
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	    return new ResponseEntity<String>(returnValue, responseHeaders, HttpStatus.CREATED);
	}
	
	private String Ischecked(Action act )
	{
		for (Action a : groupActions) {
		if (a.getId()==act.getId())	
			return "checked=\"1\"";
		}
			return "";
			
	}
	
	@RequestMapping(value="/getGroupActions/{groupId}")
	public @ResponseBody ResponseEntity<String> list(@PathVariable int groupId) 
	{
		groupActions=groupRepo.loadByEntityId(groupId).getActions();
		returnValue ="<?xml version='1.0' encoding=\"utf-8\"?>";
		returnValue = returnValue + "<tree id=\"0\">";	
		Action act= actionRepo.loadByEntityId(1);
		returnValue=returnValue+"<item  "+Ischecked(act)+"  text=\""+act.getActionName()+"\" id=\""+act.getId()+"\" open=\"1\" im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
		if (act.getChilds().size()==0) 
			returnValue=returnValue+"/>";
		else
		{
			returnValue=returnValue+">";
			makeTree(act);
			returnValue=returnValue+"</item>";	
		}
		returnValue	= returnValue+"</tree> ";
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	    return new ResponseEntity<String>(returnValue, responseHeaders, HttpStatus.CREATED);
	}
	
	
	public void makeTree(Action  action)
	{
		for (Action act : action.getChilds()) {
			returnValue=returnValue+"<item "+Ischecked(act)+ " text=\""+act.getActionName()+"\" id=\""+act.getId()+"\" open=\"1\" im0=\"tombs.gif\" im1=\"tombs.gif\" im2=\"iconSafe.gif\"";
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
