package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.service.impl.UserServiceImpl;
import com.amber.ShoppingApp.util.MD5Encrypt;

@WebServlet("/register")
public class RegisterServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	private final Pattern emailRegex = Pattern.compile(
			"^[_a-z0-9]+([.][_a-z0-9]+)*@[_a-z0-9]+([.][_a-z0-9]+)*$");
	private final static Pattern passwordRegex = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z]).{8,}$"); // need alpha and digit, 8 char at least
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action.equals("register")) {
			createUser(request, response);
		}
	}

	private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserServiceImpl();
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		System.out.println(name);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");	
		UserBean record = new UserBean();
		List<String> errMessage = new ArrayList<String>();
		String view = "index.jsp";
		int count = 0;
		
		if (!validateEmail(email)) {
			errMessage.add("Email地址錯誤");
		}
		if (!validatePassword(password)) {
			errMessage.add("至少八碼含英文數字");
		}
		if (!password.equals(password2)) {
			errMessage.add("密碼不符合");
		}	
		
		//MD5加密
		record.setPassword(MD5Encrypt.md5(password));
		record.setUserId(userId);
		record.setName(name);
		record.setEmail(email);

		try {
			// INSERT
			count = service.createUser(record);
			if (count != 1) {
				throw new Exception("error count : " + count + " when inserting!");
			} 
			UserBean bean = service.login(userId, MD5Encrypt.md5(password));
			if(bean != null) {
				request.getSession().setAttribute("login", bean);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			view = "/jsp/register.jsp";
		}
		
		request.setAttribute("errMessage", errMessage);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	public static boolean validatePassword(String password) {
		return password != null & passwordRegex.matcher(password).find();
	}

	private boolean validateEmail(String email) {
		// if email is not null, email != null return true, else false
		return email != null & emailRegex.matcher(email).find();
	}
}
