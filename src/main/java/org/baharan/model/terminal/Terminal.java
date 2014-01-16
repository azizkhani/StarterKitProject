package org.baharan.model.terminal;

import org.baharan.model.BaseEntity;
import org.baharan.model.location.Location;
import org.baharan.model.personel.Personel;

public class Terminal extends BaseEntity {
	
	private String code;
    private String name;
    private String latitude;
    private String longitude;
    private int lineTotal;
    private int vehicleCapacity;
    private String phoneNumber;
    private String address;
    private String description;
    
    private Location location;
    private Personel responsible;
    
    
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
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public int getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(int lineTotal) {
		this.lineTotal = lineTotal;
	}
	
	public int getVehicleCapacity() {
		return vehicleCapacity;
	}
	public void setVehicleCapacity(int vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Personel getResponsible() {
		return responsible;
	}
	public void setResponsible(Personel responsible) {
		this.responsible = responsible;
	}
    
    
}
