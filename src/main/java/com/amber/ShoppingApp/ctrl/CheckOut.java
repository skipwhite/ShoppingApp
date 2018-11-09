package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ODBean;
import com.amber.ShoppingApp.model.OrderBean;
import com.amber.ShoppingApp.model.OrderDetailBean;
import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ODService;
import com.amber.ShoppingApp.service.OrderDetailService;
import com.amber.ShoppingApp.service.OrderService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ODServiceImpl;
import com.amber.ShoppingApp.service.impl.OrderDetailServiceImpl;
import com.amber.ShoppingApp.service.impl.OrderServiceImpl;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
import com.amber.ShoppingApp.util.SerialUtil;

@WebServlet("/checkOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sendOrder = request.getParameter("sendOrder");
		String checkOrder = request.getParameter("checkOrder");
		String view = "/jsp/checkOut.jsp";
		
		//判斷是否登入
		String userId = (String) request.getSession().getAttribute("login");
		if (userId == null) {
			view = "/jsp/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
			return;
		}

		//在結帳頁面按下結帳,轉至訂單完成頁面
		if(sendOrder != null) {
			sendOrder(request,response);
			return;
		}
		if(checkOrder != null) {
			try {
				checkOrder(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		//轉至結帳頁面
		check(request,response);
	}
	
	private void checkOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		String view = "/jsp/orderComplete.jsp";
		//取得OrderBean
		OrderService os = new OrderServiceImpl();
		OrderBean ob = os.selectByPrimaryKey("00000000000001");
		//取得OrderDetailBean
		OrderDetailService ods = new OrderDetailServiceImpl();
		List<OrderDetailBean> odList = ods.selectAllItem("00000000000001");
		request.setAttribute("ob", ob);
		request.setAttribute("odList", odList);
		
		
//		//取出ODBean顯示在畫面
//		ODService odService = new ODServiceImpl();
//		try {
//			List<ODBean> ODbList = odService.selectAll();
//			ODBean ODb = ODbList.get(0);
//			System.out.println(ODbList.get(0));
//			System.out.println(ODbList.get(1));
//			request.setAttribute("ODb", ODb);			
//			request.setAttribute("ODbList", ODbList);			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		String view = "/jsp/checkOut.jsp";
		Integer total = 0;
		
		//取得購物車
		try {
			Map<ProductBean, String> beanCart = service.checkCart(request);
			request.getSession().setAttribute("beanCart", beanCart);
			for (Map.Entry<ProductBean, String> entry : beanCart.entrySet()) {
				//計算商品總額
				Integer qty = Integer.parseInt(entry.getValue());
				Integer price = entry.getKey().getPrice();
				System.out.println(entry.getKey() + "/" + entry.getValue());
				System.out.println(price + "/" + qty + "before total" + total);
				total += price * qty;
				System.out.println("after total" + total);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("total", total);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	
	private void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		OrderService os = new OrderServiceImpl();
		OrderDetailService ods = new OrderDetailServiceImpl();
		String view = "/jsp/orderComplete.jsp";
		Integer total = 0;
		String poNo = "";
		String userId = (String) request.getSession().getAttribute("login");
		
		//取得購物車
		try {
			Map<ProductBean, String> beanCart = service.checkCart(request);
			request.getSession().setAttribute("beanCart", beanCart);
			for (Map.Entry<ProductBean, String> entry : beanCart.entrySet()) {
				//處理OrderDetailBean, 從購物車取出每個item
				Integer item = 1;
				poNo = SerialUtil.increment("0", "%014d");
				String productId = entry.getKey().getProductId();
				Integer qty = Integer.parseInt(entry.getValue());
				System.out.println(poNo);
				OrderDetailBean odb = new OrderDetailBean(item.toString(), poNo, productId, qty);
				try {
					ods.insert(odb);
				} catch (Exception e){
					e.printStackTrace();
					throw new Exception("odb insert 失敗");
				}
				//計算商品總額
				Integer price = entry.getKey().getPrice();
				System.out.println(entry.getKey() + "/" + entry.getValue());
				System.out.println(price + "/" + qty + "before total" + total);
				total += price * qty;
				System.out.println("after total" + total);
			}
			
			//處理OrderBean
			OrderBean ob = new OrderBean(poNo, userId, total);
			try {
				os.insert(ob);
			} catch (Exception e){
				e.printStackTrace();
				view = "/jsp/fail.jsp";
				throw new Exception("ob insert 失敗");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 查詢完成的訂單
		//取得OrderBean 和 OrderDetailBean
		OrderBean ob;
		try {
			ob = os.selectByPrimaryKey("00000000000001");
			List<OrderDetailBean> odList = ods.selectAllItem("00000000000001");
			request.setAttribute("ob", ob);
			request.setAttribute("odList", odList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
}