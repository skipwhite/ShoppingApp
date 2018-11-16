package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.OrderBean;

public interface OrderDAO {
	
	List<OrderBean> selectAll() throws SQLException, Exception;

	List<OrderBean> selectByUser(String userId) throws SQLException, Exception;
	
	OrderBean selectByPrimaryKey(String poNo) throws SQLException, Exception;
	
	int insert(OrderBean record) throws SQLException, Exception;

	int insertSelective(OrderBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(OrderBean record) throws SQLException, Exception;

	int updateByPrimaryKey(OrderBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String poNo) throws SQLException, Exception;
}
