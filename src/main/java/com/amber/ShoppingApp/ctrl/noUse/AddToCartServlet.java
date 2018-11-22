package com.amber.ShoppingApp.ctrl.noUse;
//package com.amber.ShoppingApp.ctrl;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.amber.ShoppingApp.model.ProductBean;
//import com.amber.ShoppingApp.service.ProductService;
//import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
//
//@WebServlet("/addToCart")
//public class AddToCartServlet extends BaseHttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("action");
//		if (action.equals("addToCart")) {
//			try {
//				addToCart(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if (action.equals("bill")) {
//			bill(request, response);
//		}
//	}
//	
//	private void bill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("/bill");
//		rd.forward(request, response);
//		
//	}
//
//	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception { 
//		String productId = request.getParameter("productId");
//		String qty = request.getParameter("qty"); 
//		String view = "/product?productId=" + productId;
//		Cookie cookie[]= request.getCookies();
////		int count = 0;
////	    if (cookie != null) {
////	    	for( int i = 0; i < cookie.length; i++) {
////	    		System.out.print(cookie[i].getName() + " : " + cookie[i].getValue() +"   ");
////		      } 
////	    	System.out.println();
////	    } 
////
////		if (!productId.isEmpty() && !qty.isEmpty()) {
////			//if product already exist in cart. update cookie
////			for( int i = 0; i < cookie.length; i++) {
////				if (productId.equals(cookie[i].getName())) {
////					Integer result = new Integer(cookie[i].getValue()) + new Integer(qty);
////					cookie[i].setValue(result.toString());
////					response.addCookie(cookie[i]);
////					count++;
////				} 
////			}
////			// productId doesn't exist. new cookie
////			if (count == 0) {
////				Cookie c1 = new Cookie(productId, qty);
////				response.addCookie(c1); 
////				System.out.print("Just add new cookie" + productId);
////		    	for( int i = 0; i < cookie.length; i++) {
////		    		System.out.print(cookie[i].getName() + " : " + cookie[i].getValue() +"   ");
////			      } 
////			}
////		}
////		ProductService service = new ProductServiceImpl();
////		Map<ProductBean, String> map;
////		try {
////			map = service.checkCookie(request);
////			request.setAttribute("map", map);
////		} catch (Exception e) {
////			e.printStackTrace();
////			throw new Exception("checkCart Error");
////		}
//
//		
//		RequestDispatcher rd = request.getRequestDispatcher(view);
//		rd.forward(request, response);
//        
////		ProductService service = new ProductServiceImpl();
////		Map<String, String> cart = (Map<String, String>) request.getSession().getAttribute("cart");
////		//判斷是否存在購物車, 若不存在, 創建購物車
////		if (cart == null) {
////        	cart = new HashMap<>();
////        }
////		// 將商品加入購物車
////		try {
////			service.addToCart(productId, qty, cart);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		request.getSession().setAttribute("cart", cart);
////		System.out.println(request.getSession().getAttribute("cart"));
//	}
//}