package org.azizkhani.model.packet;

import java.util.Date;

import org.azizkhani.model.device.Device;
import org.azizkhani.model.line.Line;
import org.azizkhani.model.location.Location;
import org.azizkhani.model.personel.Personel;
import org.azizkhani.model.vehicle.Vehicle;

public class Passenger {
	
	private Long id;
	private String actionDate;//
	private String actionTime;
	private Long amount;
	private Long cardSerial;
	private Long reducerTurnRow;
	private Line line;
	private Personel driver;
	private Vehicle bus;
	private Device device;
	private Location location;
	private Date createdDate;
	private Date updatedDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActionDate() {
		return actionDate;
	}
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Personel getDriver() {
		return driver;
	}
	public void setDriver(Personel driver) {
		this.driver = driver;
	}
	public Vehicle getBus() {
		return bus;
	}
	public void setBus(Vehicle bus) {
		this.bus = bus;
	}
	public Long getCardSerial() {
		return cardSerial;
	}
	public void setCardSerial(Long cardSerial) {
		this.cardSerial = cardSerial;
	}
	public Long getReducerTurnRow() {
		return reducerTurnRow;
	}
	public void setReducerTurnRow(Long reducerTurnRow) {
		this.reducerTurnRow = reducerTurnRow;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
