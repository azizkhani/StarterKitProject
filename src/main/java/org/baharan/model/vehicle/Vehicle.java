package org.baharan.model.vehicle;

import java.util.Set;

import org.baharan.model.BaseEntity;
import org.baharan.model.baseInfo.BaseInformation;
import org.baharan.model.line.Line;

public class Vehicle extends BaseEntity {

	private String code;
	private int type;
	private String plaque1;
	private String plaque2;
	private String plaque3;
	private String plaque4;

	private int possession;
	private String enginNumber;
	private String chasisNumber;
	private String productionYear;
	private int capacity;
	private int axisTotal;
	private String description;

	private BaseInformation fuelType;
	private BaseInformation model;
	private BaseInformation mark;
	private Set<Line> lines;
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public String getPlaque1() {
		return plaque1;
	}

	public void setPlaque1(String plaque1) {
		this.plaque1 = plaque1;
	}

	public String getPlaque2() {
		return plaque2;
	}

	public void setPlaque2(String plaque2) {
		this.plaque2 = plaque2;
	}

	public String getPlaque3() {
		return plaque3;
	}

	public void setPlaque3(String plaque3) {
		this.plaque3 = plaque3;
	}

	public String getPlaque4() {
		return plaque4;
	}

	public void setPlaque4(String plaque4) {
		this.plaque4 = plaque4;
	}


	public int getPossession() {
		return possession;
	}

	public void setPossession(int possession) {
		this.possession = possession;
	}

	public String getEnginNumber() {
		return enginNumber;
	}

	public void setEnginNumber(String enginNumber) {
		this.enginNumber = enginNumber;
	}

	public String getChasisNumber() {
		return chasisNumber;
	}

	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	public String getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAxisTotal() {
		return axisTotal;
	}

	public void setAxisTotal(int axisTotal) {
		this.axisTotal = axisTotal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BaseInformation getFuelType() {
		return fuelType;
	}

	public void setFuelType(BaseInformation fuelType) {
		this.fuelType = fuelType;
	}

	public BaseInformation getModel() {
		return model;
	}

	public void setModel(BaseInformation model) {
		this.model = model;
	}

	public BaseInformation getMark() {
		return mark;
	}

	public void setMark(BaseInformation mark) {
		this.mark = mark;
	}
	
	public Set<Line> getLines() {
		return lines;
	}

	public void setLines(Set<Line> lines) {
		this.lines = lines;
	}

	public String getPlaque(){
		return plaque1+" "+plaque2+" "+plaque3+" "+plaque4;
	}
}
