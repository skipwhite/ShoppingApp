package com.amber.ShoppingApp.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amber.ShoppingApp.dao.ProductCommentDAO;
import com.amber.ShoppingApp.model.ProductCommentBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class ProductCommentDAOImpl implements ProductCommentDAO {

	@Override
	public List<ProductCommentBean> selectByProduct(String productId) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductCommentBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PRODUCT = "select * from AB_PRODUCT_COMMENT where product_id = ?";
			ps = conn.prepareStatement(SELECT_BY_PRODUCT);
			if(productId != null) {
				ps.setString(1, productId);
			} else {
				throw new Exception("selectByProduct must input productId");
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
	public List<ProductCommentBean> selectAll() throws SQLException, Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		List<ProductCommentBean> beans = null;
//		
//		try {
//			conn = ConnectionDB.getConnection("amberDS");
//			
//			String SELECT_ALL = "select * from AB_TEST_TABLE";
//			ps = conn.prepareStatement(SELECT_ALL);
//			rs = ps.executeQuery();
//			
//			beans = getBeans(rs);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			ConnectionDB.closeJDBCConnection(conn);
//			ConnectionDB.closePreparedStatement(ps);
//			ConnectionDB.closeResultSet(rs);
//		}
//		return beans;
		return null;
	}

	@Override
	public ProductCommentBean selectByPrimaryKey(String id) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductCommentBean> beans = null;
		
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
	public int insert(ProductCommentBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String INSERT = "insert into AB_PRODUCT_COMMENT values(?,?,?,?,? ,?)";
			ps = conn.prepareStatement(INSERT);
			if (record.getCommentId() != null) {
				ps.setString(1, record.getCommentId());
			} else {
				throw new Exception("must input getCommentId");
			}
			
			ps.setString(2, record.getProductId());
			ps.setString(3, record.getUserId());
			ps.setBigDecimal(4, new BigDecimal(record.getRate().toString()));
			ps.setString(5, record.getComment());
			ps.setTimestamp(6, new java.sql.Timestamp(record.getTimestamp().getTime()));
			
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
	public int insertSelective(ProductCommentBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(ProductCommentBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ProductCommentBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static List<ProductCommentBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<ProductCommentBean> resultList = new ArrayList<ProductCommentBean>();
		while (rs.next()) {
			ProductCommentBean beam = new ProductCommentBean();
			beam.setCommentId(rs.getString("comment_id"));
			beam.setProductId(rs.getString("product_id"));
			beam.setUserId(rs.getString("user_id"));
			beam.setRate(rs.getBigDecimal("rate"));
			beam.setComment(rs.getString("comment"));
			beam.setTimestamp(rs.getTimestamp("timestamp"));
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}


}
