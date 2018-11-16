package com.amber.ShoppingApp.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.dao.ProductCommentDAO;
import com.amber.ShoppingApp.dao.TestDAO;
import com.amber.ShoppingApp.dao.impl.ProductCommentDAOImpl;
import com.amber.ShoppingApp.dao.impl.TestDAOImpl;
import com.amber.ShoppingApp.model.ProductCommentBean;
import com.amber.ShoppingApp.model.ProductImgBean;
import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.service.OrderDetailService;
import com.amber.ShoppingApp.service.ProductCommentService;
import com.amber.ShoppingApp.util.ConnectionDB;
import com.amber.ShoppingApp.util.SerialUtil;

public class ProductCommentServiceImpl implements ProductCommentService {

	@Override
	public List<ProductCommentBean> selectAll() throws SQLException, Exception {
		ProductCommentDAO dao = new ProductCommentDAOImpl();
		return dao.selectAll();
	}

	@Override
	public List<ProductCommentBean> selectByProduct(String productId) throws SQLException, Exception {
		ProductCommentDAO dao = new ProductCommentDAOImpl();
		return dao.selectByProduct(productId);
	}

	@Override
	public ProductCommentBean selectByPrimaryKey(String userId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ProductCommentBean record) throws SQLException, Exception {
		ProductCommentDAO dao = new ProductCommentDAOImpl();
		return dao.insert(record);
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
	public int deleteByPrimaryKey(String userId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int createComment(ProductCommentBean record, String poNo) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			// Check current ProductId
			String SELECT_MAX_ID = "select MAX(comment_id) from AB_PRODUCT_COMMENT";
			ps = conn.prepareStatement(SELECT_MAX_ID);
			rs = ps.executeQuery();
			
			String maxCommentId = "0";
			while (rs.next()) {
				if(rs.getString(1) != null) {
					maxCommentId = rs.getString(1);
				}
			}
			String commentId = SerialUtil.increment(maxCommentId, "%05d");
			
			String INSERT = "insert into AB_PRODUCT_COMMENT (comment_id, product_id, user_id, rate, comment) values(?,?,?,?,?)";
			ps = conn.prepareStatement(INSERT);
			
			ps.setString(1, commentId);
			ps.setString(2,record.getProductId());
			ps.setString(3,record.getUserId());
			ps.setBigDecimal(4,new BigDecimal(record.getRate().toString()));
			if (record.getComment() != null) {
				ps.setString(5,record.getComment());
			} else {
				ps.setString(5, null);
			}
//			ps.setTimestamp(6, new java.sql.Timestamp(record.getTimestamp().getTime()));
			
			
			count = ps.executeUpdate();
			System.out.println("product insert count : " + count);
			if (count != 0) {
				//update OrderDetailBean
				OrderDetailService ods = new OrderDetailServiceImpl();
				ods.updateIsComment(poNo, record.getProductId());
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


}
