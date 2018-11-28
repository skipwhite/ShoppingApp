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
	
	int insert(ProductBean record) throws SQLException, Exception;

	int insertSelective(ProductBean record, List<ProductImgBean> pibs) throws SQLException, Exception;

	int updateInvSalesByPK(String productId, Integer qty) throws SQLException, Exception;

	int updateByPrimaryKeySelective(ProductBean record) throws SQLException, Exception;

	int updateByPrimaryKey(ProductBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String productId) throws SQLException, Exception;
	
//	Map<ProductBean, String> checkCart(HttpServletRequest request) throws SQLException, Exception;
	
//	Map<String, String> addToCart(String addProductId, String qty, Map<String, String> cart) throws Exception;
	
	Map<ProductBean, String> checkCookie(HttpServletRequest request) throws SQLException, Exception;

	int createProduct(ProductBean pb, List<ProductImgBean> pibs) throws SQLException, Exception;
	
	Map<ProductBean, String> selectAllProductOneImg() throws SQLException, Exception;

	List<ProductBean> multipleSelectByPrimaryKey(List<String> productIds) throws SQLException, Exception;

}
