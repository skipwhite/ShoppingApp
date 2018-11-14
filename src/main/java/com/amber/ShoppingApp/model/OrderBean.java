package com.amber.ShoppingApp.model;

import java.io.Serializable;

public class OrderBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String poNo;
	private String userId;
	private String name;
	private String phone;
	private String shipId;
	private String shipIdValue;
	private String shipStore; 
	private String payId; 
	private String payIdValue; 
	private String zipCode;
	private String address;
	private Integer totalPrice;
	private String status;
	private Boolean invalid;
	private Boolean isCommented;
	public OrderBean() {
		
	}
	
	public OrderBean(String poNo, String userId, String name, String phone, String shipId, String shipIdValue,
			String shipStore, String payId, String payIdValue, String zipCode, String address, Integer totalPrice,
			String status, Boolean invalid, Boolean isCommented) {
		super();
		this.poNo = poNo;
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.shipId = shipId;
		this.shipIdValue = shipIdValue;
		this.shipStore = shipStore;
		this.payId = payId;
		this.payIdValue = payIdValue;
		this.zipCode = zipCode;
		this.address = address;
		this.totalPrice = totalPrice;
		this.status = status;
		this.invalid = invalid;
		this.isCommented = isCommented;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShipId() {
		return shipId;
	}
	public void setShipId(String shipId) {
		this.shipId = shipId;
	}
	public String getShipIdValue() {
		return shipIdValue;
	}
	public void setShipIdValue(String shipIdValue) {
		this.shipIdValue = shipIdValue;
	}
	public String getShipStore() {
		return shipStore;
	}
	public void setShipStore(String shipStore) {
		this.shipStore = shipStore;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getPayIdValue() {
		return payIdValue;
	}
	public void setPayIdValue(String payIdValue) {
		this.payIdValue = payIdValue;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Boolean getInvalid() {
		return invalid;
	}
	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}
	public Boolean getIsCommented() {
		return isCommented;
	}
	public void setIsCommented(Boolean isCommented) {
		this.isCommented = isCommented;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}