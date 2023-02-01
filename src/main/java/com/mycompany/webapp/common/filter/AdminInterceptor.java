package com.mycompany.webapp.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 담당자 관리자 이상에 해당하는 접근 권한을 확인하는 인터셉터
 * @author 임유진
 * */
public class AdminInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String authority = (String)request.getSession().getAttribute("authority");
		
		if(authority.equals("manager")) {
			return false;
		}
		
		return true;
	}

}
