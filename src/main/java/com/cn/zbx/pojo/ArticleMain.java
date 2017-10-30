package com.cn.zbx.pojo;

import java.util.Date;

public class ArticleMain extends BasicPojo{
    /** TODO */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String imgurl;

    private Integer classid;

    private String excerpt;

    private Integer isfree;

    private Integer nocomment;
    
    private Integer state;

    private Date makedate;

    private Date modifydate;

    private byte[] content;
    
    private String contentStr;
    
    public String getContentStr() {
		return contentStr;
	}

	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt == null ? null : excerpt.trim();
    }

    public Integer getIsfree() {
        return isfree;
    }

    public void setIsfree(Integer isfree) {
        this.isfree = isfree;
    }

    public Integer getNocomment() {
        return nocomment;
    }

    public void setNocomment(Integer nocomment) {
        this.nocomment = nocomment;
    }

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}