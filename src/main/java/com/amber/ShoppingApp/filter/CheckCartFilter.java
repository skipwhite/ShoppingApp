package com.amber.ShoppingApp.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amber.ShoppingApp.model.ProductBean;
import com.amber.ShoppingApp.service.ProductService;
import com.amber.ShoppingApp.service.impl.ProductServiceImpl;

//@WebFilter(filterName = "CheckCartFilter", urlPatterns = { "/product", "/bill" })
@WebFilter(filterName = "CheckCartFilter", urlPatterns = { "/product", "/selectAllProduct" })
public class CheckCartFilter implements Filter {
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof ServletRequest && response instanceof ServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			
			System.out.println("This is cart filter");
			
			ProductService service = new ProductServiceImpl();
			Map<ProductBean, String> map;
			try {
				map = service.checkCookie(req);
				request.setAttribute("map", map);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Cart filter go wrong");
			}
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}
