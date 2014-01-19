package org.azizkhani.common.viewmodel.packet.passenger.driver;

public class DriverWorkDetailViewModel {
	
	private String personCode;
	private String personFirstName;
	private String personLastName;
	private String vehiclePlaque;
	private String passengerPackageActionDate;
	private String passengerPackageActionTime;
	private Long passengerPackageAmount;
	
	
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
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
	public String getVehiclePlaque() {
		return vehiclePlaque;
	}
	public void setVehiclePlaque(String vehiclePlaque) {
		this.vehiclePlaque = vehiclePlaque;
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
}
