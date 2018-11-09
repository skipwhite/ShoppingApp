package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.TestBean;
import com.amber.ShoppingApp.service.TestService;
import com.amber.ShoppingApp.service.impl.TestServiceImpl;
import com.amber.ShoppingApp.util.BDUtil;
import com.amber.ShoppingApp.util.DateUtil;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			test(request, response);
		}
	}

	private void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TestService service = new TestServiceImpl();
		int count = 0;
		Date sysDate = new Date();
		List<String> errMessage = new ArrayList<String>();
		
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		String deci = request.getParameter("deci");
		String bin = request.getParameter("bin");
		String timestamp = request.getParameter("timestamp");

		
		TestBean bean = new TestBean();
		String view = "/jsp/test.jsp";
		
		try {
			TestBean record = new TestBean();
			record.setId(id);
			record.setNum(Integer.parseInt(num));
			record.setDeci(BDUtil.getBigDecimalFromString(deci, "0.##"));
			record.setBin(Boolean.valueOf(bin));
			record.setTimestamp(DateUtil.getTimestampFromString(timestamp, "yyyy-MM-dd"));
			
			count = service.insert(record);
			if (count != 1) {
				throw new Exception("error count : " + count + " when inserting!");
			} 
		} catch (Exception e) {
			e.printStackTrace();
			view = "/jsp/fail.jsp";
		}
		
		request.setAttribute("id", id);
		request.setAttribute("num", num);
		request.setAttribute("deci",deci );
		request.setAttribute("bin", bin);
		request.setAttribute("timestamp", timestamp);
		
		request.setAttribute("errMessage", errMessage);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

}
