package org.baharan.model.device;

import org.baharan.model.BaseEntity;
import org.baharan.model.baseInfo.BaseInformation;

public class Device extends BaseEntity{
	
    private String code;
    private String description;
    private DeviceGroup deviceGroup;
    private BaseInformation model;
    private BaseInformation mark;
    
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DeviceGroup getDeviceGroup() {
		return deviceGroup;
	}
	public void setDeviceGroup(DeviceGroup deviceGroup) {
		this.deviceGroup = deviceGroup;
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
    
}
