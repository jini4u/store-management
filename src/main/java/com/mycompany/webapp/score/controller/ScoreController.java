package com.mycompany.webapp.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.webapp.score.service.IScoreService;

@Controller
public class ScoreController {

	@RequestMapping("/scoreupload")
	public String scoreupload() {
		return "jsp/score/scoreupload";
	}
	
	@RequestMapping("/score")
	public String centerscoreinquiry() {
		return "jsp/score/scoreList";
	}
	
	@RequestMapping(value="/code")
	public String code() {
		return "jsp/score/code";
	}
	

	

	
}
