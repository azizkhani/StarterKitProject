package org.azizkhani.model.coding;

import java.util.Date;

import org.azizkhani.model.BaseEntity;

public class CodingTitle extends BaseEntity  {
	private String title;
	private Date creationdate;
	private Long userId;
	private Integer codingId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getCodingId() {
		return codingId;
	}
	public void setCodingId(Integer codingId) {
		this.codingId = codingId;
	}
}
