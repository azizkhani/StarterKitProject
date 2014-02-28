package org.azizkhani.common.viewmodel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseEntityViewModel {

	private int id;
	private int createdBy;
	private int updatedBy;
	private Date createdDate;
	private Date updatedDate;
	private Boolean isEnabled;
	private String processDefinationKey;
	private String taskKey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@JsonIgnore
	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	@JsonIgnore
	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	@JsonIgnore
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@JsonIgnore
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@JsonIgnore
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getProcessDefinationKey() {
		return processDefinationKey;
	}

	public void setProcessDefinationKey(String processDefinationKey) {
		this.processDefinationKey = processDefinationKey;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	
}
