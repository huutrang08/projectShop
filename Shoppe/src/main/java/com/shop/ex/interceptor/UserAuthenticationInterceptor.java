package com.shop.ex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class UserAuthenticationInterceptor implements HandlerInterceptor{
    
	@Autowired
	HttpSession session;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (session.getAttribute("user") != null) {
			return true;
		}
		session.setAttribute("uri",request.getRequestURI());
		response.sendRedirect("/users/login");
		return false;
	}
    
}
