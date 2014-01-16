package org.baharan.common.viewmodel.packet.passenger.bus;

public class BusWorkHourlyViewModel {
	private String passengerPackageBusCode;
	private String hour;
	private Long count;
	private Long amount;
	
	public String getPassengerPackageBusCode() {
		return passengerPackageBusCode;
	}
	public void setPassengerPackageBusCode(String passengerPackageBusCode) {
		this.passengerPackageBusCode = passengerPackageBusCode;
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
