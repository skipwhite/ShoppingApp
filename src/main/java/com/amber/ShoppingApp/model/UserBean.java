package com.amber.ShoppingApp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String name;
	private String email; 
	private String password;
	private Integer salt;
	
	public UserBean(String userId, String name, String email, String password, Integer salt) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.salt = salt;
	}
	public UserBean() {
		// TODO Auto-generated constructor stub
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSalt() {
		return salt;
	}
	public void setSalt(Integer salt) {
		this.salt = salt;
	}
	
}