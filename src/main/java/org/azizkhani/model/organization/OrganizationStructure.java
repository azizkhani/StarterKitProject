package org.azizkhani.model.organization;

import java.util.Date;

public class OrganizationStructure {
 
	private Long id;
	private String parentId;
	private String code;
	private String title;
	private Integer edited;
	private Boolean correction;
	private Date createDate;
	private String tierCreator;
	private Long userId;
	private Long count;
	private Long organizationId;
	private Integer priorityId;
	private String classificationName;
	private String tacticalDeployment="";
	private String fixedDeployment="";
	private String fathersName;
	private Boolean pic = Boolean.FALSE;
	private Boolean hasChild;
	private int layer;

	
	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public String getTacticalDeployment() {
		return tacticalDeployment;
	}

	public void setTacticalDeployment(String tacticalDeployment) {
		this.tacticalDeployment = tacticalDeployment;
	}

	public String getFixedDeployment() {
		return fixedDeployment;
	}

	public void setFixedDeployment(String fixedDeployment) {
		this.fixedDeployment = fixedDeployment;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public Boolean getPic() {
		return pic;
	}

	public void setPic(Boolean pic) {
		this.pic = pic;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setEdited(Integer edited) {
		this.edited = edited;
	}

	public Integer getEdited() {
		return edited;
	}

	public void setCorrection(Boolean correction) {
		this.correction = correction;
	}

	public Boolean getCorrection() {
		return correction;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setTierCreator(String tierCreator) {
		this.tierCreator = tierCreator;
	}

	public String getTierCreator() {
		return tierCreator;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getCount() {
		return count;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public Integer getPriorityId() {
		return priorityId;
	}
 
}
