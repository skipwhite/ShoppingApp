package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.ProductCommentBean;
import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.model.UserBean;

public interface ProductCommentService {
	List<ProductCommentBean> selectAll() throws SQLException, Exception;
	
	List<ProductCommentBean> selectByProduct(String productId) throws SQLException, Exception;
	
	ProductCommentBean selectByPrimaryKey(String userId) throws SQLException, Exception;
	
	int insert(ProductCommentBean record) throws SQLException, Exception;

	int insertSelective(ProductCommentBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(ProductCommentBean record) throws SQLException, Exception;

	int updateByPrimaryKey(ProductCommentBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String userId) throws SQLException, Exception;

	int createComment(ProductCommentBean record, String poNo) throws SQLException, Exception;
}
