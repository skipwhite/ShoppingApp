package com.amber.ShoppingApp.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amber.ShoppingApp.dao.OrderDetailDAO;
import com.amber.ShoppingApp.model.OrderDetailBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class OrderDetailDAOImpl implements OrderDetailDAO {

	@Override
	public List<OrderDetailBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderDetailBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from AB_ORDER_DTL";
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
	public List<OrderDetailBean> selectAllItem(String poNo) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderDetailBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PO = "select * from AB_ORDER_DTL where po_no = ?";
			ps = conn.prepareStatement(SELECT_BY_PO);
			if(poNo != null) {
				ps.setString(1, poNo);
			} else {
				throw new Exception("selectAllItem must input poNo");
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
		return (beans != null && !beans.isEmpty()) ? beans : null;
	}

	@Override
	public OrderDetailBean selectOneItem(String poNo, String productId) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderDetailBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ONE_ITEM = "select * from AB_ORDER_DTL where po_no = ? AND product_id = ?";
			ps = conn.prepareStatement(SELECT_ONE_ITEM);
			if(poNo != null) {
				ps.setString(1, poNo);
			} else {
				throw new Exception("selectOneItem must input poNo");
			}
			if(productId != null) {
				ps.setString(2, productId);
			} else {
				throw new Exception("selectOneItem must input productId");
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
	public int insert(OrderDetailBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String INSERT = "insert into AB_ORDER_DTL values(?,?,?,?,? ,?,?)";
			ps = conn.prepareStatement(INSERT);
			if (record.getItem() != null) {
				ps.setString(1, record.getItem());
			} else {
				throw new Exception("must input item ODDAOImpl");
			}
			
			ps.setString(2, record.getPoNo());
			ps.setString(3, record.getProductId());
			ps.setString(4, record.getCategory());
			ps.setInt(5, new Integer(record.getQty()));
			ps.setInt(6, new Integer(record.getPrice()));
			ps.setBoolean(7, new Boolean(record.getIsCommented()));
			
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
	public int insertSelective(OrderDetailBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderDetailBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int updateIsComment(String poNo, String productId) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String UPDATE = "update AB_ORDER_DTL set is_commented = true where po_no = ? AND product_id = ? ";
			ps = conn.prepareStatement(UPDATE);
			
			if (poNo != null) {
				ps.setString(1, poNo);
			} else {
				throw new Exception("must input poNo");
			}
			if (productId != null) {
				ps.setString(2, productId);
			} else {
				throw new Exception("must input ProductId");
			}

			count = ps.executeUpdate();
			System.out.println("update count : " + count);
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
	public int updateByPrimaryKey(OrderDetailBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int deleteByPrimaryKey(String poNo, String productId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static List<OrderDetailBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<OrderDetailBean> resultList = new ArrayList<OrderDetailBean>();
		while (rs.next()) {
			OrderDetailBean beam = new OrderDetailBean();
			beam.setItem(rs.getString("item"));
			beam.setPoNo(rs.getString("po_no"));
			beam.setProductId(rs.getString("product_id"));
			beam.setCategory(rs.getString("category"));
			beam.setQty(rs.getInt("qty"));
			beam.setPrice(rs.getInt("price"));
			beam.setIsCommented(rs.getBoolean("is_commented"));
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}
}
