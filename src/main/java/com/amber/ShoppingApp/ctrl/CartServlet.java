package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
import com.google.gson.Gson;


@WebServlet(value = "/cart", name = "cart")
public class CartServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService service = new ProductServiceImpl();
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=UTF-8"); 
		String productId = request.getParameter("productId");
		String jsonReturn = null;
		try {
			ProductBean bean = service.selectByPrimaryKey(productId);
			if(bean != null) {
				jsonReturn = new Gson().toJson(bean);
				System.out.println("jsonReturn : " + jsonReturn);
				
				PrintWriter out = response.getWriter();
				out.write(jsonReturn);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		String keyword = request.getParameter("keyword");
//		System.out.println("keyword : " + keyword);
//		String jsonReturn = null;
//		try {
//			List<ProductBean> resultList = select2(keyword);
//			jsonReturn = new Gson().toJson(resultList);
//			System.out.println("jsonReturn : " + jsonReturn);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<String> lessonList = select(keyword);
//		String jsonReturn = new Gson().toJson(lessonList);
//		System.out.println("jsonReturn : " + jsonReturn);
		
//		PrintWriter out = response.getWriter();
//		out.write(bean);
	}

}
