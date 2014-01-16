package org.baharan.core;

import java.util.List;



public class QueryResult<T>
{
	public int pageNumber ;
    public int totalRecords ;
    public int pageSize ;
    public List<T> entityList;
   
	public QueryResult(int pageNumber, int totalRecords, int pageSize,List<T> entityList) {
		super(); 
		this.pageNumber = pageNumber;
		this.totalRecords = totalRecords;
		this.pageSize = pageSize;
		this.entityList = entityList;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getEntityList() {
		return entityList;
	}
	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}
}
