package com.mycompany.webapp.manager.controller;

import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.service.IManagerService;
import com.mycompany.webapp.manager.vo.ManagerVO;

@Controller
public class ManagerController {
	static final Logger logger=LoggerFactory.getLogger(ManagerController.class);

	
	@Autowired 
	IManagerService managerService;
	/* author ��蹂�
	   �떞�떦�옄 紐⑸줉 議고쉶*/
	@RequestMapping(value="/managerList")
	public String selectManagerList(@RequestParam(defaultValue="1") int pageNo,Model model) {
		int totalRows = managerService.countAllMgr();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<ManagerVO> managerList = managerService.selectManagerList(pager);
		for(int i=0;i<managerList.size();i++) {
			ManagerVO manager = managerList.get(i);
			int userCode = manager.getUserCode();
			List<CenterVO> centerList = managerService.getCenterByManager(userCode);
			manager.setCenterList(centerList);
		}
		model.addAttribute("managerList", managerList);
		model.addAttribute("pager", pager);
		int usercode = managerList.get(0).getUserCode();
		model.addAttribute("userCode",usercode+1);
		logger.info("managerList : " + managerList);
		

		return "jsp/manager/managerlookup";
	}
	
	/* author ��蹂�
	   �떞�떦�옄 �긽�꽭 議고쉶*/
	@RequestMapping(value="/managerDetail")
	public String selectManagerDetail(Model model, @PathVariable int userCode) {
		ManagerVO mgrDetails = managerService.selectManagerDetail(userCode);
		model.addAttribute("managerVO",mgrDetails);
		return "jsp/manager/managerdetail";
	}
	
	/* author ��蹂�
	  �떞�떦�옄 �벑濡� GET*/
	@GetMapping(value="/managerInsert")
	public String insertManager() {
		return "jsp/manager/managerlookup";
	}
	
	/* author ��蹂�
	  �떞�떦�옄 �벑濡� POST*/
	@ResponseBody
	@PostMapping(value="/managerInsert")
	public List<ManagerVO> insertManager(@RequestParam(defaultValue="1") int pageNo, ManagerVO mgr) {
		managerService.insertManager(mgr);
		int totalRows = managerService.countAllMgr();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<ManagerVO> managerList = managerService.selectManagerList(pager);
		return managerList;
	}

	/* author ��蹂�
	  �떞�떦�옄 �닔�젙 GET*/
	@GetMapping(value="/managerUpdate")
	public  String managerUpdate() {
		return "jsp/manager/managerlookup";
	}
	
	/* author ��蹂�
	  �떞�떦�옄 �닔�젙 POST*/
	@ResponseBody
	@PostMapping(value="/managerUpdate")
	public  List<ManagerVO> managerUpdate(@RequestParam(defaultValue="1") int pageNo, ManagerVO mgr) {
		managerService.managerUpdate(mgr);
		int totalRows = managerService.countAllMgr();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<ManagerVO> managerList = managerService.selectManagerList(pager);
		logger.info(managerList.toString());
		return managerList;
	}
	
	
	//�떞�떦�옄 留ㅽ븨
	@RequestMapping(value="/managerMapping")
	public String managerMapping(@RequestParam(defaultValue="1") int pageNo, Model model) {
		int totalRows = managerService.countAllMgr();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("managerList", managerService.selectManagerList(pager));
		return "jsp/manager/managermapping";
	}

	/**
	 * @author �엫�쑀吏�
	 * @describe �떞�떦�옄�뿉 �뵲�씪 �떞�떦�븯�뒗 �꽱�꽣 議고쉶
	 * @param {Integer} userCode �떞�떦�옄 userCode
	 * @return List<�떞�떦 以묒씤 CenterVO>
	 * */
	@RequestMapping(value="/getCenters/{userCode}")
	public @ResponseBody List<CenterVO> getCenterByManager(@PathVariable int userCode){
		return managerService.getCenterByManager(userCode);
	}

	/**
	 * @author �엫�쑀吏�
	 * �떞�떦�옄�� �꽱�꽣 媛� 留듯븨 �빐�젣
	 * @param String {userCode:�떞�떦�옄肄붾뱶, centerCode:�꽱�꽣肄붾뱶} �삎�깭
	 * @return int �빐�젣�맂 留듯븨 愿�怨� �닔
	 * */
	@RequestMapping(value="/cancelMapping", method=RequestMethod.POST)
	public @ResponseBody int cancelMapping(@RequestBody String req) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(req, Map.class);
		int userCode = Integer.parseInt(map.get("userCode"));
		int centerCode = Integer.parseInt(map.get("centerCode"));
		return managerService.cancelMapping(userCode, centerCode);
	}
	
	/**
	 * @author �엫�쑀吏�
	 * �떞�떦�옄�� �꽱�꽣 媛� 留듯븨 �슂泥�
	 * @param String {userCode:�떞�떦�옄肄붾뱶, centerCode:�꽱�꽣肄붾뱶} �삎�깭
	 * @return int 諛섏쁺�맂 留듯븨 �닔
	 * */
	@RequestMapping(value="/mapping", method=RequestMethod.POST)
	public @ResponseBody int mapping(@RequestBody String req) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(req, Map.class);
		int userCode = Integer.parseInt(map.get("userCode"));
		int centerCode = Integer.parseInt(map.get("centerCode"));
		return managerService.mapping(userCode, centerCode);
	}
}