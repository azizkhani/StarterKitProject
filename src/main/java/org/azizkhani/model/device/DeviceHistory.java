package org.azizkhani.model.device;

import org.azizkhani.model.BaseEntity;
import org.azizkhani.model.baseInfo.BaseInformation;
import org.azizkhani.model.location.Location;
import org.azizkhani.model.personel.Personel;
import org.azizkhani.model.vehicle.Vehicle;


public class DeviceHistory extends BaseEntity {

	private String simCard;
	private String softwareVersion;
	private String softwareUpdateDateTime;
	private String sendDateTime;
	private String installDateTime;
	private String historyDateTime;
	private boolean status;
	private String description;
	private Device device;
	private Personel responsible;
	private Location location;
	private Vehicle vehicle;
	private BaseInformation vehicleSetupLocation;

	public String getSimCard() {
		return simCard;
	}

	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getSoftwareUpdateDateTime() {
		return softwareUpdateDateTime;
	}

	public void setSoftwareUpdateDateTime(String softwareUpdateDateTime) {
		this.softwareUpdateDateTime = softwareUpdateDateTime;
	}

	public String getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public String getInstallDateTime() {
		return installDateTime;
	}

	public void setInstallDateTime(String installDateTime) {
		this.installDateTime = installDateTime;
	}

	public String getHistoryDateTime() {
		return historyDateTime;
	}

	public void setHistoryDateTime(String historyDateTime) {
		this.historyDateTime = historyDateTime;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Personel getResponsible() {
		return responsible;
	}

	public void setResponsible(Personel responsible) {
		this.responsible = responsible;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public BaseInformation getVehicleSetupLocation() {
		return vehicleSetupLocation;
	}

	public void setVehicleSetupLocation(BaseInformation vehicleSetupLocation) {
		this.vehicleSetupLocation = vehicleSetupLocation;
	}
	

}
