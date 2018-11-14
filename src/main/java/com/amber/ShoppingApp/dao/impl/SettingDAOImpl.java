package com.amber.ShoppingApp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amber.ShoppingApp.dao.SettingDAO;
import com.amber.ShoppingApp.model.SettingBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class SettingDAOImpl implements SettingDAO {

	@Override
	public List<SettingBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SettingBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from AB_SETTING";
			ps = conn.prepareStatement(SELECT_ALL);
			rs = ps.executeQuery();
			
			beans = getBeans(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionDB.closeJDBCConnection(conn);
			ConnectionDB.closePreparedStatement(ps);
			ConnectionDB.closeResultSet(rs);
		}
		return beans;
		
	}
	
	@Override
	public SettingBean selectByPrimaryKey(String lookupType, String lookupCode) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SettingBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PK = "select * from AB_SETTING where lookup_type = ? AND lookup_code = ?";
			ps = conn.prepareStatement(SELECT_BY_PK);
			if(lookupType != null && lookupCode != null) {
				ps.setString(1, lookupType);
				ps.setString(2, lookupCode);
			} else {
				throw new Exception("must input type & code SettingDAO");
			}
			rs = ps.executeQuery();
			
			beans = getBeans(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionDB.closeJDBCConnection(conn);
			ConnectionDB.closePreparedStatement(ps);
			ConnectionDB.closeResultSet(rs);
		}
		return (beans != null && !beans.isEmpty()) ? beans.get(0) : null;
	}

	@Override
	public int insert(SettingBean record) throws SQLException, Exception {
		return 0;
	}

	@Override
	public int insertSelective(SettingBean record) throws SQLException, Exception {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(SettingBean record) throws SQLException, Exception {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SettingBean record) throws SQLException, Exception {
		return 0;
	}
	
	public static List<SettingBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<SettingBean> resultList = new ArrayList<SettingBean>();
		while (rs.next()) {
			SettingBean beam = new SettingBean();
			beam.setLookupType(rs.getString("lookup_type"));
			beam.setLookupCode(rs.getString("lookup_code"));
			beam.setLookupDscr(rs.getString("lookup_dscr"));
			beam.setValue(rs.getString("value"));
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}

	@Override
	public int deleteByPrimaryKey(String lookupType, String lookupCode) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
