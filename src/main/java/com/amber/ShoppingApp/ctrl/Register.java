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
import com.amber.ShoppingApp.service.impl.UserServiceImpl;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Pattern emailRegex = Pattern.compile(
			"^[_a-z0-9]+([.][_a-z0-9]+)*@[_a-z0-9]+([.][_a-z0-9]+)*$");
	private final Pattern passwordRegex = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z]).{8,}$"); // need alpha and digit, 8 char at least
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		if (userId != null) {
			tryCreateUser(request, response);
		}
	}

	private void tryCreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
		
		com.amber.ShoppingApp.service.UserService service = new UserServiceImpl();
		int count = 0;
		String errCde = "00";
		String errMsg = "";
		List<String> errMessage = new ArrayList<String>();
		
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");	
		String SucessMessage = "";
		UserBean bean = new UserBean();
		String view = "index.jsp";
		
		try {
			UserBean record = new UserBean();
			if (userId == null || userId.isEmpty()) {
				errMessage.add("must inupt userId!");
				errMessage.add("請輸入姓名");
			}
			if (!validateEmail(email)) {
				errMessage.add("Email地址錯誤");
			}
			if (!validatePassword(password)) {
				errMessage.add("至少八碼含英文數字");
			}
			if (!password.equals(password2)) {
				errMessage.add("密碼不符合");
			}	
			
			// Hash password
//			Integer salt = (int)(Math.random() * 100);
//			String encrypt = String.valueOf(salt + password.hashCode());
			
			record.setUserId(userId);
			record.setName(name);
			record.setEmail(email);
//			record.setPassword(encrypt);
//			record.setSalt(salt);
			record.setPassword(password);
			record.setSalt(123);

			// INSERT
			count = service.tryCreateUser(record);
			if (count != 1) {
				throw new Exception("error count : " + count + " when inserting!");
			} else {
				errCde = "00";
				errMsg = "insert success!";
				SucessMessage = "註冊成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			// error handling...
			errCde = "01";
			errMsg = "Error : " + e.getMessage();
			view = "/jsp/register.jsp";
		}
		request.setAttribute("errCde", errCde);
		request.setAttribute("errMsg", errMsg);
		request.setAttribute("errMessage", errMessage);
		request.setAttribute("SucessMessage", SucessMessage);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	private boolean validatePassword(String password) {
		return password != null & passwordRegex.matcher(password).find();
	}

	private boolean validateEmail(String email) {
		// if email is not null, email != null return true, else false
		return email != null & emailRegex.matcher(email).find();
	}
}
