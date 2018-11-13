package com.amber.ShoppingApp.model.noUse;

import java.io.Serializable;
import java.util.List;

import com.amber.ShoppingApp.model.OrderDetailBean;

public class ODBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String poNo;
	private String userId;
	private Integer total;
	
	//ADD
	private List<OrderDetailBean> odbList;
	
	public ODBean(String poNo, String userId, Integer total) {
		super();
		this.poNo = poNo;
		this.userId = userId;
		this.total = total;
	}
	public ODBean() {
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
	public List<OrderDetailBean> getOdbList() {
		return odbList;
	}
	public void setOdbList(List<OrderDetailBean> odbList) {
		this.odbList = odbList;
	}

}