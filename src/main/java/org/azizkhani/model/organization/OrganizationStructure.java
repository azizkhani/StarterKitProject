package org.azizkhani.model.organization;

import org.azizkhani.model.BaseEntity;

public class OrganizationStructure extends BaseEntity{
 
	private OrganizationStructure parentId;
	private String code;
	private String title;
	public OrganizationStructure getParentId() {
		return parentId;
	}
	public void setParentId(OrganizationStructure parentId) {
		this.parentId = parentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
 
}
