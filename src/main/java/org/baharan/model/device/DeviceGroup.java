package org.baharan.model.device;

import java.util.List;
import java.util.Set;

import org.baharan.model.BaseEntity;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


public class DeviceGroup extends BaseEntity{
	
	private String code;
	private String name;
    private DeviceGroup parent;
    private String description;
    
    private Set<DeviceGroup> childs;

    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public DeviceGroup getParent() {
		return parent;
	}
	@JsonProperty
	public void setParent(DeviceGroup parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@JsonIgnore
	public Set<DeviceGroup> getChilds() {
		return childs;
	}

	public void setChilds(Set<DeviceGroup> childs) {
		this.childs = childs;
	}
    
    
    
}
