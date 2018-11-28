package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductImgService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductImgServiceImpl;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;
import com.google.gson.Gson;


@WebServlet(value = "/search", name = "ajax002")
public class SearchServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		search(request, response);
		String action = request.getParameter("action");

		if (action != null && action.equals("search")) {
			query(request, response);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		request.setAttribute("query", query);
		String view = "/jsp/searchResult.jsp";
		try {
			Map<ProductBean, String> maps = select3(query);
			request.setAttribute("maps", maps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		PrintWriter out = response.getWriter();
		out.write(jsonReturn);
	}
	
	private List<ProductBean> select2(String keyword) throws SQLException, Exception {
		ProductService service = new ProductServiceImpl();
		List<ProductBean> beans = service.selectAll();
		List<ProductBean> resultList = new ArrayList<ProductBean>();
		for (ProductBean bean : beans) {
			if (keyword != null && bean.getName().contains(keyword.toLowerCase())) {
				resultList.add(bean);
			}
		}
		return resultList;
	}

	private Map<ProductBean, String> select3(String keyword) throws SQLException, Exception {
		ProductImgService pis = new ProductImgServiceImpl();
		ProductService service = new ProductServiceImpl();
		Map<ProductBean, String> map = new HashMap<>();
		
		List<ProductBean> pbsAll = service.selectAll();
		List<ProductBean> pbs = new ArrayList<ProductBean>();
		for (ProductBean bean : pbsAll) {
			if (bean.getName().contains(keyword.toLowerCase())) {
				pbs.add(bean);
			}
		}
		for (ProductBean pb : pbs) {
			String productId = pb.getProductId();
			map.put(pb, pis.selectMainImg(productId));
		}
		return map;
	}
}
