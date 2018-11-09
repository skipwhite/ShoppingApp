package com.amber.ShoppingApp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amber.ShoppingApp.dao.OrderDAO;
import com.amber.ShoppingApp.model.OrderBean;
import com.amber.ShoppingApp.model.OrderDetailBean;
import com.amber.ShoppingApp.model.OrderBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public List<OrderBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from AB_ORDER";
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
	public OrderBean selectByPrimaryKey(String poNo) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PK = "select * from AB_ORDER where po_no = ?";
			ps = conn.prepareStatement(SELECT_BY_PK);
			if(poNo != null) {
				ps.setString(1, poNo);
			} else {
				throw new Exception("selectByPrimaryKey must input poNo");
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
	public int insert(OrderBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String INSERT = "insert into AB_ORDER values(?,?,?)";
			ps = conn.prepareStatement(INSERT);
			if (record.getPoNo() != null) {
				ps.setString(1, record.getPoNo());
			} else {
				throw new Exception("must input item");
			}
			
			ps.setString(2, record.getUserId());
			ps.setInt(3, new Integer(record.getTotal()));
			
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
	public int insertSelective(OrderBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String poNo) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static List<OrderBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<OrderBean> resultList = new ArrayList<OrderBean>();
		while (rs.next()) {
			OrderBean beam = new OrderBean();
			beam.setPoNo(rs.getString("po_no"));
			beam.setUserId(rs.getString("user_id"));
			beam.setTotal(rs.getInt("total"));
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}

}
