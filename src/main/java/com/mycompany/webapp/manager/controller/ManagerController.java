package com.mycompany.webapp.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {
	
	@RequestMapping(value="/code")
	public String code() {
		return "jsp/manager/code";
	}
}
