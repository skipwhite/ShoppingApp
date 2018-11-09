package com.amber.ShoppingApp.service;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.ODBean;

public interface ODService {
	
	List<ODBean> selectAll() throws SQLException, Exception;
	
	ODBean selectByPrimaryKey(String poNo) throws SQLException, Exception;
	
	/*
	 * insert DateBeam
	 */
	int insert(ODBean record) throws SQLException, Exception;

	/*
	 * insert DateBeam selective
	 */
	int insertSelective(ODBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective by PK
	 */
	int updateByPrimaryKeySelective(ODBean record) throws SQLException, Exception;

	/*
	 * update DateBeam selective
	 */
	int updateByPrimaryKey(ODBean record) throws SQLException, Exception;

	/*
	 * delete DateBeam by PK
	 */
	int deleteByPrimaryKey(String poNo) throws SQLException, Exception;
	
}
