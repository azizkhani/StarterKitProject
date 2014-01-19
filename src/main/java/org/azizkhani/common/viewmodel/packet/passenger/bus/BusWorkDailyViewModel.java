package org.azizkhani.common.viewmodel.packet.passenger.bus;

public class BusWorkDailyViewModel {
	private String passengerPackageBusCode;
	private String passengerPackageActionDate;
	private Long count;
	private Long amount;
	
	public String getPassengerPackageBusCode() {
		return passengerPackageBusCode;
	}
	public void setPassengerPackageBusCode(String passengerPackageBusCode) {
		this.passengerPackageBusCode = passengerPackageBusCode;
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
