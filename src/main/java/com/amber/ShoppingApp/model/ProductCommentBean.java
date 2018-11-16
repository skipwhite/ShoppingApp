package com.amber.ShoppingApp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductCommentBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commentId;
	private String productId;
	private String userId;
	private BigDecimal rate;
	private String comment;
	private Timestamp timestamp;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}