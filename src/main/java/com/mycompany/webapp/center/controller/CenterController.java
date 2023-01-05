package com.mycompany.webapp.center.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.center.service.CenterService;
import com.mycompany.webapp.center.service.ICenterService;
import com.mycompany.webapp.center.vo.CenterVO;
/**
 * @ClassName : centerController.java
 * @Description : 센터에 관한 controller
 * @Modification 
 * @
 * @       수정일                  수정자                 수정내용
 * @ ==============   ============   ===========
 * @
 * @author 이소정
 * **/
@Controller
public class CenterController {
	private static Logger logger = LoggerFactory.getLogger(CenterController.class);
	@Autowired
	ICenterService centerService;

	@RequestMapping(value="/centerPhoto")
	public String managePhotoCenter() {
		return "jsp/center/centerPhoto";
	}
	//url은 value에 적혀있는 값으로 동작하고, centerlist.jsp 페이지를 로딩해준다?
	@GetMapping(value="/centerInsert")
	public String insertCenter(Model model) {
		return "jsp/center/centerlist";
	}
	//물어보기
	@ResponseBody
	@PostMapping(value="/centerInsert")
	public List<CenterVO>  insertCenter(CenterVO centerVO) {
		centerVO.setCenterCode(centerService.insertCenterCode()+1);
		System.out.println(centerVO);
		centerService.insertCenter(centerVO);
		List<CenterVO> list = centerService.centerList();
		return list;
	}
	
	@GetMapping(value="/centerList")
	public String centerList(Model model) {
		model.addAttribute("centerCode", centerService.insertCenterCode()+1);
		model.addAttribute("centerList" ,centerService.centerList());
		return "jsp/center/centerlist";
	}

	
	
}