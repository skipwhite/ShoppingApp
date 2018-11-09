package com.amber.ShoppingApp.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amber.ShoppingApp.dao.TestDAO;
import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class TestDAOImpl implements TestDAO {

	@Override
	public List<TestBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TestBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from AB_TEST_TABLE";
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
	public TestBean selectByPrimaryKey(String id) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TestBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PK = "select * from AB_TEST_TABLE where id = ?";
			ps = conn.prepareStatement(SELECT_BY_PK);
			if(id != null) {
				ps.setString(1, id);
			} else {
				throw new Exception("must input Id");
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
	public int insert(TestBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String INSERT = "insert into AB_TEST_TABLE values(?,?,?,?,?)";
			ps = conn.prepareStatement(INSERT);
			if (record.getId() != null) {
				ps.setString(1, record.getId());
			} else {
				throw new Exception("must input Id");
			}
			
			ps.setInt(2, new Integer(record.getNum()));
			ps.setBigDecimal(3, new BigDecimal(record.getDeci().toString()));
			ps.setBoolean(4, new Boolean(record.getBin()));
			ps.setTimestamp(5, new java.sql.Timestamp(record.getTimestamp().getTime()));
			
			count = ps.executeUpdate();
			System.out.println("insert count : " + count);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionDB.closeJDBCConnection(conn);
			ConnectionDB.closePreparedStatement(ps);
			ConnectionDB.closeResultSet(rs);
		}
		return count;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static List<TestBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<TestBean> resultList = new ArrayList<TestBean>();
		while (rs.next()) {
			TestBean beam = new TestBean();
			beam.setId(rs.getString("id"));
			beam.setNum(rs.getInt("num"));
			beam.setDeci(rs.getBigDecimal("deci"));
			beam.setBin(rs.getBoolean("bin"));
			beam.setTimestamp(rs.getTimestamp("timestamp"));
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}
}
