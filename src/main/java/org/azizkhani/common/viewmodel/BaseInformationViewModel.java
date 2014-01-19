package org.azizkhani.common.viewmodel;

import org.dozer.Mapping;



public class BaseInformationViewModel extends BaseEntityViewModel {
	
	private int code;
	private String topic;
    //@Mapping("parent.id")
	private int parentId;
	
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTopic() {
		return topic;
	}
    //@Mapping("parent.id")
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
