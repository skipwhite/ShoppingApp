package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
import com.google.gson.Gson;


@WebServlet(value = "/showCart", name = "showCart")
public class CartServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService service = new ProductServiceImpl();
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====================Cart Servlet");
		response.setHeader("content-type", "text/html;charset=UTF-8"); 
		String productId = request.getParameter("productId");
		List<String> productIds = Arrays.asList(productId.split(","));
		System.out.println(productIds);
		
		String jsonReturn = null;
		try {
			List<ProductBean> beans = service.multipleSelectByPrimaryKey(productIds);
			if(beans != null) {
				jsonReturn = new Gson().toJson(beans);
				System.out.println("jsonReturn : " + jsonReturn);
				
				PrintWriter out = response.getWriter();
				out.write(jsonReturn);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		System.out.println("=====================Cart Servlet");
//		response.setHeader("content-type", "text/html;charset=UTF-8"); 
//		String productId = request.getParameter("productId");
//		String jsonReturn = null;
//		try {
//			ProductBean bean = service.selectByPrimaryKey(productId);
//			if(bean != null) {
//				jsonReturn = new Gson().toJson(bean);
//				System.out.println("jsonReturn : " + jsonReturn);
//				
//				PrintWriter out = response.getWriter();
//				out.write(jsonReturn);
//			}
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}

}
