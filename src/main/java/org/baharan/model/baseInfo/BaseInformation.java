package org.baharan.model.baseInfo;

import org.baharan.model.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



public class BaseInformation extends BaseEntity
{
	private int code;
	private String topic;
	private BaseInformation parent;
	
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
	@JsonProperty
	public void setParent(BaseInformation parent) {
		this.parent = parent;
	}
	@JsonIgnore
	public BaseInformation getParent() {
		return parent;
	}
}
