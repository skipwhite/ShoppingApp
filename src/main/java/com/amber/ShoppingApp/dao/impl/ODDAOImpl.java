package com.amber.ShoppingApp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amber.ShoppingApp.dao.ODDAO;
import com.amber.ShoppingApp.model.OrderDetailBean;
import com.amber.ShoppingApp.model.noUse.ODBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class ODDAOImpl implements ODDAO {

	@Override
	public List<ODBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ODBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from ab_order,ab_order_dtl where ab_order.po_no = ab_order_dtl.po_no";
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
	public ODBean selectByPrimaryKey(String poNo) throws SQLException, Exception {
		return null;
	}

	@Override
	public int insert(ODBean record) throws SQLException, Exception {
		return 0;
	}

	@Override
	public int insertSelective(ODBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(ODBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ODBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String poNo) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static List<ODBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<ODBean> resultList = new ArrayList<ODBean>();
		while (rs.next()) {
			ODBean beam = new ODBean();
			// OrderBean
			beam.setPoNo(rs.getString("po_no"));
			beam.setUserId(rs.getString("user_id"));
			beam.setTotal(rs.getInt("total"));
			System.out.println(rs.getString("po_no")+rs.getString("user_id")+rs.getInt("total"));

			List<OrderDetailBean> odbList = new ArrayList<>();
			//OrderDetailBean
			OrderDetailBean odb = new OrderDetailBean(rs.getString("item"),rs.getString("po_no"),rs.getString("product_id"),rs.getInt("qty"));
			System.out.println(rs.getString("item")+rs.getString("po_no")+rs.getString("product_id")+rs.getInt("qty"));
			//List<OrderDetailBean>
			odbList.add(odb);
				
			beam.setOdbList(odbList);
			
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}

		return resultList;
	}

}
