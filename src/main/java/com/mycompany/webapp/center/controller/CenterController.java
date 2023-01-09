package com.mycompany.webapp.center.controller;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.center.service.ICenterService;
import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;

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
	public String manageCenterPhoto(@RequestParam(defaultValue="1") int pageNo, Model model) {
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<CenterVO> centerList = centerService.centerList(pager);
		model.addAttribute("centerList", centerList);

		return "jsp/center/centerphoto";
	}
	//url은 value에 적혀있는 값으로 동작하고, centerlist.jsp 페이지를 로딩해준다?
	@GetMapping(value="/centerInsert")
	public String insertCenter(Model model) {
		return "jsp/center/centerlist";
	}
	//물어보기
	@ResponseBody
	@PostMapping(value="/centerInsert")
	public List<CenterVO>  insertCenter(@RequestParam(defaultValue="1") int pageNo, CenterVO centerVO) {
		centerVO.setCenterCode(centerService.insertCenterCode()+1);
		centerService.insertCenter(centerVO);
//		int totalRows = centerService.countAllCenters();
//		Pager pager = new Pager(10, 10, totalRows, pageNo);
//		List<CenterVO> list = centerService.centerList(pager);
		List<CenterVO> list = centerService.centerList();
		return list;
	}

	@GetMapping(value="/centerList")
	public String centerList(Model model, CenterVO centerVO){
		model.addAttribute("newCenterCode", centerService.insertCenterCode()+1);
		model.addAttribute("centerList", centerService.centerList());
		return "jsp/center/centerlist";
	}
	
	@ResponseBody
	@PostMapping(value ="/centerUpdate")
	public List<CenterVO> centerUpdate(CenterVO centerVO) throws Exception{
		centerService.centerUpdate(centerVO);
		List<CenterVO> centerList = centerService.centerList();
		return centerList;
	}

	@ResponseBody
	@PostMapping(value ="/centerCondition")
	public String centerCondition(CenterVO centerVO) {
		String centerCondition = centerService.centerCondition(centerVO);
		System.out.println(centerCondition);
		return centerCondition;
	}

	/**
	 * @author 임유진
	 * 담당자가 지정되어있지 않은 센터 조회
	 * @return List<맵핑가능센터>
	 * */
	@RequestMapping("/availCenter")
	public @ResponseBody List<CenterVO> getAvailableCenterList(){
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(totalRows, 10, totalRows, 1);
		List<CenterVO> allCenterList = centerService.centerList(pager);
		List<CenterVO> result = new ArrayList<>();
		for(CenterVO center:allCenterList) {
			logger.info(center.toString());
			if(center.getUserCode() == 0) {
				result.add(center);
			}
		}
		return result;
	}
}