package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;

@WebServlet("/buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		if (productId != null) {
			buy(request,response);
		}
	}
	
	private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { {
		ProductService service = new ProductServiceImpl();
		String productId = request.getParameter("productId");
		String qty = request.getParameter("qty"); 
		String check = request.getParameter("check");
		String view = "/jsp/cart.jsp";
		if(check != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/checkOut");
			rd.forward(request, response);
			return;
		}
		Map<String, String> cart = (Map<String, String>) request.getSession().getAttribute("cart");
        
		//判斷是否存在購物車, 若不存在, 創建購物車
		if (cart == null) {
        	cart = new HashMap<>();
        }
		
		// 將商品加入購物車
		try {
			service.addToCart(productId, qty, cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("cart", cart);
		System.out.println(request.getSession().getAttribute("cart"));
		//透過productId 取出bean
		
			//將bean加入購物車
        
//	    ProductService service = new ProductServiceImpl();
//	    String productId = request.getParameter("productId");       
//	    String qty = request.getParameter("qty");        
//	    int count = 0;
//	    
//	    Cookie cookie[]= request.getCookies();
//	    
//	    //
//	    if (cookie != null) {
//	    	for( int i = 0; i < cookie.length; i++) {
//	    		System.out.print("Start" + cookie[i].getName() + " : " + cookie[i].getValue() +"   ");
//		      } 
//	    	System.out.println();
//	    } 
//	    
//	    // Check productId and info
//	    if (!productId.isEmpty() && !qty.isEmpty()) {
//    		//if product already exist in cart. update cookie
//        	for( int i = 0; i < cookie.length; i++) {
//        		if (productId.equals(cookie[i].getName())) {
//        			String qtyC = cookie[i].getValue();
//        			Integer result = new Integer(qtyC) + new Integer(qty);
//        			cookie[i].setValue(result.toString());
//        			response.addCookie(cookie[i]);
//        			count++;
//        			System.out.println("come here" + count);
//        		} 
//    	      }
//        	// productId doesn't exist. new cookie
//        	if (count == 0) {
//    			Cookie c1 = new Cookie(productId, qty);
//    			response.addCookie(c1); 
//    			System.out.print("add new cookie" + productId);
//        	}
//	    }
//	    
//    	for( int i = 0; i < cookie.length; i++) {
//    		System.out.print("Finish" + cookie[i].getName() + " : " + cookie[i].getValue() +"   ");
//	      } 
//    	System.out.println();
//	    
//	    // Check cart qty
//    	try {
//			Map<ProductBean, String> map = service.checkCart(cookie);
//			request.setAttribute("map", map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	    
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	}
}