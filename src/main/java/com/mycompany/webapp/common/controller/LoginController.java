package com.mycompany.webapp.common.controller;

import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.webapp.common.service.ILoginService;
import com.mycompany.webapp.manager.vo.ManagerVO;

/*@RequestMapping("/member")*/
@Controller
public class LoginController {

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
		System.out.println(member);
		//회원일때
		if(member != null) {
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
					return "home";
				}else {
					//비밀번호 불일치
					//국제화처리
					model.addAttribute("message","아이디 또는 비밀번호를 잘못 입력했습니다.");
				}
			}
		}else {
			//회원 아닐때
			System.out.println(password);
			System.out.println("로그인 실패");
			model.addAttribute("message","회원이 아닙니다.");
		}
		session.invalidate();
		return "login";
	}


}
