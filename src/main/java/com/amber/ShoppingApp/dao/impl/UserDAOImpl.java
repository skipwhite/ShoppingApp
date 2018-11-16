package com.amber.ShoppingApp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amber.ShoppingApp.dao.UserDAO;
import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.util.ConnectionDB;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<UserBean> selectAll() throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UserBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_ALL = "select * from AB_USER";
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
	public UserBean selectByPrimaryKey(String userId) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UserBean> beans = null;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String SELECT_BY_PK = "select * from AB_USER where user_id = ?";
			ps = conn.prepareStatement(SELECT_BY_PK);
			if(userId != null) {
				ps.setString(1, userId);
			} else {
				throw new Exception("must input userId UserDAO");
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
	public int insert(UserBean record) throws SQLException, Exception {
		return 0;
	}

	@Override
	public int insertSelective(UserBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String INSERT = "insert into AB_USER values(?,?,?,?,? ,?,?,?,?)";
			ps = conn.prepareStatement(INSERT);
			if (record.getUserId() != null) {
				ps.setString(1, record.getUserId());
			} else {
				throw new Exception("must input userId insert");
			}
			
			ps.setString(2, new String(record.getName()));
			ps.setString(3, new String(record.getEmail()));
			ps.setString(4, new String(record.getPassword()));
			
			if (record.getZipCode() != null) {
				ps.setString(5, record.getZipCode());
			} else {
				ps.setString(5, null);
			}
			if (record.getAddress() != null) {
				ps.setString(6, record.getAddress());
			} else {
				ps.setString(6, null);
			}
			if (record.getPhone() != null) {
				ps.setString(7, record.getPhone());
			} else {
				ps.setString(7, null);
			}
			if (record.getShipStore() != null) {
				ps.setString(8, record.getShipStore());
			} else {
				ps.setString(8, null);
			}
			if (record.getRole() != null) {
				ps.setString(9, record.getRole());
			} else {
				ps.setString(9, null);
			}
			
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
	public int updateByPrimaryKeySelective(UserBean record) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
			String UPDATE = "update AB_USER set name = ?, email = ?, zip_code = ?, address = ?, phone = ?, ship_store = ?" + "where user_id = ?";
			ps = conn.prepareStatement(UPDATE);
			if (record.getUserId() != null) {
				ps.setString(7, record.getUserId());
			} else {
				throw new Exception("must input userId");
			}
			ps.setString(1, new String(record.getName()));
			ps.setString(2, new String(record.getEmail()));
			if (record.getZipCode() != null) {
				ps.setString(3, record.getZipCode());
			} else {
				ps.setString(3, null);
			}
			if (record.getAddress() != null) {
				ps.setString(4, record.getAddress());
			} else {
				ps.setString(4, null);
			}
			if (record.getPhone() != null) {
				ps.setString(5, record.getPhone());
			} else {
				ps.setString(5, null);
			}
			if (record.getShipStore() != null) {
				ps.setString(6, record.getShipStore());
			} else {
				ps.setString(6, null);
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
	public int updateByPrimaryKey(UserBean record) throws SQLException, Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		int count = 0;
//		
//		try {
//			conn = ConnectionDB.getConnection("amberDS");
//			
//			String UPDATE = "update ab_user set name = ?, email = ?, password = ?, salt = ?" + "where user_id = ?";
//			ps = conn.prepareStatement(UPDATE);
//			if (record.getUserId() != null) {
//				ps.setString(5, record.getUserId());
//			} else {
//				throw new Exception("must input userId");
//			}
//			ps.setString(1, new String(record.getName()));
//			ps.setString(2, new String(record.getEmail()));
//			ps.setString(3, new String(record.getPassword()));
//			ps.setLong(4, new Integer(record.getSalt()));
//			
//			count = ps.executeUpdate();
//			System.out.println("update count : " + count);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			ConnectionDB.closeJDBCConnection(conn);
//			ConnectionDB.closePreparedStatement(ps);
//			ConnectionDB.closeResultSet(rs);
//		}
//		return count;
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String userId) throws SQLException, Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		int count = 0;
//		
//		try {
//			conn = ConnectionDB.getConnection("amberDS");
//			
//			String DELETE = "select * from AB_USER where user_id = ?";
//			ps = conn.prepareStatement(DELETE);
//			if(userId != null) {
//				ps.setString(1, userId);
//			} else {
//				throw new Exception("must input userId");
//			}
//			count = ps.executeUpdate();
//			System.out.println("delete count : " + count);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			ConnectionDB.closeJDBCConnection(conn);
//			ConnectionDB.closePreparedStatement(ps);
//			ConnectionDB.closeResultSet(rs);
//		}
//		return count;
		return 0;
	}
	
	
	public static List<UserBean> getBeans(ResultSet rs) throws SQLException{
		int count = 0;
		List<UserBean> resultList = new ArrayList<UserBean>();
		while (rs.next()) {
			UserBean beam = new UserBean();
			beam.setUserId(rs.getString("user_id"));
			beam.setName(rs.getString("name"));
			beam.setEmail(rs.getString("email"));
			beam.setPassword(rs.getString("password"));
			beam.setZipCode(rs.getString("zip_code"));
			beam.setAddress(rs.getString("address"));
			beam.setPhone(rs.getString("phone"));
			beam.setShipStore(rs.getString("ship_store"));
			beam.setRole(rs.getString("role"));
			System.out.println((++count) + ". " + beam.toString());

			resultList.add(beam);
		}
		return resultList;
	}
}
