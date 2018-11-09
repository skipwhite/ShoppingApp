package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.model.UserBean;

public interface TestService {
	List<TestBean> selectAll() throws SQLException, Exception;
	
	TestBean selectByPrimaryKey(String id) throws SQLException, Exception;
	
	/*
	 * insert DateBeam
	 */
	int insert(TestBean record) throws SQLException, Exception;

	/*
	 * insert DateBeam selective
	 */
	int insertSelective(TestBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective by PK
	 */
	int updateByPrimaryKeySelective(TestBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective
	 */
	int updateByPrimaryKey(TestBean record) throws SQLException, Exception;

	/*
	 * delete DateBeam by PK
	 */
	int deleteByPrimaryKey(String id) throws SQLException, Exception;
	

}
