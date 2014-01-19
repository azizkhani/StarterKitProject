package org.azizkhani.common.viewmodel.packet.passenger.bus;

public class BusWorkDetailViewModel {
	
	private String vehicleCode;
	private String vehiclePlaque;
	private String personFirstName;
	private String personLastName;
	private String passengerPackageActionDate;
	private String passengerPackageActionTime;
	private Long passengerPackageAmount;
	private Long passengerPackageCardSerial;
	
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getVehiclePlaque() {
		return vehiclePlaque;
	}
	public void setVehiclePlaque(String vehiclePlaque) {
		this.vehiclePlaque = vehiclePlaque;
	}
	public String getPersonFirstName() {
		return personFirstName;
	}
	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}
	public String getPersonLastName() {
		return personLastName;
	}
	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}
	public String getPassengerPackageActionDate() {
		return passengerPackageActionDate;
	}
	public void setPassengerPackageActionDate(String passengerPackageActionDate) {
		this.passengerPackageActionDate = passengerPackageActionDate;
	}
	public String getPassengerPackageActionTime() {
		return passengerPackageActionTime;
	}
	public void setPassengerPackageActionTime(String passengerPackageActionTime) {
		this.passengerPackageActionTime = passengerPackageActionTime;
	}
	public Long getPassengerPackageAmount() {
		return passengerPackageAmount;
	}
	public void setPassengerPackageAmount(Long passengerPackageAmount) {
		this.passengerPackageAmount = passengerPackageAmount;
	}
	public Long getPassengerPackageCardSerial() {
		return passengerPackageCardSerial;
	}
	public void setPassengerPackageCardSerial(Long passengerPackageCardSerial) {
		this.passengerPackageCardSerial = passengerPackageCardSerial;
	}
}
