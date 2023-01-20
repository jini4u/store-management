package com.mycompany.webapp.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.notice.service.INoticeService;
import com.mycompany.webapp.notice.vo.PostVO;

@RequestMapping("/board")
@Controller
public class NoticeController {
	
	@Autowired
	INoticeService noticeService;
	
	@RequestMapping("/list")
	public String getAllPosts(Model model) {
		model.addAttribute("allPosts", noticeService.getAllPosts());
		return "jsp/notice/board";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createPost() {
		return "jsp/notice/create";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createPost(PostVO post, Model model) {
		noticeService.createPost(post);
		return "redirect: /board/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updatePost(@RequestParam int postno, Model model) {
		model.addAttribute("post", noticeService.getPost(postno));
		return "jsp/notice/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updatePost(PostVO post, Model model) {
		noticeService.updatePost(post);
		return "redirect: /board/list";
	}
	
	@RequestMapping("/delete")
	public String deletePost(@RequestParam int postno, Model model) {
		noticeService.deletePost(postno);
		return "redirect: /board/list";
	}
}
