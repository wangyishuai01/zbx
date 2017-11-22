package com.cn.zbx.pojo;

import java.util.Date;

public class Statistics extends BasicPojo{

	/**
	 * 统计
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userId;//用户Id

    private Integer commentToId;//文章id/视屏id

    private String type;//类型：1：文章 2：视频

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

	public Integer getCommentToId() {
		return commentToId;
	}

	public void setCommentToId(Integer commentToId) {
		this.commentToId = commentToId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    
}