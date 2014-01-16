package org.baharan.model.security;

import java.util.Set;

import org.baharan.model.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Action extends BaseEntity {
	private String actionName;
	private Action parent;
	private Set<Action> childs;
	private Set<Group> groups;
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Action getParent() {
		return parent;
	}
	public void setParent(Action parent) {
		this.parent = parent;
	}
	@JsonIgnore
	public Set<Action> getChilds() {
		return childs;
	}
	public void setChilds(Set<Action> childs) {
		this.childs = childs;
	}
	@JsonIgnore
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
