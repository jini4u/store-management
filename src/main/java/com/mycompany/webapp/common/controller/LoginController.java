package com.mycompany.webapp.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.webapp.common.service.ILoginService;
import com.mycompany.webapp.manager.controller.ManagerController;
import com.mycompany.webapp.manager.vo.ManagerVO;


@Controller
public class LoginController {
	static final Logger logger=LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	ILoginService loginService;
	
	/* author 고은별
	 * 로그인 GET
	 * */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/login";
	}
	
	/* author 고은별
	 * 로그인 POST
	 * */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(int userCode, String password, HttpSession session, Model model) {
		ManagerVO member = loginService.selectMember(userCode);
		
		//회원
		if(member != null) {
			logger.info(member.toString());
			String userPW = member.getUserPassword();
			if(userPW == null) {
				//아이디 존재하지 않음
				//국제화처리
				model.addAttribute("message","아이디 또는 비밀번호를 잘못 입력했습니다.");
			}else {
				//아이디 존재
				if(userPW.equals(password)){
					//비밀번호 일치
					session.setAttribute("userCode", userCode);
					session.setAttribute("userName", member.getUserName());
					session.setAttribute("userPassword", member.getUserPassword());
					return "redirect: /";
				}else {
					//비밀번호 불일치
					//국제화처리
					model.addAttribute("message","아이디 또는 비밀번호를 잘못 입력했습니다.");
				}
			}
		}else {
			//비회원
			logger.info("비회원"+password);
			model.addAttribute("message","비회원입니다.");
		}
		session.invalidate();
		return "redirect:/login";
	}

	/* author 고은별
	 * 회원 정보 수정
	 * */
	@RequestMapping(value="/passwordupdate", method=RequestMethod.POST)
	public String passwordUpdate(ManagerVO member,HttpSession session, Model model) {
		loginService.updateMember(member);
		logger.info(member.toString());
		/*model.addAttribute("message","");*/
		model.addAttribute("member",member);		
		return "redirect: /";
	}
	
	/* author 고은별
	 * 로그아웃
	 * */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect: /login";
	}
}
