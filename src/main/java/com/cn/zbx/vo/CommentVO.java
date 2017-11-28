package com.cn.zbx.vo;

import java.util.Date;

public class CommentVO {

	private Integer id;

    private String title;

    private String comment;

    private Integer commenttype;

    private Integer customerId;
    
    private String customerName;
    
    private Integer commentToId;//文章id或者视频id
    
    private String spareField1;
	
	private String spareField2;
	
	private String spareField3;

    private Date makedate;

    private Date modifydate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCommenttype() {
		return commenttype;
	}

	public void setCommenttype(Integer commenttype) {
		this.commenttype = commenttype;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Integer getCommentToId() {
		return commentToId;
	}

	public void setCommentToId(Integer commentToId) {
		this.commentToId = commentToId;
	}

	public String getSpareField1() {
		return spareField1;
	}

	public void setSpareField1(String spareField1) {
		this.spareField1 = spareField1;
	}

	public String getSpareField2() {
		return spareField2;
	}

	public void setSpareField2(String spareField2) {
		this.spareField2 = spareField2;
	}

	public String getSpareField3() {
		return spareField3;
	}

	public void setSpareField3(String spareField3) {
		this.spareField3 = spareField3;
	}

	public Date getMakedate() {
		return makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	@Override
	public String toString() {
		return "CommentVO [id=" + id + ", title=" + title + ", comment="
				+ comment + ", commenttype=" + commenttype + ", customerId="
				+ customerId + ", customerName=" + customerName
				+ ", commentToId=" + commentToId + ", spareField1="
				+ spareField1 + ", spareField2=" + spareField2
				+ ", spareField3=" + spareField3 + ", makedate=" + makedate
				+ ", modifydate=" + modifydate + "]";
	}
}