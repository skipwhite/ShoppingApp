package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ODBean;
import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.OrderService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.OrderServiceImpl;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;

@WebServlet("/bill")
public class BillServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String view = "/jsp/bill.jsp";
		//判斷是否登入
		//TODO 改用filter
		String userId = (String) request.getSession().getAttribute("login");
		if (userId == null) {
			view = "/jsp/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
			return;
		}
		
		
		if (action.equals("sendOrder")) {
			sendOrder(request, response);
		} else {
			check(request,response);
		}
		
		if (action.equals("checkOrder")) {
			try {
				checkOrder(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//查看目前購物車及總額
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		String view = "/jsp/bill.jsp";
		Integer totalPrice = 0;
		
    	try {
			Map<ProductBean, String> map = service.checkCookie(request);
			request.setAttribute("beanCart", map);
			for (Map.Entry<ProductBean, String> entry : map.entrySet()) {
				//計算商品總額
				Integer qty = Integer.parseInt(entry.getValue());
				Integer price = entry.getKey().getPrice();
				System.out.println(entry.getKey() + "/" + entry.getValue());
				System.out.println(price + "/" + qty + "before totalPrice" + totalPrice);
				totalPrice += price * qty;
				System.out.println("after totalPrice" + totalPrice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("totalPrice", totalPrice);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	//查看已完成的訂單
	private void checkOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		String view = "/jsp/orderComplete.jsp";
		OrderService service = new OrderServiceImpl();
		ODBean OD = service.checkOrder("001");
		request.setAttribute("ob", OD.getOb());
		request.setAttribute("odList", OD.getOdbl());
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService service = new OrderServiceImpl();
		String view = "/jsp/orderComplete.jsp";
		try {
			request = service.confirmOrder(request, response);
		} catch (Exception e) {
			view = "/jsp/bill.jsp";
			request.setAttribute("message", "出錯");
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
}