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
	
	/*
	 * insert DateBeam
	 */
	int insert(ProductBean record) throws SQLException, Exception;

	/*
	 * insert DateBeam selective
	 */
	int insertSelective(ProductBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective by PK
	 */
	int updateByPrimaryKeySelective(ProductBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective
	 */
	int updateByPrimaryKey(ProductBean record) throws SQLException, Exception;

	/*
	 * delete DateBeam by PK
	 */
	int deleteByPrimaryKey(String productId) throws SQLException, Exception;
	
//	Map<String, ProductBean> checkCart(Cookie[] cookie) throws SQLException, Exception;

	Map<ProductBean, String> checkCart(HttpServletRequest request) throws SQLException, Exception;
	
	Map<String, String> addToCart(String addProductId, String qty, Map<String, String> cart) throws Exception;
}
