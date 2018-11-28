package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ODBean;
import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.OrderService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.service.impl.OrderServiceImpl;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
import com.amber.ShoppingApp.service.impl.UserServiceImpl;

@WebServlet("/bill")
public class BillServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String view = "/jsp/bill.jsp";
		
		if (action != null && action.equals("sendOrder")) {
			sendOrder(request, response);
			return;
		} 
			
		showCart(request,response);
	}

	//查看目前購物車及總額
	private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductService service = new ProductServiceImpl();
		String view = "/jsp/bill.jsp";
		Integer totalPrice = 0;
		String s_stName = request.getParameter("stName")==null?"":request.getParameter("stName");
//		System.out.println(s_stName);
//		s_stName = new String(s_stName.getBytes("ISO8859_1"),"UTF-8");
//		System.out.println(s_stName);
		request.setAttribute("stname", s_stName);
    	try {
			Map<ProductBean, String> map = service.checkCookie(request);
			request.setAttribute("beanCart", map);
			for (Map.Entry<ProductBean, String> entry : map.entrySet()) {
				//計算商品總額
				Integer qty = Integer.parseInt(entry.getValue());
				Integer price = entry.getKey().getPrice();
				totalPrice += price * qty;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("s_stName", s_stName);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService service = new OrderServiceImpl();
		ProductService ps = new ProductServiceImpl();
		String view = "/jsp/orderComplete.jsp";
		try {
			request = service.confirmOrder(request, response);
		} catch (Exception e) {
			view = "/jsp/bill.jsp";
			request.setAttribute("message", "出錯");
			e.printStackTrace();
		}
		
		//訂單完成 清除購物車
		Cookie cookie[]= request.getCookies();
		ProductBean bean = new ProductBean();
    	for (int i = 0; i < cookie.length; i++) {
    		String productId = cookie[i].getName();
    		try {
				bean = ps.selectByPrimaryKey(productId);
	    		if (bean != null) {
	    			cookie[i].setValue("");
	    			cookie[i].setMaxAge(0);
	    			response.addCookie(cookie[i]);
	    		}
			} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
}