package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.service.impl.UserServiceImpl;
import com.amber.ShoppingApp.util.MD5Encrypt;

@WebServlet("/login")
public class LoginServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("login")) {
			login(request, response);
		}
		if (action.equals("logout")) {
			logout(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getSession().getAttribute("login") != null) {
        	request.getSession().invalidate();
        }
		System.out.println("Logged out");
		response.sendRedirect("index.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserServiceImpl();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		List<String> errMessage = new ArrayList<String>();
		String view = "";
		String oriUri = (String) request.getSession().getAttribute("oriUri");
		
		try {
				//MD5加密後再傳至service
				UserBean bean = service.login(userId, MD5Encrypt.md5(password));
				if(bean != null) {
					if(request.getSession(false) != null) {
						request.changeSessionId();
					}
					request.getSession().setAttribute("login", bean);
					if(oriUri == null) {
						view = "index.jsp";
					} else {
						view = oriUri;
					}
				} else {
					errMessage.add("帳號或密碼錯誤");
					view = "jsp/login.jsp";
				}
		} catch (Exception e) {
			e.printStackTrace();
			view = "/jsp/login.jsp";
		}
		
		request.setAttribute("errMessage", errMessage);
		response.sendRedirect(view);
	}
}