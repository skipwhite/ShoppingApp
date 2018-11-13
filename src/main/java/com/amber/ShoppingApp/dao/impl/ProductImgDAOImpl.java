package com.amber.ShoppingApp.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.amber.ShoppingApp.dao.ProductImgDAO;
import com.amber.ShoppingApp.model.ProductImgBean;
import com.amber.ShoppingApp.util.ConnectionDB;
import com.amber.ShoppingApp.util.SerialUtil;

public class ProductImgDAOImpl implements ProductImgDAO {

	@Override
	public List<ProductImgBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductImgBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from AB_PRODUCT_IMG";
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
	public List<String> selectByPrimaryKey(String product_id) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> imgList = new ArrayList<>();
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PK = "select * from AB_PRODUCT_IMG where product_id = ?";
			ps = conn.prepareStatement(SELECT_BY_PK);
			if(product_id != null) {
				ps.setString(1, product_id);
			} else {
				throw new Exception("must input product_id");
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				byte[] img = rs.getBytes("img");
				String base64 = Base64.getEncoder().encodeToString(img);
				imgList.add(base64);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionDB.closeJDBCConnection(conn);
			ConnectionDB.closePreparedStatement(ps);
			ConnectionDB.closeResultSet(rs);
		}
		return (imgList != null && !imgList.isEmpty()) ? imgList : null;
	}
	
	@Override
	public String selectMainImg(String product_id) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String base64 = "";
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PK = "select * from AB_PRODUCT_IMG where product_id = ? AND item = '1'";
			ps = conn.prepareStatement(SELECT_BY_PK);
			if(product_id != null) {
				ps.setString(1, product_id);
			} else {
				throw new Exception("must input product_id");
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				byte[] img = rs.getBytes("img");
				base64 = Base64.getEncoder().encodeToString(img);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionDB.closeJDBCConnection(conn);
			ConnectionDB.closePreparedStatement(ps);
			ConnectionDB.closeResultSet(rs);
		}
		return base64;
	}
	
	
//	@Override
//	public List<ProductImgBean> selectByPrimaryKey(String product_id) throws SQLException, Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		List<ProductImgBean> beans = null;
//		
//		try {
//			conn = ConnectionDB.getConnection("amberDS");
//			
//			String SELECT_BY_PK = "select * from AB_PRODUCT_IMG where product_id = ?";
//			ps = conn.prepareStatement(SELECT_BY_PK);
//			if(product_id != null) {
//				ps.setString(1, product_id);
//			} else {
//				throw new Exception("must input product_id");
//			}
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
//		return (beans != null && !beans.isEmpty()) ? beans : null;
//	}

	@Override
	public int insert(List<ProductImgBean> beans, String productId) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			for(ProductImgBean record : beans) {
				// Check current item no.
				String SELECT_MAX_ITEM = "select MAX(item) from AB_PRODUCT_IMG where product_id = ?";
				ps = conn.prepareStatement(SELECT_MAX_ITEM);
				ps.setString(1, productId);
				rs = ps.executeQuery();
				
				String maxItem = "1";
				while (rs.next()) {
					if(rs.getString(1) != null) {
						maxItem = SerialUtil.increment(maxItem, "%01d");
					}
				}
				
				//INSERT
				String INSERT = "insert into AB_PRODUCT_IMG values(?,?,?)";
				ps = conn.prepareStatement(INSERT);
				
				ps.setString(1, productId);
				ps.setString(2, maxItem);
				ps.setBytes(3, record.getImg());
				
				count = ps.executeUpdate();
				System.out.println("insert count : " + count);
			}
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
	
	
//	@Override
//	public int insert(ProductImgBean record) throws SQLException, Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		int count = 0;
//		
//		try {
//			conn = ConnectionDB.getConnection("amberDS");
//			
//			// Check current item no.
//			String SELECT_MAX_ITEM = "select MAX(item) from AB_PRODUCT_IMG where product_id = ?";
//			ps = conn.prepareStatement(SELECT_MAX_ITEM);
//			ps.setString(1, "00001");
//			rs = ps.executeQuery();
//			
//			Integer maxItem = 1;
//			while (rs.next()) {
//				if(rs.getString(1) != null) {
//					maxItem = Integer.parseInt(rs.getString(1)) + 1;
//				}
//			}
//			
//			//INSERT
//			String INSERT = "insert into AB_PRODUCT_IMG values(?,?,?)";
//			ps = conn.prepareStatement(INSERT);
//			if (record.getProductId() != null) {
//				ps.setString(1, record.getProductId());
//			} else {
//				throw new Exception("must input ProductId");
//			}
//			
//			ps.setString(2, maxItem.toString());
//			ps.setBytes(3, record.getImg());
//			
//			count = ps.executeUpdate();
//			System.out.println("insert count : " + count);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			ConnectionDB.closeJDBCConnection(conn);
//			ConnectionDB.closePreparedStatement(ps);
//			ConnectionDB.closeResultSet(rs);
//		}
//		return count;
//	}

	@Override
	public int insertSelective(ProductImgBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(ProductImgBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ProductImgBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static List<ProductImgBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<ProductImgBean> resultList = new ArrayList<ProductImgBean>();
		while (rs.next()) {
			ProductImgBean beam = new ProductImgBean();
			beam.setProductId(rs.getString("product_id"));
			beam.setItem(rs.getString("item"));
			beam.setImg(Base64.getEncoder().encode(rs.getBytes("img")));
//			beam.setImg(Base64.getEncoder().encodeToString(rs.getBytes(3)));
			
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}

		return resultList;
	}
}
