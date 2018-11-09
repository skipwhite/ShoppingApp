package com.amber.ShoppingApp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class TestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Integer num;
	private BigDecimal deci;
	private Boolean bin;
	private Timestamp timestamp;
	
	public TestBean(String id, Integer num, BigDecimal deci, Boolean bin, Timestamp timestamp) {
		super();
		this.id = id;
		this.num = num;
		this.deci = deci;
		this.bin = bin;
		this.timestamp = timestamp;
	}
	
	public TestBean() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getDeci() {
		return deci;
	}
	public void setDeci(BigDecimal deci) {
		this.deci = deci;
	}
	public Boolean getBin() {
		return bin;
	}
	public void setBin(Boolean bin) {
		this.bin = bin;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	
}