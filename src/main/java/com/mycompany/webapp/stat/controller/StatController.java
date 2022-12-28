package com.mycompany.webapp.stat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatController {
	
	@RequestMapping(value="/statistics")
	public String statistics() {
		return "statistics";
	}
}
