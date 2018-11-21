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


@WebServlet(value = "/search", name = "ajax002")
public class SearchServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	private String[] lessons = { "Ajax", "Docker", "Java", "AWS", "GCP", "Linux", "Node.js", "Swift", "Spting Framework", "堅果"};

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=UTF-8"); 
		String keyword = request.getParameter("keyword");
		System.out.println("keyword : " + keyword);
		String jsonReturn = null;
		try {
			List<ProductBean> resultList = select2(keyword);
			jsonReturn = new Gson().toJson(resultList);
			System.out.println("jsonReturn : " + jsonReturn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		List<String> lessonList = select(keyword);
//		String jsonReturn = new Gson().toJson(lessonList);
//		System.out.println("jsonReturn : " + jsonReturn);
		
		PrintWriter out = response.getWriter();
		out.write(jsonReturn);
	}

	private List<String> select(String keyword) {
		List<String> resultList = new ArrayList<String>();
		for (String s : lessons) {
			if (s.toLowerCase().contains(keyword.toLowerCase())) {
				resultList.add(s);
			}
		}
		return resultList;
	}
	
	private List<ProductBean> select2(String keyword) throws SQLException, Exception {
		ProductService service = new ProductServiceImpl();
		List<ProductBean> beans = service.selectAll();
		List<ProductBean> resultList = new ArrayList<ProductBean>();
		for (ProductBean bean : beans) {
			if (bean.getName().contains(keyword.toLowerCase())) {
				resultList.add(bean);
			}
		}
		return resultList;
	}
}
