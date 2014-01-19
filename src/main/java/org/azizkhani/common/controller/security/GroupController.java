package org.azizkhani.common.controller.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.azizkhani.core.QueryResult;
import org.azizkhani.dao.security.IGroupRepository;
import org.azizkhani.model.security.Action;
import org.azizkhani.model.security.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/security/group")
public class GroupController {
	
	@Autowired(required=true)
	private IGroupRepository groupRepo;
	@RequestMapping("/list")
	public @ResponseBody QueryResult<Group> list(String searchFilter,String order,int pageNumber, int pageSize) {
		return new QueryResult<Group>(pageNumber, groupRepo.count(),  pageSize, groupRepo.getAll(searchFilter, order, pageNumber,pageSize));
	}
	@RequestMapping("/listAll")
	public @ResponseBody List<Group> list() {
		return groupRepo.getAll();
	}
	@RequestMapping("/load/{Id}")
	public @ResponseBody Group load(@PathVariable int Id) {
		return groupRepo.loadByEntityId(Id);
	}
	@RequestMapping("/delete/{Id}")
	public @ResponseBody Boolean delete(@PathVariable int Id) {
		groupRepo.deleteByEntityId(Id);
		return true;
	}
	@RequestMapping("/save")
	public @ResponseBody Boolean save(@ModelAttribute("group") Group group, BindingResult result,SessionStatus status) {
		if (result.hasErrors()) {
			return false;
		} else {
			group.setUpdatedDate(new Date());
			group.setCreatedDate(new Date());
			status.setComplete();
			if (group.getId() > 0) 
				groupRepo.update(group);
			 else 
				groupRepo.add(group);
			return true;
		}
	}
	
	
	
	@RequestMapping("/loadActionsXmlForTree/{groupId}")
	@ResponseBody
	public  Set<Action>  loadActions(@PathVariable int groupId) {
		return groupRepo.loadByEntityId(groupId).getActions();
	}
	
	
	
	@RequestMapping(value = "/saveGroupActions/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveGroupActions(@PathVariable int groupId,@RequestBody MyActionList actions) {
		Group group=groupRepo.loadByEntityId(groupId);
		group.getActions().clear();
		for (Iterator iterator = actions.iterator(); iterator.hasNext();) {
			Action action = (Action) iterator.next();
			group.getActions().add(action);
		}
		groupRepo.update(group);
		return true;
	}
	static class MyActionList extends ArrayList<Action> { }
}
