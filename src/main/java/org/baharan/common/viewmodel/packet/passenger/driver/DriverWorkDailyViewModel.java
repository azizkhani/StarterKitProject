package org.baharan.common.viewmodel.packet.passenger.driver;

public class DriverWorkDailyViewModel {
	
	private String passengerPackageDriverCode;
	private String personFirstName;
	private String personLastName;
	private String passengerPackageActionDate;
	private Long count;
	private Long amount;
	
	public String getPassengerPackageDriverCode() {
		return passengerPackageDriverCode;
	}
	public void setPassengerPackageDriverCode(String passengerPackageDriverCode) {
		this.passengerPackageDriverCode = passengerPackageDriverCode;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
}
