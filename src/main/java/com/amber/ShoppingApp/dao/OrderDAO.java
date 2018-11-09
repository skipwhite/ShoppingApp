package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.OrderBean;

public interface OrderDAO {
	
	List<OrderBean> selectAll() throws SQLException, Exception;
	
	OrderBean selectByPrimaryKey(String poNo) throws SQLException, Exception;
	
	/*
	 * insert DateBeam
	 */
	int insert(OrderBean record) throws SQLException, Exception;

	/*
	 * insert DateBeam selective
	 */
	int insertSelective(OrderBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective by PK
	 */
	int updateByPrimaryKeySelective(OrderBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective
	 */
	int updateByPrimaryKey(OrderBean record) throws SQLException, Exception;

	/*
	 * delete DateBeam by PK
	 */
	int deleteByPrimaryKey(String poNo) throws SQLException, Exception;
	

}
