package com.amber.ShoppingApp.dao;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.model.SettingBean;

public interface SettingDAO {
	
	List<SettingBean> selectAll() throws SQLException, Exception;
	
	SettingBean selectByPrimaryKey(String lookupType, String lookupCode) throws SQLException, Exception;
	
	int insert(SettingBean record) throws SQLException, Exception;

	int insertSelective(SettingBean record) throws SQLException, Exception;

	int updateByPrimaryKeySelective(SettingBean record) throws SQLException, Exception;

	int updateByPrimaryKey(SettingBean record) throws SQLException, Exception;

	int deleteByPrimaryKey(String lookupType, String lookupCode) throws SQLException, Exception;
}
