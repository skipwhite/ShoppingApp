package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.amber.ShoppingApp.model.ProductBean;

public interface ProductDAO {
	
	List<ProductBean> selectAll() throws SQLException, Exception;
	
	ProductBean selectByPrimaryKey(String productId) throws SQLException, Exception;
	
	int insert(ProductBean record) throws SQLException, Exception;

	int insertSelective(ProductBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(ProductBean record) throws SQLException, Exception;

	int updateByPrimaryKey(ProductBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String productId) throws SQLException, Exception;
	
}
