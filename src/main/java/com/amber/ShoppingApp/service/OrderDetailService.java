package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.OrderDetailBean;
import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.model.UserBean;

public interface OrderDetailService {
	
	List<OrderDetailBean> selectAll() throws SQLException, Exception;
	
	List<OrderDetailBean> selectAllItem(String poNo) throws SQLException, Exception;

	OrderDetailBean selectOneItem(String poNo, String productId) throws SQLException, Exception;
	
	int insert(OrderDetailBean record) throws SQLException, Exception;

	int insertSelective(OrderDetailBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(OrderDetailBean record) throws SQLException, Exception;

	int updateByPrimaryKey(OrderDetailBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String poNo) throws SQLException, Exception;
	
	int updateIsComment(String poNo, String productId) throws SQLException, Exception;
	

}
