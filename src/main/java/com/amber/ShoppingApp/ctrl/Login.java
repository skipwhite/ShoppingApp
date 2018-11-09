package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.service.impl.UserServiceImpl;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		if (userId != null) {
			login(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UserService service = new UserServiceImpl();
		
		int count = 0;
		String errCde = "00";
		String errMsg = "";
		
		String password = request.getParameter("password");
		String userId = request.getParameter("userId");
		List<String> errMessage = new ArrayList<String>();
		String view = "index.jsp";
		
		try {
			if(!errMessage.equals(null)) {
				if(service.login(userId, password)) {
			    	if(request.getSession(false) != null) {
			    		request.changeSessionId();
			    	}
			    	request.getSession().setAttribute("login", userId);
	//		    	if(role.equals("vendor")) {
	//		    		request.getSession().setAttribute("role", "vendor");
	//		    	}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// error handling...
			errCde = "01";
			errMsg = "Error : " + e.getMessage();
			view = "/jsp/login.jsp";
		}
		request.setAttribute("errMessage", errMessage);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}