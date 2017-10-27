package com.cn.zbx.pojo;

import java.io.Serializable;
import java.util.Date;

public abstract class BasicPojo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pageCount;			//页码
	private Integer pageSize;			//每页条数
	
	private Integer startLimit;				//开始查询记录
	
	private Date startTime;				//开始时间
	private Date endTime;				//结束时间
	
	private String sortFields;			//排序字段
	private String sort;				//排序方式
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getStartLimit() {
		if(pageCount != null && pageSize != null){
			startLimit = (pageCount.intValue() - 1) * pageSize.intValue();
		}
		return startLimit;
	}
	public void setStartLimit(Integer startLimit) {
		this.startLimit = startLimit;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public BasicPojo() {
		super();
	}
	public String getSortFields() {
		return sortFields;
	}
	public void setSortFields(String sortFields) {
		this.sortFields = sortFields;
	}
	public BasicPojo(Integer pageSize, Integer pageCount, Date startTime, Date endTime) {
		super();
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public BasicPojo(Date startTime, Date endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public BasicPojo(Integer pageSize, Integer pageCount, Date startTime, Date endTime, String sortFields, String sort) {
		super();
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.sortFields = sortFields;
		this.sort = sort;
	}
	public BasicPojo(Integer pageSize, Integer pageCount, String sortFields, String sort) {
		super();
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.sortFields = sortFields;
		this.sort = sort;
	}
	public BasicPojo(String sortFields, String sort) {
		super();
		this.sortFields = sortFields;
		this.sort = sort;
	}
	public BasicPojo(Integer pageSize, Integer pageCount) {
		super();
		this.pageSize = pageSize;
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "BasicPojo [pageCount=" + pageCount + ", pageSize=" + pageSize
				+ ", startLimit=" + startLimit + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", sortFields=" + sortFields
				+ ", sort=" + sort + "]";
	}
	
	
}
