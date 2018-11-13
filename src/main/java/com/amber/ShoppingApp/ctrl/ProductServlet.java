package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductImgService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductImgServiceImpl;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;

@WebServlet("/product")
public class ProductServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		ProductImgService pis = new ProductImgServiceImpl();
		ProductBean bean = new ProductBean();
		List<String> imgs = new ArrayList<>();
		String productId = request.getParameter("productId");

		//傳參數product id顯示出product訊息
		try {
			bean = service.selectByPrimaryKey(productId);
			imgs = pis.selectByPrimaryKey(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pd", bean);
		request.setAttribute("imgs", imgs);
		
    	try {
			Map<ProductBean, String> map = service.checkCookie(request);
			request.setAttribute("map", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/product.jsp");
		rd.forward(request, response);
	}
}