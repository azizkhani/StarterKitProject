package org.baharan.model.line;

import org.baharan.model.BaseEntity;
import org.baharan.model.personel.Personel;
import org.baharan.model.terminal.Terminal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class Line extends BaseEntity {

	private String code;
	private String name;
	private int totalTimeGo;
	private int totalTimeBack;
	private int type;
	private String startTime;
	private String endTime;
	private String description;
	private int tariff ;
	private Personel responsible;
	private Terminal terminal;

	
	public Line(){
		
	}
	public Line(int LineId){
		this.setId(LineId);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getTotalTimeGo() {
		return totalTimeGo;
	}
	public void setTotalTimeGo(int totalTimeGo) {
		this.totalTimeGo = totalTimeGo;
	}

	public int getTotalTimeBack() {
		return totalTimeBack;
	}
	public void setTotalTimeBack(int totalTimeBack) {
		this.totalTimeBack = totalTimeBack;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Personel getResponsible() {
		return responsible;
	}
	public void setResponsible(Personel responsible) {
		this.responsible = responsible;
	}

	public Terminal getTerminal() {
		return terminal;
	}
	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	public int getTariff() {
		return tariff;
	}
	public void setTariff(int tariff) {
		this.tariff = tariff;
	}
	

}
