//package com.amber.ShoppingApp.service.impl;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import com.amber.ShoppingApp.dao.ProductCommentDAO;
//import com.amber.ShoppingApp.dao.TestDAO;
//import com.amber.ShoppingApp.dao.impl.ProductCommentDAOImpl;
//import com.amber.ShoppingApp.dao.impl.TestDAOImpl;
//import com.amber.ShoppingApp.model.ProductCommentBean;
//import com.amber.ShoppingApp.model.TestBean;
//import com.amber.ShoppingApp.service.ProductCommentService;
//
//public class ProductCommentServiceImpl implements ProductCommentService {
//
//	@Override
//	public List<TestBean> selectAll() throws SQLException, Exception {
//		TestDAO dao = new TestDAOImpl();
//		return dao.selectAll();
//	}
//
//	@Override
//	public List<ProductCommentBean> selectByProduct(String productId) throws SQLException, Exception {
//		ProductCommentDAO dao = new ProductCommentDAOImpl();
//		return dao.selectByProduct(productId);
//	}
//
//	@Override
//	public ProductCommentBean selectByPrimaryKey(String userId) throws SQLException, Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int insert(ProductCommentBean record) throws SQLException, Exception {
//		ProductCommentDAO dao = new ProductCommentDAOImpl();
//		return dao.insert(record);
//	}
//
//	@Override
//	public int insertSelective(ProductCommentBean record) throws SQLException, Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(ProductCommentBean record) throws SQLException, Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateByPrimaryKey(ProductCommentBean record) throws SQLException, Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int deleteByPrimaryKey(String userId) throws SQLException, Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//
//}
