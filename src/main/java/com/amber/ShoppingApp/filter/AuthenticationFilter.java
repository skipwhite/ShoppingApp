package com.amber.ShoppingApp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.UserBean;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = { "/bill" , "/addToCart?action=bill"})
public class AuthenticationFilter implements Filter {
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof ServletRequest && response instanceof ServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			
			System.out.println("This is filter");

			String oriUri = req.getRequestURI();
			req.getSession().setAttribute("oriUri", oriUri);
			UserBean bean = (UserBean) req.getSession().getAttribute("login");
			
			if (bean == null) {
				System.out.println("Redirect to login page");
				String redirect = resp.encodeRedirectURL(req.getContextPath() + "/jsp/login.jsp");
				resp.sendRedirect(redirect);
			} else {
				System.out.println("Have login info.");
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
	}
}
