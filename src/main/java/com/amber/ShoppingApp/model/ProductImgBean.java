package com.amber.ShoppingApp.model;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductImgBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productId;
	private String item;
	private byte[] img;
	public ProductImgBean(byte[] img) {
		super();
		this.img = img;
	}
	public ProductImgBean() {
		// TODO Auto-generated constructor stub
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}



	
}