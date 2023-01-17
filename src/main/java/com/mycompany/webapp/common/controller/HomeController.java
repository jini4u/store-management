package com.mycompany.webapp.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webapp.notice.dao.INoticeRepository;

/**
 * 공통적인 기능을 위한 HomeController
 * */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	INoticeRepository test;

	/**
	 * 메인 페이지
	 * */
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	/**
	 * 로그인 페이지 
	 * */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
