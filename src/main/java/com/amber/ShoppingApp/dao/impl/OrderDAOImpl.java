package com.amber.ShoppingApp.dao.impl;

import java.math.BigDecimal;
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
	public List<OrderBean> selectByUser(String userId) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_USER = "select * from AB_ORDER where user_id = ?";
			ps = conn.prepareStatement(SELECT_BY_USER);
			if(userId != null) {
				ps.setString(1, userId);
			} else {
				throw new Exception("selectByUser must input userId");
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
		return 0;
	}

	@Override
	public int insertSelective(OrderBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String INSERT = "insert into AB_ORDER values(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,?)";
			ps = conn.prepareStatement(INSERT);
			
			if (record.getPoNo() != null) {
				ps.setString(1, record.getPoNo());
			} else {
				throw new Exception("must input PoNo OrderDAO");
			}
			
			// 2-5
			ps.setString(2, record.getUserId());
			ps.setString(3, record.getName());
			ps.setString(4, record.getPhone());
			ps.setString(5, record.getShipId());
			
			// 6-10
			ps.setString(6, record.getShipIdValue());
			if (record.getShipStore() != null) {
				ps.setString(7, record.getShipStore());
			} else {
				ps.setString(7, null);
			}
			ps.setString(8, record.getPayId());
			ps.setString(9, record.getPayIdValue());
			if (record.getZipCode() != null) {
				ps.setString(10, record.getZipCode());
			} else {
				ps.setString(10, null);
			}
			
			// 11-15
			if (record.getAddress() != null) {
				ps.setString(11, record.getAddress());
			} else {
				ps.setString(11, null);
			}
			ps.setInt(12, new Integer(record.getTotalPrice()));
			ps.setString(13, record.getStatus());
			ps.setBoolean(14, new Boolean(record.getInvalid()));
			ps.setBoolean(15, new Boolean(record.getIsCommented()));

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
			beam.setName(rs.getString("name"));
			beam.setPhone(rs.getString("phone"));
			beam.setShipId(rs.getString("ship_id"));
			beam.setShipIdValue(rs.getString("ship_id_value"));
			beam.setShipStore(rs.getString("ship_store"));
			beam.setPayId(rs.getString("pay_id"));
			beam.setPayIdValue(rs.getString("pay_id_value"));
			beam.setZipCode(rs.getString("zip_code"));
			beam.setAddress(rs.getString("address"));
			beam.setTotalPrice(rs.getInt("total_price"));
			beam.setInvalid(rs.getBoolean("invalid"));
			beam.setIsCommented(rs.getBoolean("is_commented"));
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}

}
