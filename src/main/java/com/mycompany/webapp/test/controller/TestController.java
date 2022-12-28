package com.mycompany.webapp.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.test.service.ITestService;

@Controller
public class TestController {
	
	@Autowired
	ITestService testService;
	
	@RequestMapping("/board")
	public String getAllPosts(Model model) {
		model.addAttribute("allPosts", testService.getAllPosts());
		return "board";
	}
}
