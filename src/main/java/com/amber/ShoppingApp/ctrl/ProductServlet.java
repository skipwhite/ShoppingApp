package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.model.ProductCommentBean;
import com.amber.ShoppingApp.service.ProductCommentService;
import com.amber.ShoppingApp.service.ProductImgService;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductCommentServiceImpl;
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
		
		//抓product comment
		ProductCommentService pcs = new ProductCommentServiceImpl();
		List<ProductCommentBean> comments;
		try {
			comments = pcs.selectByProduct(productId);
			request.setAttribute("comments", comments);
			
			if (comments != null) {
				BigDecimal count = new BigDecimal(0);
				BigDecimal total = new BigDecimal(0);
				BigDecimal avg = new BigDecimal(0);
				for(ProductCommentBean comment : comments) {
					total = comment.getRate().add(total);
					count = count.add(new BigDecimal(1));
				}
				avg = total.divide(count);
				request.setAttribute("avg", avg.intValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//    	try {
//			Map<ProductBean, String> map = service.checkCookie(request);
//			request.setAttribute("map", map);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/product.jsp");
		rd.forward(request, response);
	}
}