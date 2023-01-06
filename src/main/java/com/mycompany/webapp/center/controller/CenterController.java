package com.mycompany.webapp.center.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<CenterVO> list = centerService.centerList(pager);
		return list;
	}

	/* 여기 수정해주세용~~
	@GetMapping(value="/centerList")
	public String centerList(Model model) throws Exception {

		//calendar 객체를 생성하여 현재 날짜와 시간정보를 가져옵니다
		Calendar calendar = Calendar.getInstance();
		//원하는 date형식을 지정
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
//		Date nowToday = Calendar.getInstance().getTime();
		//가져온 정보를 format 후 string 으로 저장
		String now = format.format(calendar.getTime()).replaceAll("-", "");
		int nowToday = Integer.parseInt(now);
		
		model.addAttribute("centerCode", centerService.insertCenterCode()+1);
		model.addAttribute("centerList" ,centerService.centerList());
		List<CenterVO> centerList = centerService.centerList();
		String con = "";
		//현재 날짜 구하기
		//센터 상태 정보 , 센터리스트 만큼 돌려야 함
		List<String>conList = new ArrayList<String>();

		for(int i=0; i<centerList.size(); i++) {
			String open = centerList.get(i).getCenterOpeningDate().replaceAll("-","");
			String close = centerList.get(i).getCenterClosingDate().replaceAll("-","");
//			Date openDate = format.parse(open);
//			Date closeDate = format.parse(close);
			
			
			int openInteger = Integer.parseInt(open);
			int closeInteger = Integer.parseInt(close);
			
//			int openCenter = openInteger.compareTo(nowToday); //오픈예정 1
//			int expectedCenter = closeInteger.compareTo(nowToday); // 오픈중 -1
//			int closeCenter = close.compareTo(nowToday); //폐점 1
			
			if (openInteger > nowToday) {
				con = "N";
			}if (openInteger <= nowToday) {
				con = "Y";
			}if ( closeInteger > 00000000 && closeInteger < nowToday && openInteger < nowToday) {
				con = "N";
			}
			conList.add(con);
		}
		model.addAttribute("centerConList", conList);
		return "jsp/center/centerlist";
	}*/

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