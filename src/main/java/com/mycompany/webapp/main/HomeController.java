package com.mycompany.webapp.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.test.dao.ITestRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {
	//private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);
	@Autowired
	ITestRepository test;
	
	@RequestMapping("/")
	public String home() {
		System.out.println(test.test());
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
