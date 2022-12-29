package com.mycompany.webapp.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {
	
	@RequestMapping(value="/managerlookup")
	public String managePhotoCenter() {
		return "jsp/manager/managerlookup";
	}
}
