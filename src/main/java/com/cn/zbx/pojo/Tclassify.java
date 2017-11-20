package com.cn.zbx.pojo;

import java.util.Date;

public class Tclassify extends BasicPojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String code;

    private String name;

    private Integer pid;

    private Integer torder;

    private Integer level;

    private String excerpt;

    private Integer isdisplay;

    private Date makedate;

    private Date modifydate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public Integer getTorder() {
		return torder;
	}

	public void setTorder(Integer torder) {
		this.torder = torder;
	}

	public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt == null ? null : excerpt.trim();
    }

    public Integer getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Integer isdisplay) {
        this.isdisplay = isdisplay;
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
}