package org.azizkhani.model.location;


import java.util.Set;

import org.azizkhani.model.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location extends BaseEntity{
	
    private String code;
    private String name;
    private Location parent;
    private double latitude;
    private double longitude;
    private String description;
    
    private Set<Location> childs; 

	
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
	public Location getParent() {
		return parent;
	}
	@JsonProperty
	public void setParent(Location parent) {
		this.parent = parent;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonIgnore
	public Set<Location> getChilds() {
		return childs;
	}
	public void setChilds(Set<Location> childs) {
		this.childs = childs;
	} 
}
