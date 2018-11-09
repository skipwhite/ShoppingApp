package com.amber.ShoppingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.dao.OrderDetailDAO;
import com.amber.ShoppingApp.dao.impl.OrderDetailDAOImpl;
import com.amber.ShoppingApp.model.OrderDetailBean;
import com.amber.ShoppingApp.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {

	@Override
	public List<OrderDetailBean> selectAll() throws SQLException, Exception {
		OrderDetailDAO dao = new OrderDetailDAOImpl();
		return dao.selectAll();
	}

	@Override
	public List<OrderDetailBean> selectAllItem(String poNo) throws SQLException, Exception {
		OrderDetailDAO dao = new OrderDetailDAOImpl();
		return dao.selectAllItem(poNo);
	}

	@Override
	public OrderDetailBean selectOneItem(String poNo, String productId) throws SQLException, Exception {
		OrderDetailDAO dao = new OrderDetailDAOImpl();
		return dao.selectOneItem(poNo, productId);
	}

	@Override
	public int insert(OrderDetailBean record) throws SQLException, Exception {
		OrderDetailDAO dao = new OrderDetailDAOImpl();
		return dao.insert(record);
	}

	@Override
	public int insertSelective(OrderDetailBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderDetailBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderDetailBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String poNo) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}



}
