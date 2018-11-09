package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
import com.amber.ShoppingApp.service.impl.UserServiceImpl;

@WebServlet("/product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		ProductBean bean = new ProductBean(); 
//		Map<String, String> cart = (Map<String, String>) request.getSession().getAttribute("cart");
		String productId = request.getParameter("productId");
		
		//傳參數product id顯示出product訊息
		try {
			bean = service.selectByPrimaryKey(productId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("pd", bean);
		
		//顯示購物車
		try {
			Map<ProductBean, String> beanCart = service.checkCart(request);
			request.getSession().setAttribute("beanCart", beanCart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//    	try {
//    		Cookie cookie[]= request.getCookies();
//			Map<ProductBean, String> map = service.checkCart(cookie);
//			request.setAttribute("map", map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/product.jsp");
		rd.forward(request, response);
	}
}