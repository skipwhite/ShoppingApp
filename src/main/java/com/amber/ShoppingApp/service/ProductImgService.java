package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.ProductImgBean;
import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.model.UserBean;

public interface ProductImgService {
	List<ProductImgBean> selectAll() throws SQLException, Exception;
	
	List<String> selectByPrimaryKey(String productId) throws SQLException, Exception;
	
	String selectMainImg(String product_id) throws SQLException, Exception;
	/*
	 * insert DateBeam
	 */
//	int insert(ProductImgBean record) throws SQLException, Exception;

	int insert(List<ProductImgBean> beans, String productId) throws SQLException, Exception;
	/*
	 * insert DateBeam selective
	 */
	int insertSelective(ProductImgBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective by PK
	 */
	int updateByPrimaryKeySelective(ProductImgBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective
	 */
	int updateByPrimaryKey(ProductImgBean record) throws SQLException, Exception;

	/*
	 * delete DateBeam by PK
	 */
	int deleteByPrimaryKey(String productId) throws SQLException, Exception;
	

}
