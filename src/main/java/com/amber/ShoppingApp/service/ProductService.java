package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.model.ProductImgBean;

public interface ProductService {
	
	List<ProductBean> selectAll() throws SQLException, Exception;
	
	ProductBean selectByPrimaryKey(String productId) throws SQLException, Exception;
	
	/*
	 * insert DateBeam
	 */
	int insert(ProductBean record) throws SQLException, Exception;

	/*
	 * insert DateBeam selective
	 */
//	int insertSelective(ProductBean record) throws SQLException, Exception;

	int insertSelective(ProductBean record, List<ProductImgBean> pibs) throws SQLException, Exception;
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
	
	
//	Map<ProductBean, String> checkCart(HttpServletRequest request) throws SQLException, Exception;
	
//	Map<String, String> addToCart(String addProductId, String qty, Map<String, String> cart) throws Exception;
	
	Map<ProductBean, String> checkCookie(HttpServletRequest request) throws SQLException, Exception;

	int createProduct(ProductBean pb, List<ProductImgBean> pibs) throws SQLException, Exception;
	
//	Map<ProductBean, List<String>> selectAllProduct() throws SQLException, Exception;
	
	Map<ProductBean, String> selectAllProductOneImg() throws SQLException, Exception;
}
