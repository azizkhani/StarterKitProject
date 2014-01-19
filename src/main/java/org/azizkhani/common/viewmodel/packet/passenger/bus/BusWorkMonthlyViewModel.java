package org.azizkhani.common.viewmodel.packet.passenger.bus;

public class BusWorkMonthlyViewModel {
	private String passengerPackageBusCode;
	private String month;
	private Long count;
	private Long amount;
	
	public String getPassengerPackageBusCode() {
		return passengerPackageBusCode;
	}
	public void setPassengerPackageBusCode(String passengerPackageBusCode) {
		this.passengerPackageBusCode = passengerPackageBusCode;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
