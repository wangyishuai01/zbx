package com.cn.zbx.vo;

public class TClassifyVO {
	private Integer classid;
	
	private String name;
	
	private Integer count;
	
	private String spareField1;
	
	private String spareField2;
	
	private String spareField3;

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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
		return "TClassifyVO [classid=" + classid + ", name=" + name
				+ ", count=" + count + ", spareField1=" + spareField1
				+ ", spareField2=" + spareField2 + ", spareField3="
				+ spareField3 + "]";
	}

	public TClassifyVO(Integer classid, String name, Integer count,
			String spareField1, String spareField2, String spareField3) {
		this.classid = classid;
		this.name = name;
		this.count = count;
		this.spareField1 = spareField1;
		this.spareField2 = spareField2;
		this.spareField3 = spareField3;
	}

	public TClassifyVO() {
		
	}
	
}
