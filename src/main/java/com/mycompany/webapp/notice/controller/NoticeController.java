package com.mycompany.webapp.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.notice.service.INoticeService;
import com.mycompany.webapp.notice.vo.PostVO;

@RequestMapping("/notice")
@Controller
public class NoticeController {
	
	@Autowired
	INoticeService noticeService;
	
	/**
	 * 공지사항 글 목록 조회
	 * @author 임유진
	 * */
	@RequestMapping("/list")
	public String getAllPosts(@RequestParam(defaultValue="1") int pageNo, Model model) {
		Pager pager = new Pager(10, 10, noticeService.countAllPosts(), pageNo);
		
		model.addAttribute("pager", pager);
		model.addAttribute("allPosts", noticeService.getPosts(pageNo));
		return "jsp/notice/board";
	}
	
	/**
	 * 글쓰기 GET매핑
	 * @author 임유진
	 * @return 글쓰기 뷰페이지
	 * */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createPost() {
		return "jsp/notice/create";
	}
	
	/**
	 * 글쓰기 POST매핑
	 * @author 임유진
	 * @return redirect: 글목록
	 * */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createPost(PostVO post, Model model) {
		noticeService.createPost(post);
		return "redirect: /notice/list";
	}
	
	/**
	 * 글수정 GET매핑
	 * @author 임유진
	 * @param {int} 글번호
	 * @return 글수정 뷰페이지
	 * */
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updatePost(@RequestParam int postno, Model model) {
		PostVO post = noticeService.getPost(postno);
		String postContent = post.getContent();
		int rows = postContent.split("\n").length;
		model.addAttribute("post", post);
		model.addAttribute("rows", rows+5);
		return "jsp/notice/update";
	}
	
	/**
	 * 글수정 POST매핑
	 * @author 임유진
	 * @param {PostVO} 수정한 글 PostVO
	 * @return redirect: 글목록
	 * */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updatePost(PostVO post, Model model) {
		noticeService.updatePost(post);
		return "redirect: /notice/list";
	}
	
	/**
	 * 글삭제 처리
	 * @author 임유진
	 * @param {int} 글번호
	 * @return redirect: 글목록
	 * */
	@RequestMapping("/delete")
	public String deletePost(@RequestParam int postno, Model model) {
		noticeService.deletePost(postno);
		return "redirect: /notice/list";
	}
}
