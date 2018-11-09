package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.OrderDetailBean;

public interface OrderDetailDAO {
	
	List<OrderDetailBean> selectAll() throws SQLException, Exception;
	
	/*
	 * Select all product item
	 */
	List<OrderDetailBean> selectAllItem(String poNo) throws SQLException, Exception;

	/*
	 * Select product item
	 */
	OrderDetailBean selectOneItem(String poNo, String productId) throws SQLException, Exception;
	
	/*
	 * insert DateBeam
	 */
	int insert(OrderDetailBean record) throws SQLException, Exception;

	/*
	 * insert DateBeam selective
	 */
	int insertSelective(OrderDetailBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective by PK
	 */
	int updateByPrimaryKeySelective(OrderDetailBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective
	 */
	int updateByPrimaryKey(OrderDetailBean record) throws SQLException, Exception;

	/*
	 * delete DateBeam by PK
	 */
	int deleteByPrimaryKey(String poNo) throws SQLException, Exception;
	

}
