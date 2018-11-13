package com.amber.ShoppingApp.model.noUse;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderDetailBean2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String item;
	private String poNo;
	private String productId;
	private Integer qty;
	
	public OrderDetailBean2() {
		
	}
	
	public OrderDetailBean2(String item, String poNo, String productId, Integer qty) {
		super();
		this.item = item;
		this.poNo = poNo;
		this.productId = productId;
		this.qty = qty;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}


}