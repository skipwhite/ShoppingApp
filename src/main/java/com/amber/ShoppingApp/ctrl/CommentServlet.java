package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ODBean;
import com.amber.ShoppingApp.model.ProductCommentBean;
import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.OrderService;
import com.amber.ShoppingApp.service.ProductCommentService;
import com.amber.ShoppingApp.service.impl.OrderServiceImpl;
import com.amber.ShoppingApp.service.impl.ProductCommentServiceImpl;
import com.amber.ShoppingApp.util.MD5Encrypt;


@WebServlet("/comment")
@MultipartConfig
public class CommentServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String view = "/jsp/account/myOrder.jsp";
		String productId = request.getParameter("productId");
		String rate = request.getParameter("rate");
		String comment = request.getParameter("comment");
		String poNo = request.getParameter("poNo");
		String userId = (String) request.getSession().getAttribute("login");
		ProductCommentService service = new ProductCommentServiceImpl();
		ProductCommentBean bean = new ProductCommentBean();
		bean.setProductId(productId);
		bean.setRate(new BigDecimal(rate));
		bean.setComment(comment);
		bean.setUserId(userId);
		
		try {
			service.createComment(bean, poNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String action = request.getParameter("action");
//		if (action != null && action.equals("save")) {
//			save(request, response, bean);
//		}

		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	

	}
}
