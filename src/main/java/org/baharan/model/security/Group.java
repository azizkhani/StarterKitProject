package org.baharan.model.security;

import java.util.HashSet;
import java.util.Set;

import org.baharan.model.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Group extends BaseEntity{
	private String groupName;
	private Set<User> users = new HashSet<User>(0);
	private Set<Action> actions = new HashSet<Action>(0);
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName; 
	}
	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@JsonIgnore
	public Set<Action> getActions() {
		return actions;
	}
	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}
	
}