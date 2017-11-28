package com.cn.zbx.vo;

import java.util.Date;

public class CustomerVO {

	private Integer id;

    private String name;

    private String password;

    private String mobile;

    private String email;

    private String realname;

    private String sex;

    private Integer age;

    private Date birthday;

    private Date makedate;

    private Date modifydate;
    
    private Integer isdisable;
    
    private Date logindate;
    
    private String spareField1;
	
	private String spareField2;
	
	private String spareField3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

	public Integer getIsdisable() {
		return isdisable;
	}

	public void setIsdisable(Integer isdisable) {
		this.isdisable = isdisable;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
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

	@Override
	public String toString() {
		return "CustomerVO [id=" + id + ", name=" + name + ", password="
				+ password + ", mobile=" + mobile + ", email=" + email
				+ ", realname=" + realname + ", sex=" + sex + ", age=" + age
				+ ", birthday=" + birthday + ", makedate=" + makedate
				+ ", modifydate=" + modifydate + ", isdisable=" + isdisable
				+ ", logindate=" + logindate + ", spareField1=" + spareField1
				+ ", spareField2=" + spareField2 + ", spareField3="
				+ spareField3 + "]";
	}
}