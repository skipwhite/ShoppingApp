package com.amber.ShoppingApp.model;

import java.io.Serializable;

public class SettingBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lookupType;
	private String lookupCode;
	private String lookupDscr;
	private String value;
	public String getLookupType() {
		return lookupType;
	}
	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}
	public String getLookupCode() {
		return lookupCode;
	}
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	public String getLookupDscr() {
		return lookupDscr;
	}
	public void setLookupDscr(String lookupDscr) {
		this.lookupDscr = lookupDscr;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}