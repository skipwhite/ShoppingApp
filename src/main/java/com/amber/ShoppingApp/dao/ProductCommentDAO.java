package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.ProductCommentBean;

public interface ProductCommentDAO {
	
	List<ProductCommentBean> selectAll() throws SQLException, Exception;
	
	List<ProductCommentBean> selectByProduct(String productId) throws SQLException, Exception;
	
	ProductCommentBean selectByPrimaryKey(String userId) throws SQLException, Exception;
	
	int insert(ProductCommentBean record) throws SQLException, Exception;

	int insertSelective(ProductCommentBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(ProductCommentBean record) throws SQLException, Exception;

	int updateByPrimaryKey(ProductCommentBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String userId) throws SQLException, Exception;
}
