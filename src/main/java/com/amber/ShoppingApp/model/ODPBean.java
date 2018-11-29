package com.amber.ShoppingApp.model;

import java.io.Serializable;
import java.util.List;

public class ODPBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderBean ob;
	private List<OrderDetailBean> odbl;
	private List<ProductBean> pb;
	
	public ODPBean(OrderBean ob, List<OrderDetailBean> odbl, List<ProductBean> pb) {
		super();
		this.ob = ob;
		this.odbl = odbl;
		this.pb = pb;
	}
	public ODPBean() {
		// TODO Auto-generated constructor stub
	}
	public OrderBean getOb() {
		return ob;
	}
	public void setOb(OrderBean ob) {
		this.ob = ob;
	}
	public List<OrderDetailBean> getOdbl() {
		return odbl;
	}
	public void setOdbl(List<OrderDetailBean> odbl) {
		this.odbl = odbl;
	}
	public List<ProductBean> getPb() {
		return pb;
	}
	public void setPb(List<ProductBean> pb) {
		this.pb = pb;
	}
	

	
	
}