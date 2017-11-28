package com.cn.zbx.vo;

import java.util.Date;

import com.cn.zbx.pojo.BasicPojo;

public class VideoVO extends BasicPojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String videopath;

    private String imgurl;

    private Integer articleid;

    private String suffix;

    private Integer size;

    private Integer playtime;

    private Integer isfree;

    private Integer nocomment;//评论权限

    private Date makedate;

    private Date modifydate;
    
    private String articleTitle;//文章标题
    
    private Integer buyCount;//购买次数
    
    private Integer commentCount;//评论次数
    
    private Integer classid;//分类id

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

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath == null ? null : videopath.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
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

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	@Override
	public String toString() {
		return "VideoVO [id=" + id + ", title=" + title + ", videopath="
				+ videopath + ", imgurl=" + imgurl + ", articleid=" + articleid
				+ ", suffix=" + suffix + ", size=" + size + ", playtime="
				+ playtime + ", isfree=" + isfree + ", nocomment=" + nocomment
				+ ", makedate=" + makedate + ", modifydate=" + modifydate
				+ ", articleTitle=" + articleTitle + ", buyCount=" + buyCount
				+ ", commentCount=" + commentCount + ", classid=" + classid
				+ "]";
	}
}