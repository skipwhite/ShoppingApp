package com.amber.ShoppingApp.model;

import java.io.Serializable;
import java.util.List;

public class ODBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderBean ob;
	private List<OrderDetailBean> odbl;
	public ODBean(OrderBean ob, List<OrderDetailBean> odbl) {
		super();
		this.ob = ob;
		this.odbl = odbl;
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
	

	
	
}