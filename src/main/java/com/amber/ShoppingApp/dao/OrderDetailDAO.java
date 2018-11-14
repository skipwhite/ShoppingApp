package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.OrderDetailBean;

public interface OrderDetailDAO {
	
	List<OrderDetailBean> selectAll() throws SQLException, Exception;
	
	List<OrderDetailBean> selectAllItem(String poNo) throws SQLException, Exception;

	OrderDetailBean selectOneItem(String poNo, String productId) throws SQLException, Exception;
	
	int insert(OrderDetailBean record) throws SQLException, Exception;

	int insertSelective(OrderDetailBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(OrderDetailBean record) throws SQLException, Exception;

	int updateByPrimaryKey(OrderDetailBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String poNo, String productId) throws SQLException, Exception;
	

}
