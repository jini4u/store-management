package com.mycompany.webapp.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 시스템 관리자에 해당하는 접근 권한을 확인하는 인터셉터
 * @author 임유진
 * */
public class SysadminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String authority = (String)request.getSession().getAttribute("authority");
		//권한이 sysadmin이 아니라면 Controller로 넘어가지 않는다
		if(!authority.equals("sysadmin")) {
			return false;
		}
		
		return true;
	}
}
