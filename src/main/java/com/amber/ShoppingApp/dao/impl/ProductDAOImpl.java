package com.amber.ShoppingApp.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.amber.ShoppingApp.dao.ProductDAO;
import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public List<ProductBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from AB_PRODUCT";
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
	public ProductBean selectByPrimaryKey(String productId) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PK = "select * from AB_PRODUCT where product_id = ?";
			ps = conn.prepareStatement(SELECT_BY_PK);
			if(productId != null) {
				ps.setString(1, productId);
			} else {
				throw new Exception("must input productId");
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
	public int insert(ProductBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String INSERT = "insert into AB_PRODUCT values(?,?,?,?,?,  ?,?,?,?,?  ,? )";
			ps = conn.prepareStatement(INSERT);
			if (record.getProductId() != null) {
				ps.setString(1, record.getProductId());
			} else {
				throw new Exception("must input ProductId");
			}
			
			ps.setString(2,record.getName());
			ps.setString(3,record.getDscr());
			ps.setString(4,record.getCategory());
			ps.setInt(5,new Integer(record.getPrice()));
			ps.setInt(6,new Integer(record.getViewCount()));
			ps.setInt(7,new Integer(record.getInventory()));
			ps.setString(8,record.getTag());
			ps.setBigDecimal(9,new BigDecimal(record.getDiscount().toString()));
			ps.setInt(10,new Integer(record.getSalesQty()));
			ps.setBoolean(11,new Boolean(record.getLaunched()));
			
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
	public int insertSelective(ProductBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(ProductBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ProductBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String userId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public static List<ProductBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<ProductBean> resultList = new ArrayList<ProductBean>();
		while (rs.next()) {
			ProductBean beam = new ProductBean();
			beam.setProductId(rs.getString("product_id"));
			beam.setName(rs.getString("name"));
			beam.setDscr(rs.getString("dscr"));
			beam.setCategory(rs.getString("category"));
			beam.setPrice(rs.getInt("price"));
			
			beam.setViewCount(rs.getInt("view_count"));
			beam.setInventory(rs.getInt("inventory"));
			beam.setTag(rs.getString("tag"));
			beam.setDiscount(rs.getBigDecimal("discount"));
			beam.setSalesQty(rs.getInt("sales_qty"));
			
			beam.setLaunched(rs.getBoolean("launched"));
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}

//	@Override
//	public Map<String, ProductBean> checkCart(Cookie[] cookie) throws SQLException, Exception {
////		ProductBean bean = new ProductBean();
//		Map<String, ProductBean> map = new HashMap<>();
////    	for( int i = 0; i < cookie.length; i++) {
////    		if(!cookie[i].getName().isEmpty() || !cookie[i].getValue().isEmpty()) {
////    			bean = selectByPrimaryKey(cookie[i].getName());
////    			// Sort cookie for real product ID
////    			if (bean != null) {
////    				map.put(bean, cookie[i].getValue());
////    			}
////    		}
////    	}
//		return map;
//	}
	
//	@Override	//Bean		qty					  productId qty
//	public Map<ProductBean, String> checkCart(Map<String, String> cart) throws SQLException, Exception {
//		ProductBean bean = new ProductBean();
//		Map<ProductBean, String> beanCart = new HashMap<>();
//		
//		if(cart == null) {
//			throw new Exception("checkCart cart is null");
//		}
//		
//		for(Map.Entry<String, String> entry : cart.entrySet()) {
//			//從Map取出productId
//			String productId = entry.getKey();
//			if(productId == null) {
//				throw new Exception("checkCart productId is null");
//			}
//			//用productId取出bean
//			bean = selectByPrimaryKey(productId);
//			//把bean, qty放入新的Map
//			beanCart.put(bean, entry.getValue());
//		}
//		return beanCart;
//	}
	
	@Override	//Bean		qty					  productId qty
	public Map<ProductBean, String> checkCart(HttpServletRequest request) throws SQLException, Exception {
		//從session取得productId和qty
		Map<String, String> cart = (Map<String, String>) request.getSession().getAttribute("cart");
		Map<ProductBean, String> beanCart = new HashMap<>();
		ProductBean bean = new ProductBean();
		
		if(cart == null) {
			//注意
			throw new Exception("checkCart cart is null");
		}
		
		for(Map.Entry<String, String> entry : cart.entrySet()) {
			//從Map取出productId
			String productId = entry.getKey();
			if(productId == null) {
				throw new Exception("checkCart productId is null");
			}
			//用productId取出bean
			bean = selectByPrimaryKey(productId);
			//把bean, qty放入新的Map
			beanCart.put(bean, entry.getValue());
		}
		return beanCart;
	}
	
	@Override
	public Map<String, String> addToCart(String addProductId, String qty, Map<String, String> cart) throws Exception{
		if (addProductId.isEmpty()) {
			throw new Exception("addToCart productId is empty");
		}
		//判斷商品是否已存在購物車中
		String qtyInCartS = cart.get(addProductId);
		
		if (qtyInCartS != null) {
			//取得購物車中的商品數量
			Integer qtyInCart = Integer.parseInt(qtyInCartS);
			//更新購物車中的數量
			Integer total = new Integer(qtyInCart + Integer.parseInt(qty));
			cart.put(addProductId, total.toString());
		} else {
			cart.put(addProductId, qty.toString());
		}
		return cart;
	}
	
}
