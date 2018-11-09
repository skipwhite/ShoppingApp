package com.amber.ShoppingApp.model;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productId;
	private String name;
	private String dscr;
	private String category;
	private Integer price; 
	private Integer viewCount;
	private Integer inventory;
	private String tag;
	private BigDecimal discount;
	private Integer salesQty;
	private Boolean launched;
	
	
	
	public ProductBean(String productId, String name, String dscr, String category, Integer price, Integer viewCount,
			Integer inventory, String tag, BigDecimal discount, Integer salesQty, Boolean launched) {
		super();
		this.productId = productId;
		this.name = name;
		this.dscr = dscr;
		this.category = category;
		this.price = price;
		this.viewCount = viewCount;
		this.inventory = inventory;
		this.tag = tag;
		this.discount = discount;
		this.salesQty = salesQty;
		this.launched = launched;
	}
	public ProductBean() {
		// TODO Auto-generated constructor stub
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDscr() {
		return dscr;
	}
	public void setDscr(String dscr) {
		this.dscr = dscr;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Integer getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(Integer salesQty) {
		this.salesQty = salesQty;
	}
	public Boolean getLaunched() {
		return launched;
	}
	public void setLaunched(Boolean launched) {
		this.launched = launched;
	}

	
}