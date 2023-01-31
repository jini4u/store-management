package com.mycompany.webapp.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 로그인 여부를 확인하는 인터셉터
 * @author 임유진
 * */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try {
			int userCode = (int)request.getSession().getAttribute("userCode");			
		} catch(NullPointerException e){
			response.sendRedirect(request.getContextPath()+"/login");
			return false;			
		}
		
		return true;
	}
}
