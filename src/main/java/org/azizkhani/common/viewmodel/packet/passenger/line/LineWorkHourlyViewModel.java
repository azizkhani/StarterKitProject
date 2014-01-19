package org.azizkhani.common.viewmodel.packet.passenger.line;

public class LineWorkHourlyViewModel {
	private String  lineCode;
	private String  lineName;
	private String  houre;
	private Long  count;
	private Long  amount;
	
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getHoure() {
		return houre;
	}
	public void setHoure(String houre) {
		this.houre = houre;
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
