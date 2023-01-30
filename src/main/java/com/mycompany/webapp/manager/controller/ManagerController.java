package com.mycompany.webapp.manager.controller;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.service.IManagerService;
import com.mycompany.webapp.manager.vo.ManagerVO;

/**
 * 담당자 메뉴 관련 기능 
 * */
@RequestMapping("/manager")
@Controller
public class ManagerController {
	static final Logger logger=LoggerFactory.getLogger(ManagerController.class);


	  @Autowired 
	   IManagerService managerService;
	   /* author 은별
	       담당자 목록조회*/
	   @RequestMapping(value="/managerList")
	   public String selectManagerList(@RequestParam(defaultValue="1") int pageNo,Model model) {
	      int totalRows = managerService.countAllMgr();
	      Pager pager = new Pager(10, 10, totalRows, pageNo);
	      List<ManagerVO> managerList = managerService.selectManagerList(pager);
	      model.addAttribute("managerList", managerList);
	      model.addAttribute("pager", pager);
	      model.addAttribute("mgrURL","/manager/managerList");

	      logger.info("managerList : " + managerList);
	      return "jsp/manager/managerlookup";
	   }


	@RequestMapping(value="/managerDetail")
	public String selectManagerDetail(Model model, @PathVariable int userCode) {
		List<CenterVO> centerName = managerService.getCenterByManager(userCode);
		model.addAttribute("centerName",centerName);
		return "jsp/manager/managerdetail";
	}

	/* author 은별
	  담당자 등록 GET*/
	@GetMapping(value="/managerInsert")
	public String insertManager() {
		return "jsp/manager/managerlookup";
	}

	/* author 은별
	  담당자 등록 POST*/
	@ResponseBody
	@PostMapping(value="/managerInsert")
	public List<ManagerVO> insertManager(@RequestParam(defaultValue="1") int pageNo, ManagerVO mgr) {
		managerService.insertManager(mgr);
		int totalRows = managerService.countAllMgr();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<ManagerVO> managerList = managerService.selectManagerList(pager);
		//비동기 처리를 하기 위해 결과값을 보내준다
		return managerList;

	}

	/* author 은별
	  담담자 수정 GET*/
	@GetMapping(value="/managerUpdate")
	public  String managerUpdate() {
		return "jsp/manager/managerlookup";
	}

	/* author 은별
	  담담자 수정 POST*/
	@ResponseBody
	@PostMapping(value="/managerUpdate")
	public  ManagerVO managerUpdate(@RequestParam(defaultValue="1") int pageNo, ManagerVO mgr) {
		logger.info(mgr.toString());
		managerService.managerUpdate(mgr);
		int totalRows = managerService.countAllMgr();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		/*List<ManagerVO> managerList = managerService.selectManagerList(pager);
		logger.info(managerList.toString());*/
		return mgr;
	}

	/* author 은별
	  담담자 검색 */
	@GetMapping(value="/managerSearch")
	public String managerSearch(@RequestParam(defaultValue="1")int pageNo, @RequestParam("keyword") String keyword,
			@RequestParam("keywordType") String keywordType,Model model){
		  int keywordTotalRows = managerService.managerCountByKeyword(keyword, keywordType);
		  logger.info("검색11"+keywordTotalRows);
	      Pager searchPager = new Pager(10, 10, keywordTotalRows, pageNo);
	      model.addAttribute("pager", searchPager);
	      
	      List<ManagerVO> mgrSearchList = managerService.managerSearch(searchPager, keyword, keywordType);
	      if (mgrSearchList.size() != 0) {
	            model.addAttribute("managerList", mgrSearchList);
	            model.addAttribute("pager", searchPager);
	            model.addAttribute("mgrURL","/manager/managerSearch");
	            logger.info("검색"+searchPager.toString());
	         }else {
	            model.addAttribute("pager", new Pager(1, 1, 1, 1));
	            model.addAttribute("managerListCheck", "empty");
	         }
          model.addAttribute("keyword",keyword);
          model.addAttribute("keywordType",keywordType);
	      logger.info(searchPager.toString());
	      return "jsp/manager/managerlookup";

	}
	
	/**
	 * 담당자 맵핑 페이지로 이동 
	 * @author 임유진
	 * */
	@RequestMapping(value="/managerMapping")
	public String managerMapping(@RequestParam(defaultValue="1") int pageNo, @RequestParam(required=false) String keyword, 
			@RequestParam(required=false) String keywordType, Model model) {
		Pager pager;
		int totalRows;
		if(keyword==null || keyword.equals("")) {
			totalRows = managerService.countAllMgr();
			pager = new Pager(10, 5, totalRows, pageNo);
			model.addAttribute("managerList", managerService.selectManagerList(pager));			
		} else {
			keywordType = "UN";
			totalRows = managerService.managerCountByKeyword(keyword, keywordType);
			if(totalRows > 0) {
				pager = new Pager(10, 5, totalRows, pageNo);				
			} else {
				pager = new Pager(1, 1, 1, 1);
			}
			model.addAttribute("managerList", managerService.managerSearch(pager, keyword, keywordType));
		}
		model.addAttribute("totalManagers", totalRows);
		model.addAttribute("pager", pager);
		return "jsp/manager/managermapping";
	}

	/**
	 * 담당자에 따라 담당하는 센터 조회
	 * @author 임유진
	 * @param {Integer} 담당자 userCode
	 * @return {List<CenterVO>} 담당하고 있는 센터 리스트 
	 * */
	@RequestMapping(value="/getCenters/{userCode}")
	public @ResponseBody List<CenterVO> getCenterByManager(@PathVariable int userCode){
		return managerService.getCenterByManager(userCode);
	}

	/**
	 * 담당자와 센터 간 맵핑 해제
	 * @author 임유진
	 * @param {String} [{userCode:담당자코드, centerCode:센터코드},...] 형태
	 * @return {int} 해제된 맵핑 관계 수
	 * */
	@RequestMapping(value="/cancelMapping", method=RequestMethod.POST)
	public @ResponseBody int cancelMapping(@RequestBody String req) throws Exception {
		int result = 0;
		
		List<Map<String, String>> map = new ObjectMapper().readValue(req, new TypeReference<List<Map<String, String>>>(){});
		for(Map<String, String> item:map) {
			int userCode = Integer.parseInt(item.get("userCode"));
			int centerCode = Integer.parseInt(item.get("centerCode"));			
			result += managerService.cancelMapping(userCode, centerCode);
		}
		
		return result;
	}

	/**
	 * 담당자와 센터 간 맵핑 요청
	 * @author 임유진
	 * @param {String} {userCode:담당자코드, centerCode:센터코드} 형태
	 * @return {int} 반영된 맵핑 수
	 * */
	@RequestMapping(value="/mapping", method=RequestMethod.POST)
	public @ResponseBody int mapping(@RequestBody String req) throws Exception {
		//JSON 객체를 Map으로 받기위해 Jackson 라이브러리의 ObjectMapper 생성
		ObjectMapper mapper = new ObjectMapper();
		//req를 Map 객체로 역직렬화 
		Map<String, String> map = mapper.readValue(req, Map.class);
		
		int userCode = Integer.parseInt(map.get("userCode"));
		int centerCode = Integer.parseInt(map.get("centerCode"));
		
		return managerService.mapping(userCode, centerCode);
	}
	/* author 은별
	  담담자 엑셀 파일  히스토리  */
	@RequestMapping(value="/managerFileUploadHistory" , method=RequestMethod.GET)
	public String mgrUploadFileHistory(Model model) {
		model.addAttribute("mgrHistoryMapList", managerService.mgrUploadFileHistory());
		return  "jsp/manager/managerFileUpload";
	}
	
	/* author 은별
	  담담자 엑셀 파일 업로드 POSt 요청 */
	@RequestMapping(value="/managerFileUpload", method=RequestMethod.POST)
	public String managerFileUpload(MultipartHttpServletRequest request) {
		//MultipartHttpServletRequest을 사용하면 getFile 메소드를 통해 List 형태로 받을 수 있다
		MultipartFile file = request.getFile("mgrExcelFile");
		managerService.mgrUploadFileInfo(file, 3);
		logger.info("엑셀 등록"+file.toString());
		return "redirect: /manager/managerFileUploadHistory";
	}
}