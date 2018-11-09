package com.amber.ShoppingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.dao.UserDAO;
import com.amber.ShoppingApp.dao.impl.UserDAOImpl;
import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public List<UserBean> selectAll() throws SQLException, Exception {
		UserDAO dao = new UserDAOImpl();
		return dao.selectAll();
	}

	@Override
	public UserBean selectByPrimaryKey(String userId) throws SQLException, Exception {
		UserDAO dao = new UserDAOImpl();
		return dao.selectByPrimaryKey(userId);
	}

	@Override
	public int insert(UserBean record) throws SQLException, Exception {
		UserDAO dao = new UserDAOImpl();
		return dao.insert(record);
	}

	@Override
	public int insertSelective(UserBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(UserBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UserBean record) throws SQLException, Exception {
		UserDAO dao = new UserDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String userId) throws SQLException, Exception {
		UserDAO dao = new UserDAOImpl();
		return dao.deleteByPrimaryKey(userId);
	}

	@Override
	public int tryCreateUser(UserBean record) throws SQLException, Exception {
		UserDAO dao = new UserDAOImpl();
		return dao.tryCreateUser(record);
	}

	@Override
	public boolean login(String userId, String password) throws SQLException, Exception {
		UserDAO dao = new UserDAOImpl();
		return dao.login(userId, password);
	}

}
