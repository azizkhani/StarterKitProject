package org.azizkhani.common.viewmodel.packet.passenger.driver;

public class DriverWorkHourlyViewModel {
	
	private String passengerPackageDriverCode;
	private String personFirstName;
	private String personLastName;
	private String hour;
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
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
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
