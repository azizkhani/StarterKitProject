package org.baharan.common.viewmodel.packet.passenger.device;

public class DeviceWorkDailyViewModel {
	private String passengerPackageDeviceCode;
	private String passengerPackageActionDate;
	private String count;
	private String amount;
	
	public String getPassengerPackageDeviceCode() {
		return passengerPackageDeviceCode;
	}
	public void setPassengerPackageDeviceCode(String passengerPackageDeviceCode) {
		this.passengerPackageDeviceCode = passengerPackageDeviceCode;
	}
	public String getPassengerPackageActionDate() {
		return passengerPackageActionDate;
	}
	public void setPassengerPackageActionDate(String passengerPackageActionDate) {
		this.passengerPackageActionDate = passengerPackageActionDate;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
