package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.UserBean;

public interface UserDAO {
	
	List<UserBean> selectAll() throws SQLException, Exception;
	
	UserBean selectByPrimaryKey(String userId) throws SQLException, Exception;
	
	int insert(UserBean record) throws SQLException, Exception;

	int insertSelective(UserBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(UserBean record) throws SQLException, Exception;

	int updateByPrimaryKey(UserBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String userId) throws SQLException, Exception;
}
