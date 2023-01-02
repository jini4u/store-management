package com.mycompany.webapp.score.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.score.service.IScoreService;

@Controller
public class ScoreController {

	@Autowired
	IScoreService scoreService;
	
	@RequestMapping("/scoreupload")
	public String scoreupload() {
		return "jsp/score/scoreupload";
	}
	
	@RequestMapping("/score")
	public String centerscoreinquiry() {
		return "jsp/score/centerscoreinquiry";
	}
	
	@RequestMapping(value="/code")
	public String code(Model model) {
		List<Map<String, String>> allGroupCodes = scoreService.getAllGroupCodes();
		model.addAttribute("allGroupCodes", allGroupCodes);
		return "jsp/score/code";
	}
	
	@RequestMapping("/getDetailCodes/{groupCode}")
	public @ResponseBody List<Map<String, Object>> getDetailCodes(@PathVariable String groupCode) {
		return scoreService.getDetailCodes(groupCode);
	}
}
