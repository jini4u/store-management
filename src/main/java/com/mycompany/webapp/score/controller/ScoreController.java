package com.mycompany.webapp.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScoreController {

	@RequestMapping("/scoreupload")
	public String scoreupload() {
		return "jsp/score/scoreupload";
	}
	
	@RequestMapping("/score")
	public String centerscoreinquiry() {
		return "jsp/score/centerscoreinquiry";
	}
	
	@RequestMapping(value="/code")
	public String code() {
		return "jsp/score/code";
	}
}
