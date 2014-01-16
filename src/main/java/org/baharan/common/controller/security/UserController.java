package org.baharan.common.controller.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.baharan.core.QueryResult;
import org.baharan.model.location.Location;
import org.baharan.model.security.Group;
import org.baharan.model.security.User;
import org.baharan.service.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller 
@RequestMapping("/security/user")
public class UserController {

	@Autowired(required=true)
	private IUserService userService;
	
	@RequestMapping("/list")
	@ResponseBody
	public QueryResult<User> list(String searchFilter, String order, int pageNumber,
			int pageSize) {
		return userService.getAllGrid(searchFilter, order, pageNumber, pageSize);
	}

	@RequestMapping("/load/{Id}")
	@ResponseBody
	public User load(@PathVariable int Id) {
		return userService.loadByEntityId(Id);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleUncaughtException(Exception ex, WebRequest request,
			HttpServletResponse response) throws IOException {
		if (isAjaxRequest(request)) {
			response.setHeader("Content-Type", "application/json");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "Unknown error occurred: " + ex.getMessage();
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					ex.getMessage());
			return null;
		}
	}

	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith)
				: false;
	}
	@RequestMapping("/authenitacedUser")
	@ResponseBody
	public User getAuthenitacedUser() {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	@RequestMapping("/delete/{Id}")
	@ResponseBody
	public Boolean delete(@PathVariable int Id) {
		userService.deleteByEntityId(Id);
		return true;
	}

	@RequestMapping("/checkUserName/{userName}")
	@ResponseBody
	public 	Boolean userNameExistance(@PathVariable String userName) {
		return userService.checkUserNameExistance(userName);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Boolean save(@RequestBody User user) {
		
		if (user.getId() > 0) {
			user.setUpdatedDate(new Date());
			userService.update(user);
		} else {
			user.setCreatedDate(new Date());
			user.setUpdatedDate(new Date());
			user.setIsEnabled(true);
			userService.add(user);
		}
		return true;
		
	}
 
	@RequestMapping(value = "/saveLocations/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveLocations(@PathVariable int userId,@RequestBody MyLocationList locations  ) {
		User u=userService.loadByEntityId(userId);
		u.getLocations().clear();
		Set<Location> locationsSet=new HashSet<Location>(locations);
		for (Iterator iterator = locations.iterator(); iterator.hasNext();) {
			Location loc = (Location) iterator.next();
			u.getLocations().add(loc);
		}
		userService.update(u);
		return true;
	}
	
	@RequestMapping("/saveGroups/{userId}")
	@ResponseBody
	public Boolean saveGroups(@PathVariable int userId,@RequestBody MyGroupList  groups) {
		User u=userService.loadByEntityId(userId);
		Set<Group> setGroups=new HashSet<Group>(groups);
		u.setGroups(setGroups);
//		for (Iterator iterator = groups.iterator(); iterator.hasNext();) {
//			Group group = (Group) iterator.next();
//			u.getGroups().add(group);
//		}
		userService.update(u);
		return true;
	}
	static class MyGroupList extends ArrayList<Group> { }
	static class MyLocationList extends ArrayList<Location> { }
}
