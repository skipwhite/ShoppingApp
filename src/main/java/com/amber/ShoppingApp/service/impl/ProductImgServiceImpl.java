package com.amber.ShoppingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.dao.ProductImgDAO;
import com.amber.ShoppingApp.dao.impl.ProductImgDAOImpl;
import com.amber.ShoppingApp.model.ProductImgBean;
import com.amber.ShoppingApp.service.ProductImgService;

public class ProductImgServiceImpl implements ProductImgService {

	@Override
	public List<ProductImgBean> selectAll() throws SQLException, Exception {
		ProductImgDAO dao = new ProductImgDAOImpl();
		return dao.selectAll();
	}

	@Override
	public List<String> selectByPrimaryKey(String productId) throws SQLException, Exception {
		ProductImgDAO dao = new ProductImgDAOImpl();
		return dao.selectByPrimaryKey(productId);
	}

	@Override
	public String selectMainImg(String product_id) throws SQLException, Exception {
		ProductImgDAO dao = new ProductImgDAOImpl();
		return dao.selectMainImg(product_id);
	}
	
//	@Override
//	public int insert(ProductImgBean record) throws SQLException, Exception {
//		ProductImgDAO dao = new ProductImgDAOImpl();
//		return dao.insert(record);
//	}

	@Override
	public int insert(List<ProductImgBean> beans, String productId) throws SQLException, Exception {
		ProductImgDAO dao = new ProductImgDAOImpl();
		return dao.insert(beans,productId);
	}

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
		ProductImgDAO dao = new ProductImgDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String productId) throws SQLException, Exception {
		ProductImgDAO dao = new ProductImgDAOImpl();
		return dao.deleteByPrimaryKey(productId);
	}





}
