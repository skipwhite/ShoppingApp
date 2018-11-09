package com.amber.ShoppingApp.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.amber.ShoppingApp.dao.ProductDAO;
import com.amber.ShoppingApp.dao.impl.ProductDAOImpl;
import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductService;

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
		ProductDAO dao = new ProductDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String productId) throws SQLException, Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.deleteByPrimaryKey(productId);
	}


	@Override
	public Map<String, String> addToCart(String addProductId, String qty, Map<String, String> cart)
			throws Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.addToCart(addProductId, qty, cart);
	}

	@Override
	public Map<ProductBean, String> checkCart(HttpServletRequest request) throws SQLException, Exception {
		ProductDAO dao = new ProductDAOImpl();
		return dao.checkCart(request);
	}
}
