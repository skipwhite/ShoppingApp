package com.amber.ShoppingApp.model.noUse;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderBean2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String poNo;
	private String userId;
	private Integer total;
	
	public OrderBean2(String poNo, String userId, Integer total) {
		super();
		this.poNo = poNo;
		this.userId = userId;
		this.total = total;
	}
	public OrderBean2() {
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

}