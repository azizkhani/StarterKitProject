package org.baharan.model.vehicle;

import org.baharan.model.BaseEntity;
import org.baharan.model.personel.Personel;

public class VehicleDriver extends BaseEntity {

	private Vehicle vehicle;
	private Personel driver;
	private String assignDate;
	private String description;
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Personel getDriver() {
		return driver;
	}
	public void setDriver(Personel driver) {
		this.driver = driver;
	}
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
