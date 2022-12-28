package com.mycompany.webapp.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.test.service.ITestService;
import com.mycompany.webapp.test.vo.TestVO;

@Controller
public class TestController {
	
	@Autowired
	ITestService testService;
	
	@RequestMapping("/board")
	public String getAllPosts(Model model) {
		model.addAttribute("allPosts", testService.getAllPosts());
		return "board";
	}
	
	@RequestMapping(value="/board/create", method=RequestMethod.GET)
	public String createPost() {
		return "create";
	}
	
	@RequestMapping(value="/board/create", method=RequestMethod.POST)
	public String createPost(TestVO post, Model model) {
		testService.createPost(post);
		return "redirect: /board";
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.GET)
	public String updatePost(@RequestParam int postno, Model model) {
		model.addAttribute("post", testService.getPost(postno));
		return "update";
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updatePost(TestVO post, Model model) {
		testService.updatePost(post);
		return "redirect: /board";
	}
	
	@RequestMapping("/board/delete")
	public String deletePost(@RequestParam int postno, Model model) {
		testService.deletePost(postno);
		return "redirect: /board";
	}
}
