package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ODBean;
import com.amber.ShoppingApp.model.ODPBean;
import com.amber.ShoppingApp.model.OrderBean;

public interface OrderService {
	
	List<OrderBean> selectAll() throws SQLException, Exception;
	
	List<OrderBean> selectByUser(String userId) throws SQLException, Exception;
	
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
	
	HttpServletRequest confirmOrder(HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	ODBean checkOrder(String poNo) throws SQLException, Exception;

	List<ODBean> myOrder(String userId);
	
//	List<ODPBean> myOrder(String userId);
}
