package com.mycompany.webapp.center.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.mycompany.webapp.center.service.ICenterService;
import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
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
	
	@Value("${file.path}")
	private String filePath;

	@RequestMapping(value="/centerPhoto")
	public String manageCenterPhoto(@RequestParam(defaultValue="1") int pageNo, Model model) {
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<CenterVO> centerList = centerService.centerList(pager);
		model.addAttribute("centerList", centerList);
		model.addAttribute("pager", pager);
		return "jsp/center/centerphoto";
	}
	/*//url은 value에 적혀있는 값으로 동작하고, centerlist.jsp 페이지를 로딩해준다?
	@GetMapping(value="/centerInsert")
	public String insertCenter(Model model) {
		return "jsp/center/centerlist";
	}*/
	//물어보기
	@ResponseBody
	@PostMapping(value="/centerInsert")
	public List<CenterVO> insertCenter(@RequestParam(defaultValue="1") int pageNo, CenterVO centerVO) {
		centerVO.setCenterCode(centerService.getLastCenterCode());
		System.out.println("센터인서트"+centerVO);
		centerService.insertCenter(centerVO);
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<CenterVO> list = centerService.centerList(pager);
		return list;
	}

	@GetMapping(value="/centerList")
	public String centerList(@RequestParam(defaultValue="1")int pageNo, Model model, CenterVO centerVO){
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("newCenterCode", centerService.getLastCenterCode());
		model.addAttribute("centerList", centerService.centerList(pager));
		model.addAttribute("pager", pager);
		return "jsp/center/centerlist";
	}
	
	@ResponseBody
	@PostMapping(value ="/centerUpdate")
	public List<CenterVO> centerUpdate(@RequestParam(defaultValue="1")int pageNo, Model model, CenterVO centerVO){
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("centerList", centerService.centerList(pager));
		centerService.centerUpdate(centerVO);
		List<CenterVO> centerList = centerService.centerList(pager);
		System.out.println(centerList);
		return centerList;
	}
	@ResponseBody
	@PostMapping(value ="/findCenter")
	public List<CenterVO> findCenter(@RequestParam(defaultValue="1")int pageNo, CenterVO centerVO, Model model) {
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("pager", pager);
		centerVO.setCenterName(centerVO.getCenterName());
		System.out.println(centerVO.getCenterName());
		List<CenterVO> centerList = centerService.findCenter(pager,centerVO);
		return centerList;
	}
	
	@GetMapping(value="/centerExcelUpload")
	public String excelUplaod() {
		return "jsp/center/excelupload";
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
			if(center.getUserCode() == 0) {
				result.add(center);
			}
		}
		return result;
	}
	
	/**
	 * @author 임유진
	 * 센터 이미지 등록
	 * @return int 등록된 사진 수
	 * */
	@RequestMapping(value="/addCenterImage", method=RequestMethod.POST)
	public @ResponseBody int addCenterImage(MultipartHttpServletRequest request) {
		String fileDetail = request.getParameter("fileDetail");
		int centerCode = Integer.parseInt(request.getParameter("centerCode"));
		int uploadUserCode = Integer.parseInt(request.getParameter("uploadUserCode"));
		List<MultipartFile> files = request.getFiles("centerImage");
		
		return centerService.addCenterImage(fileDetail, centerCode, uploadUserCode, files);
	}
	
	/**
	 * @author 임유진
	 * 센터 이미지 조회
	 * @param int 센터 코드
	 * @return List<해당 코드의 사진>
	 * */
	@RequestMapping("/getCenterImages/{centerCode}")
	public @ResponseBody List<FileInfoVO> getCenterImages(@PathVariable int centerCode) {
		//센터코드에 맞춰서 파일리스트 리턴해주면 됨
		return centerService.getCenterImageNames(centerCode);
	}
	
	/**
	 * @author 임유진
	 * 센터 이미지 정보 수정
	 * @return 정보 수정된 파일 수 (0 또는 1)
	 * */
	@RequestMapping(value="/updateImage", method=RequestMethod.POST)
	public @ResponseBody int updateImage(MultipartHttpServletRequest request) throws Exception {
		FileInfoVO file = new FileInfoVO();
		
		file.setFileNo(Integer.parseInt(request.getParameter("fileNo")));
		file.setOriginalName(request.getParameter("newOriginalName"));
		file.setFileDetail(request.getParameter("fileDetail"));
		int centerCode = Integer.parseInt(request.getParameter("centerCode"));
		String oldOriginalName = (String)request.getParameter("oldOriginalName");
		
		return centerService.updateImage(file, centerCode, oldOriginalName);
	}
	
	/**
	 * @author 임유진
	 * 센터 이미지 삭제
	 * @return 삭제된 파일 수
	 * */
	@RequestMapping(value="/deleteImage/{centerCode}", method=RequestMethod.POST)
	public @ResponseBody int deleteImage(@RequestBody String request, @PathVariable int centerCode) {
		List<Integer> fileNoList = new ArrayList<Integer>();
		for(String fileNo:request.split(",")) {
			fileNoList.add(Integer.parseInt(fileNo));
		}
		return centerService.deleteImage(fileNoList, centerCode);
	}
}