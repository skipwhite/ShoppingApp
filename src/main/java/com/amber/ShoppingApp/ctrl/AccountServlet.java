package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ODBean;
import com.amber.ShoppingApp.model.ODPBean;
import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.OrderService;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.service.impl.OrderServiceImpl;
import com.amber.ShoppingApp.service.impl.UserServiceImpl;
import com.amber.ShoppingApp.util.MD5Encrypt;


@WebServlet("/account")
@MultipartConfig
public class AccountServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String view = "/jsp/account/profile.jsp";
		UserBean bean = (UserBean) request.getSession().getAttribute("login");

		String action = request.getParameter("action");
		if (action != null && action.equals("save")) {
			save(request, response);
		}
		
		if (action != null && action.equals("changePsw")) {
			changePsw(request, response, bean);
			return;
		}
		
		if (action != null && action.equals("myOrder")) {
			myOrder(request, response, bean);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void myOrder(HttpServletRequest request, HttpServletResponse response, UserBean bean) throws ServletException, IOException {
		String view = "/jsp/account/myOrder.jsp";
		OrderService service = new OrderServiceImpl();
		List<ODBean> ODList = service.myOrder(bean.getUserId());
		
//		List<ODPBean> ODPList = service.myOrder(bean.getUserId());
		
		request.setAttribute("ODList", ODList);
//		request.setAttribute("ODPList", ODPList);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void changePsw(HttpServletRequest request, HttpServletResponse response, UserBean bean) throws ServletException, IOException {
		String passwordOld = request.getParameter("passwordOld");
		String view = "/jsp/account/password.jsp";
		String errMessage = "";
		if (!MD5Encrypt.md5(passwordOld).equals(bean.getPassword())) {
			errMessage = "密碼錯誤";
			
		}
		String passwordNew = request.getParameter("passwordNew");
		if (!RegisterServlet.validatePassword(passwordNew)) {
			errMessage += "密碼不符規定";
		}
		String passwordNew2 = request.getParameter("passwordNew2");
		if (!passwordNew.equals(passwordNew2)) {
			errMessage += "密碼不一致";
		}
		request.setAttribute("errMessage", errMessage);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		UserService service = new UserServiceImpl();
		UserBean bean = new UserBean();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String zipCode = request.getParameter("zipCode");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String shipStore = request.getParameter("shipStore");
		bean.setName(name);
		bean.setEmail(email);
		bean.setZipCode(zipCode);
		bean.setAddress(address);
		bean.setPhone(phone);
		bean.setShipStore(shipStore);
				
		try {
			service.updateByPrimaryKeySelective(bean);
			request.setAttribute("login", bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
