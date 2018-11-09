package com.amber.ShoppingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.dao.OrderDAO;
import com.amber.ShoppingApp.dao.impl.OrderDAOImpl;
import com.amber.ShoppingApp.model.OrderBean;
import com.amber.ShoppingApp.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public List<OrderBean> selectAll() throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.selectAll();
	}

	@Override
	public OrderBean selectByPrimaryKey(String poNo) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.selectByPrimaryKey(poNo);
	}

	@Override
	public int insert(OrderBean record) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.insert(record);
	}

	@Override
	public int insertSelective(OrderBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderBean record) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String poNo) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.deleteByPrimaryKey(poNo);
	}


}
