package com.cn.zbx.vo;

import java.util.Arrays;
import java.util.Date;


public class StatisticsVO {
    /** TODO */

	private Date createDate;
	
	private Integer count;//数量
	
	private Integer customerId;//客户Id
	
	private String startDate;
	
	private String endDate;
	
	private String[] dataStr;
	
	private String[] countStr;
	
	private String dateType;
	
	private char productType;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String[] getDataStr() {
		return dataStr;
	}

	public void setDataStr(String[] dataStr) {
		this.dataStr = dataStr;
	}

	public String[] getCountStr() {
		return countStr;
	}

	public void setCountStr(String[] countStr) {
		this.countStr = countStr;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public char getProductType() {
		return productType;
	}

	public void setProductType(char productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "StatisticsVO [createDate=" + createDate + ", count=" + count
				+ ", customerId=" + customerId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", dataStr="
				+ Arrays.toString(dataStr) + ", countStr="
				+ Arrays.toString(countStr) + ", dateType=" + dateType
				+ ", productType=" + productType + "]";
	}
}