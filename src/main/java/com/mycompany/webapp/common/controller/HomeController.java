package com.mycompany.webapp.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.common.poi.POIClass;
import com.mycompany.webapp.common.poi.ScorePOI;
import com.mycompany.webapp.notice.dao.INoticeRepository;

import lombok.extern.log4j.Log4j2;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	INoticeRepository test;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//poi 테스트 중
	@RequestMapping("/poi")
	public @ResponseBody void poiTest() {
		String filePath = "C:\\Users\\KOSA\\Documents\\숙제용";
		String fileName = "template_점수.xlsx";
		
		POIClass poi = new ScorePOI();
		poi.readWorkBook(filePath, fileName);
	}
}
