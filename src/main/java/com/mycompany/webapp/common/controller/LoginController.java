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

@RequestMapping("/member")
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
		if(member != null) {
		/*	String userPSWassword = ManagerVO.get*/
		}else {
			
		}
		
		return "/login";
	}


}
