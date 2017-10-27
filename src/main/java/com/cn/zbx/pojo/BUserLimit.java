package com.cn.zbx.pojo;

public class BUserLimit extends BasicPojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer buserId;

    private Integer menuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuserId() {
        return buserId;
    }

    public void setBuserId(Integer buserId) {
        this.buserId = buserId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}