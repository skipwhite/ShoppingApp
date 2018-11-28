package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
import com.google.gson.Gson;

@WebServlet(value = "/billUpdate", name = "billUpdate")
public class BillUpdateServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=UTF-8");
		System.out.println("=====================bill update Servlet");
		ProductService service = new ProductServiceImpl();
		Integer totalPrice = 0;
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
		String jsonReturn = null;
		jsonReturn = new Gson().toJson(totalPrice);
		System.out.println("jsonReturn : " + jsonReturn);
		
		PrintWriter out = response.getWriter();
		out.write(jsonReturn);
	}
}