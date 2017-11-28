package com.cn.zbx.pojo;

import java.util.Date;

public class Price extends BasicPojo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer type;

    private Integer productId;

    private Double price;

    private Integer playNumber;

    private Double sumPrice;

    private Integer againPayDays;

    private Date makedate;

    private Date modifydate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getAgainPayDays() {
        return againPayDays;
    }

    public void setAgainPayDays(Integer againPayDays) {
        this.againPayDays = againPayDays;
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
		return "Price [id=" + id + ", type=" + type + ", productId="
				+ productId + ", price=" + price + ", playNumber=" + playNumber
				+ ", sumPrice=" + sumPrice + ", againPayDays=" + againPayDays
				+ ", makedate=" + makedate + ", modifydate=" + modifydate + "]";
	}
}