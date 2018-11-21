package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.UserBean;

public interface UserService {
	
	List<UserBean> selectAll() throws SQLException, Exception;
	
	UserBean selectByPrimaryKey(String userId) throws SQLException, Exception;
	
	/*
	 * insert DateBeam
	 */
	int insert(UserBean record) throws SQLException, Exception;

	/*
	 * insert DateBeam selective
	 */
	int insertSelective(UserBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective by PK
	 */
	int updateByPrimaryKeySelective(UserBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective
	 */
	int updateByPrimaryKey(UserBean record) throws SQLException, Exception;

	/*
	 * delete DateBeam by PK
	 */
	int deleteByPrimaryKey(String userId) throws SQLException, Exception;
	
	int createUser(UserBean record) throws SQLException, Exception;

//	boolean login(String userId, String password) throws SQLException, Exception;

	UserBean login(String userId, String password) throws SQLException, Exception;
}
