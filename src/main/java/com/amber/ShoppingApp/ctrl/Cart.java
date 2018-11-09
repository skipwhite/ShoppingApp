package com.amber.ShoppingApp.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.UserBean;
import com.amber.ShoppingApp.service.UserService;
import com.amber.ShoppingApp.service.impl.UserServiceImpl;

@WebServlet("/cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String str1 = request.getParameter("item");       // item name
	    String str2 = request.getParameter("qty");        // item quantity
	    String str3 = request.getParameter("add");        // submit button by name add
	    String str4 = request.getParameter("list");       // submit button by name list
	 
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
							
	    if(str3 != null)    
	    {    
	      Cookie c1 = new Cookie(str1, str2);                                
	      response.addCookie(c1);                                                        
	      response.sendRedirect("jsp/ShoppingCart.jsp"); 
	    }			                                                         
	    else if(str4 != null)  
	    { 
	      Cookie clientCookies[] = request.getCookies();       
	      for( int i = 0; i < clientCookies.length; i++)     
	      {
	        out.print("<B>" + clientCookies[i].getName() + " : " + clientCookies[i].getValue() + "</B><BR>");
	      }                
	    }
	    out.close( ) ;
	}

	
}