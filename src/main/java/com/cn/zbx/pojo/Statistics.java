package com.cn.zbx.pojo;

import java.util.Date;

public class Statistics extends BasicPojo{

	/**
	 * 统计
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userId;//用户Id

    private Integer productId;//文章id/视屏id

    private String productType;//类型：1：文章 2：视频

    private Date createDate;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Statistics [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", productType=" + productType + ", createDate="
				+ createDate + "]";
	}
}