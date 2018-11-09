package com.amber.ShoppingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.dao.TestDAO;
import com.amber.ShoppingApp.dao.impl.TestDAOImpl;
import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.service.TestService;

public class TestServiceImpl implements TestService {

	@Override
	public List<TestBean> selectAll() throws SQLException, Exception {
		TestDAO dao = new TestDAOImpl();
		return dao.selectAll();
	}

	@Override
	public TestBean selectByPrimaryKey(String id) throws SQLException, Exception {
		TestDAO dao = new TestDAOImpl();
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int insert(TestBean record) throws SQLException, Exception {
		TestDAO dao = new TestDAOImpl();
		return dao.insert(record);
	}

	@Override
	public int insertSelective(TestBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(TestBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TestBean record) throws SQLException, Exception {
		TestDAO dao = new TestDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) throws SQLException, Exception {
		TestDAO dao = new TestDAOImpl();
		return dao.deleteByPrimaryKey(id);
	}


}
