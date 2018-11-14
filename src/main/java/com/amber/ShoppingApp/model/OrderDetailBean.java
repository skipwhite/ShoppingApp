package com.amber.ShoppingApp.model;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String item;
	private String poNo;
	private String productId;
	private String category;
	private Integer qty;
	private Integer price;
	public OrderDetailBean() {

	}
	public OrderDetailBean(String item, String poNo, String productId, String category, Integer qty, Integer price) {
		super();
		this.item = item;
		this.poNo = poNo;
		this.productId = productId;
		this.category = category;
		this.qty = qty;
		this.price = price;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	


}