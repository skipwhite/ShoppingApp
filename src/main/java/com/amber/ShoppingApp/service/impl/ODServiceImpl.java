package com.amber.ShoppingApp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.amber.ShoppingApp.dao.ODDAO;
import com.amber.ShoppingApp.dao.impl.ODDAOImpl;
import com.amber.ShoppingApp.model.noUse.ODBean;
import com.amber.ShoppingApp.service.ODService;

public class ODServiceImpl implements ODService {

	@Override
	public List<ODBean> selectAll() throws SQLException, Exception {
		ODDAO dao = new ODDAOImpl();
		return dao.selectAll();
	}

	@Override
	public ODBean selectByPrimaryKey(String poNo) throws SQLException, Exception {
		ODDAO dao = new ODDAOImpl();
		return dao.selectByPrimaryKey(poNo);
	}

	@Override
	public int insert(ODBean record) throws SQLException, Exception {
		ODDAO dao = new ODDAOImpl();
		return dao.insert(record);
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
		ODDAO dao = new ODDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String poNo) throws SQLException, Exception {
		ODDAO dao = new ODDAOImpl();
		return dao.deleteByPrimaryKey(poNo);
	}


}
