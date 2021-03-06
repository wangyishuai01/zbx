package com.cn.zbx.pojo;

import java.util.Date;

public class Comment extends BasicPojo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String comment;

    private Integer commenttype;

    private Integer customerId;

    private Date makedate;

    private Date modifydate;
    
    private Integer commentToId;//文章id或者视频id

	public Integer getCommentToId() {
		return commentToId;
	}

	public void setCommentToId(Integer commentToId) {
		this.commentToId = commentToId;
	}

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
        this.title = title == null ? null : title.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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
		return "Comment [id=" + id + ", title=" + title + ", comment="
				+ comment + ", commenttype=" + commenttype + ", customerId="
				+ customerId + ", makedate=" + makedate + ", modifydate="
				+ modifydate + ", commentToId=" + commentToId + "]";
	}
}