package com.amber.ShoppingApp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.dao.OrderDAO;
import com.amber.ShoppingApp.dao.SettingDAO;
import com.amber.ShoppingApp.dao.impl.OrderDAOImpl;
import com.amber.ShoppingApp.dao.impl.SettingDAOImpl;
import com.amber.ShoppingApp.model.ODBean;
import com.amber.ShoppingApp.model.OrderBean;
import com.amber.ShoppingApp.model.OrderDetailBean;
import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.model.SettingBean;
import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.OrderDetailService;
import com.amber.ShoppingApp.service.OrderService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.util.ConnectionDB;
import com.amber.ShoppingApp.util.SerialUtil;

public class OrderServiceImpl implements OrderService {
	private Integer totalPrice = 0;
	
	@Override
	public List<OrderBean> selectAll() throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.selectAll();
	}

	@Override
	public List<OrderBean> selectByUser(String userId) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.selectByUser(userId);
	}
	
	@Override
	public OrderBean selectByPrimaryKey(String poNo) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.selectByPrimaryKey(poNo);
	}

	@Override
	public int insert(OrderBean record) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.insert(record);
	}

	@Override
	public int insertSelective(OrderBean record) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderBean record) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderBean record) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String poNo) throws SQLException, Exception {
		OrderDAO dao = new OrderDAOImpl();
		return dao.deleteByPrimaryKey(poNo);
	}
	
	public ODBean checkOrder(String poNo) throws SQLException, Exception {
		OrderBean ob = selectByPrimaryKey(poNo);
		OrderDetailService ods = new OrderDetailServiceImpl();
		List<OrderDetailBean> odList = ods.selectAllItem(poNo);
		ODBean OD = new ODBean(ob,odList);
		return OD;
	}
	
	public HttpServletRequest confirmOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService service = new ProductServiceImpl();
		Map<ProductBean, String> map = new HashMap<>();
		String poNo = "";
		//取得購物車
		try {
			map = service.checkCookie(request);
			request.setAttribute("map", map);
		} catch (Exception e) {
			System.out.println("取得購物車失敗");
			e.printStackTrace();
		}
		
		// 取得poNo且insert OrderDetailBean
		try {
			poNo = createOrderDetail(map);
		} catch (Exception e) {
			System.out.println("createOrderDetail 失敗");
			e.printStackTrace();
		}
		
		// PROCESS ORDER BEAN
		//取得userbean
//		String userId = (String) request.getSession().getAttribute("login");
		UserService us = new UserServiceImpl();
//		UserBean ub = us.selectByPrimaryKey(userId);
 		UserBean ub = (UserBean) request.getSession().getAttribute("login");
		
		//取得SettingBean
		String shipId = request.getParameter("shipId");
		System.out.println(shipId);
		String payId = request.getParameter("payId");
		
		SettingDAO ss = new SettingDAOImpl();
		SettingBean shipBean = ss.selectByPrimaryKey("ship_id", shipId);
		SettingBean payBean = ss.selectByPrimaryKey("pay_id", payId);
		
		//產生OrderBean內容
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String shipStore = request.getParameter("shipStore");
		String zipCode = request.getParameter("zipCode");
		String address = request.getParameter("address");
		
		OrderBean ob = new OrderBean(poNo, ub.getUserId(), name, phone, shipId, shipBean.getValue(),
				shipStore, payId, payBean.getValue(), zipCode, address, totalPrice, "收到訂單", false, false);
		
		try {
			insertSelective(ob);
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("ob insert 失敗");
		}
		
		// 更新User資訊
		ub.setPhone(phone);
		ub.setShipStore(shipStore);
		ub.setZipCode(zipCode);
		ub.setAddress(address);
		us.updateByPrimaryKeySelective(ub);
		
		//取得完成後的訂單
		ODBean OD;
		try {
			OD = checkOrder(poNo);
			request.setAttribute("ob", OD.getOb());
			request.setAttribute("odList", OD.getOdbl());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("checkOrder 失敗");
		}
		return request;
	}
	
	// 回傳poNo且insert OrderDetailBean
	private String createOrderDetail(Map<ProductBean, String> map) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String poNo = "";
		
		//指定poNo，檢查是否有今日的訂單 
		try {
			conn = ConnectionDB.getConnection("amberDS");
			
//			String CHECK_TODAY_PO = "select curdate() = substring(po_no, 1,8) from AB_ORDER_DTL";
			String CHECK_TODAY_PO = "SELECT * FROM (select (curdate() = substring(po_no, 1,8)) "
								  + "as result from AB_ORDER_DTL) as t1 " 
								  + "where result = 1 LIMIT 1";
			ps = conn.prepareStatement(CHECK_TODAY_PO);
			rs = ps.executeQuery();
			Boolean todayHasPo = null;
			String maxPo = null;
			while (rs.next()) {
				todayHasPo = rs.getBoolean(1);
			}
			
			if(todayHasPo != null && todayHasPo) {
				String SELECT_TODAY_MAX_PO = "select MAX(po_no) from AB_ORDER_DTL where curdate() = substring(po_no, 1,8)";
				ps = conn.prepareStatement(SELECT_TODAY_MAX_PO);
				rs = ps.executeQuery();
				while (rs.next()) {
					maxPo = rs.getString(1);
				}
				maxPo = maxPo.substring(8);
				
			} else {
				maxPo = "0";
			}
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			poNo = sdf.format(date) + SerialUtil.increment(maxPo, "%06d");
			
			int count = 1;
			//處理OrderDetailBean, 從購物車取出每個item
			for (Map.Entry<ProductBean, String> entry : map.entrySet()) {
				String maxItem = null;
				//指定item，選出目前最大item 
				String SELECT_MAX_ITEM = "select MAX(item) from AB_ORDER_DTL where po_no = '" + poNo + "'" ;
				ps = conn.prepareStatement(SELECT_MAX_ITEM);
				rs = ps.executeQuery();
				while (rs.next()) {
					maxItem = rs.getString(1);
				}
				if(maxItem == null) {
					maxItem = "0";
				}
				maxItem = SerialUtil.increment(maxItem, "%d");
				
				//指定productId category qty price isCommented
				String productId = entry.getKey().getProductId();
				String category = entry.getKey().getCategory();
				Integer qty = Integer.parseInt(entry.getValue());
				Integer price = entry.getKey().getPrice();
				OrderDetailBean odb = new OrderDetailBean(maxItem, poNo, productId, category, qty, price, false);
				OrderDetailService ods = new OrderDetailServiceImpl();
				try {
					ods.insert(odb);
					ProductService pService = new ProductServiceImpl();
					pService.updateInvSalesByPK(productId, qty);
					count++;
				} catch (Exception e){
					e.printStackTrace();
					throw new Exception("odb insert 失敗 row " + count);
				}
				//計算totalPrice
				totalPrice += price * qty;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionDB.closeJDBCConnection(conn);
			ConnectionDB.closePreparedStatement(ps);
			ConnectionDB.closeResultSet(rs);
		}
	
		
		return poNo;
	}
	

	
	
	@Override
	public List<ODBean> myOrder(String userId) {
		List<ODBean> ODList = new ArrayList<>();
		OrderDetailService ods = new OrderDetailServiceImpl();
		String poNo = "";
		try {
			//用userId撈出OrderBean
			List<OrderBean> obList = selectByUser(userId);
			for (OrderBean ob : obList) {
				ODBean OD = new ODBean();
				poNo = ob.getPoNo();
				//用poNo撈OrderDetailBean
				List<OrderDetailBean> odList = ods.selectAllItem(poNo);
				//把OrderBean和OrderDetailBean塞進OD
				OD.setOb(ob);
				OD.setOdbl(odList);
				ODList.add(OD);
			}
		} catch (Exception e){
			System.out.println("myOrder出錯");
			e.printStackTrace();
		}
		return ODList;
	}


}
