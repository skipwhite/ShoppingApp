package com.amber.ShoppingApp.service.impl;

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
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.dao.ProductDAO;
import com.amber.ShoppingApp.dao.impl.ProductDAOImpl;
import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.model.ProductImgBean;
import com.amber.ShoppingApp.service.ProductImgService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.util.ConnectionDB;
import com.amber.ShoppingApp.util.SerialUtil;
import com.mysql.fabric.Response;

public class ProductServiceImpl implements ProductService {
	@Override
	public List<ProductBean> selectAll() throws SQLException, Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.selectAll();
	}

	@Override
	public ProductBean selectByPrimaryKey(String productId) throws SQLException, Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.selectByPrimaryKey(productId);
	}

	@Override
	public int insert(ProductBean record) throws SQLException, Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.insert(record);
	}

	@Override
	public int insertSelective(ProductBean record, List<ProductImgBean> pibs) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			// Check current ProductId
			String SELECT_MAX_ID = "select MAX(product_id) from AB_PRODUCT";
			ps = conn.prepareStatement(SELECT_MAX_ID);
			rs = ps.executeQuery();
			
			Integer maxProductId = 0;
			while (rs.next()) {
				if(rs.getString(1) != null) {
					maxProductId = Integer.parseInt(rs.getString(1));
				}
			}
			String productId = SerialUtil.increment(maxProductId.toString(), "%05d");

			
			String INSERT = "insert into AB_PRODUCT values(?,?,?,?,?,  ?,?,?,?,?  ,? )";
			ps = conn.prepareStatement(INSERT);
			
			ps.setString(1, productId);
			ps.setString(2,record.getName());
			ps.setString(3,record.getDscr());
			ps.setString(4,record.getCategory());
			ps.setInt(5,new Integer(record.getPrice()));
			ps.setInt(6, 0);
			ps.setInt(7,new Integer(record.getInventory()));
			
			if (record.getTag() != null) {
				ps.setString(8,record.getTag());
			} else {
				ps.setString(8,null);
			}
			if (record.getDiscount() != null) {
				ps.setBigDecimal(9,new BigDecimal(record.getDiscount().toString()));
			} else {
				ps.setBigDecimal(9,new BigDecimal(1));
			}
			ps.setInt(10,0);
			if (record.getLaunched() != null) {
				ps.setBoolean(11,new Boolean(record.getLaunched()));
			} else {
				ps.setBoolean(11,new Boolean("0"));
			}
			
			count = ps.executeUpdate();
			System.out.println("product insert count : " + count);
			
			// insert product img
			count = 0;
			
			for(ProductImgBean pib : pibs) {
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
				
				INSERT = "insert into AB_PRODUCT_IMG values(?,?,?)";
				ps = conn.prepareStatement(INSERT);
				
				ps.setString(1, productId);
				ps.setString(2, maxItem);
				ps.setBytes(3, pib.getImg());
				
				count = ps.executeUpdate();
				System.out.println("product img insert count : " + count);
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
	
	@Override
	public int updateInvSalesByPK(String productId, Integer qty) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		ProductBean bean = selectByPrimaryKey(productId);
		Integer salesQty = bean.getSalesQty() + qty;
		Integer inventory = bean.getInventory() - qty; 
		if(productId == null || qty == null) {
			throw new Exception("productID qty cannot be null");
		}
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String UPDATE = "UPDATE AB_PRODUCT SET inventory = ?, sales_qty = ? where product_id='" + productId +  "'";

			ps = conn.prepareStatement(UPDATE);
			ps.setInt(1, inventory);
			ps.setInt(2, salesQty);
			
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
	public int updateByPrimaryKeySelective(ProductBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ProductBean record) throws SQLException, Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String productId) throws SQLException, Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.deleteByPrimaryKey(productId);
	}

//	@Override
//	public Map<String, String> addToCart(String addProductId, String qty, Map<String, String> cart) throws Exception{
//		if (addProductId.isEmpty()) {
//			throw new Exception("addToCart productId is empty");
//		}
//		//判斷商品是否已存在購物車中
//		String qtyInCartS = cart.get(addProductId);
//		
//		if (qtyInCartS != null) {
//			//取得購物車中的商品數量
//			Integer qtyInCart = Integer.parseInt(qtyInCartS);
//			//更新購物車中的數量
//			Integer total = new Integer(qtyInCart + Integer.parseInt(qty));
//			cart.put(addProductId, total.toString());
//		} else {
//			cart.put(addProductId, qty.toString());
//		}
//		return cart;
//	}
//	
//	@Override	//Bean		qty					  productId qty
//	public Map<ProductBean, String> checkCart(HttpServletRequest request) throws SQLException, Exception {
//		//從session取得productId和qty
//		Map<String, String> cart = (Map<String, String>) request.getSession().getAttribute("cart");
//		Map<ProductBean, String> beanCart = new HashMap<>();
//		ProductBean bean = new ProductBean();
//		
//		if(cart == null) {
//			//注意
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
	
	
	@Override
	public Map<ProductBean, String> checkCookie(HttpServletRequest request) throws SQLException, Exception {
		Cookie cookie[]= request.getCookies();
		ProductBean bean = new ProductBean();
		Map<ProductBean, String> map = new HashMap<>();
    	// Check cookie
    	for (int i = 0; i < cookie.length; i++) {
    		String productId = cookie[i].getName();
    		bean = selectByPrimaryKey(productId);
    		if (bean != null) {
    			map.put(bean, cookie[i].getValue());
    		}
    	} 
		return map;
	}
	
	@Override
	public int createProduct(ProductBean pb, List<ProductImgBean> pibs) throws SQLException, Exception {
		return insertSelective(pb, pibs);
	}
	
	@Override
	public Map<ProductBean, String> selectAllProductOneImg() throws SQLException, Exception {
		//只抓第一張圖和product table
		ProductImgService service = new ProductImgServiceImpl();
		List<ProductBean> pbs = selectAll();
		Map<ProductBean, String> map = new HashMap<>();

		for (ProductBean pb : pbs) {
			String productId = pb.getProductId();
			map.put(pb, service.selectMainImg(productId));
		}
		return map;
	}
	
//	@Override
//	public Map<ProductBean, List<String>> selectOneProduct() throws SQLException, Exception {
//		ProductImgService service = new ProductImgServiceImpl();
//		List<ProductBean> pbs = selectAll();
//		Map<ProductBean, List<String>> map = new HashMap<>();
//
//		for (ProductBean pb : pbs) {
//			String productId = pb.getProductId();
//			map.put(pb, service.selectByPrimaryKey(productId));
//		}
//		return map;
//	}
}
