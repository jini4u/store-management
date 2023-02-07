package com.mycompany.webapp.center.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

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
 * @author 이소정, 임유진 
 * **/
@RequestMapping("/center")
@Controller
public class CenterController {
	private static Logger logger = LoggerFactory.getLogger(CenterController.class);
	@Autowired
	ICenterService centerService;

	//local.properties에 있는 file.path
	@Value("${file.path}")
	private String filePath;

	/**
	 * 센터별 사진을 조회
	 * @author 임유진
	 * @param {int} 페이지 번호
	 * @return centerphoto.jsp
	 * */
	@RequestMapping(value="/centerphoto")
	public String manageCenterPhoto(@RequestParam(defaultValue="1") int pageNo, @RequestParam(required=false)String keyword, Model model) {
		Pager pager = null; 
		List<CenterVO> centerList = null;
		if (keyword == null) {
			int totalRows = centerService.countAllCenters();
			pager = new Pager(10, 10, totalRows, pageNo);
			centerList = centerService.centerList(pager);
		}else {
			String keywordType = "CN";
			int totalRows = centerService.filterCountAllCenters(keyword, keywordType);
			if (totalRows != 0) {
				pager = new Pager(10, 10, totalRows, pageNo);
				centerList = centerService.findCenter(pager, keyword, keywordType);
				model.addAttribute("keyword", keyword);
			}else {
				model.addAttribute("pager", new Pager(1, 1, 1, 1));
				model.addAttribute("centerListN" , "empty");
			}
		}
		model.addAttribute("centerList", centerList);
		model.addAttribute("pager", pager);
		return "jsp/center/centerphoto";
	}

	/**
	 * 센터 정보 등록
	 * @author 이소정
	 * @param int 페이지 번호, centerVO
	 * @return List<센터 정보 리스트>
	 * */
	@ResponseBody
	@PostMapping(value="/centerinsert")
	public List<CenterVO> insertCenter(@RequestParam(defaultValue="1") int pageNo, CenterVO centerVO) {
		//		centerVO.setCenterCode(centerService.getLastCenterCode());
		centerService.insertCenter(centerVO);
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<CenterVO> list = centerService.centerList(pager);
		return list;
	}

	/**
	 * 센터 정보 리스트
	 * @author 이소정
	 * @param int 페이지 번호, keyword, model, centerVO
	 * @return centerlist.jsp
	 * */
	@GetMapping(value="/centerlist")
	public String centerList(@RequestParam(defaultValue="1")int pageNo, @RequestParam(required=false) String keywordType, 
			@RequestParam(required=false)String keyword, Model model, CenterVO centerVO){
		if (keyword == null) {
			int totalRows = centerService.countAllCenters();
			Pager pager = new Pager(10, 10, totalRows, pageNo);
			model.addAttribute("pager", pager);	
			model.addAttribute("centerList", centerService.findCenter(pager, keyword ,keywordType));
			logger.info( "센터리스트" +centerVO.getCenterName());
		}else{
			int filterTotalRows = centerService.filterCountAllCenters(keyword, keywordType);
			Pager filterPager = new Pager(10, 10, filterTotalRows, pageNo);
			List<CenterVO> filterCenterList = centerService.findCenter(filterPager, keyword ,keywordType);
			if (filterCenterList.size() != 0) {
				model.addAttribute("centerList", filterCenterList);
				model.addAttribute("pager", filterPager);
				model.addAttribute("keyword", keyword);
				model.addAttribute("keywordType", keywordType);
			}else {
				model.addAttribute("pager", new Pager(1, 1, 1, 1));
				model.addAttribute("centerListN" , "empty");
			}
		}
		return "jsp/center/centerlist";
	}

	/**
	 * 센터 정보 수정
	 * @author 이소정
	 * @param int 페이지 번호, model, centerVO
	 * @return List<센터리스트>
	 * */
	@ResponseBody
	@PostMapping(value ="/centerupdate")
	public CenterVO centerUpdate(@RequestParam(defaultValue="1") int pageNo, Model model, CenterVO centerVO){
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		centerService.centerUpdate(centerVO);
		return centerVO;
	}


	/**
	 * 센터 정보 일괄 업로드
	 * */
	@GetMapping(value="/centerexcelupload")
	public String excelUplaod(Model model, @RequestParam(defaultValue="1")int pageNo) {
		int totalRows = centerService.countUploadHistory();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("pager", pager);
		model.addAttribute("historyMapList", centerService.getCenterUploadHistory(pager));
		return "jsp/center/excelupload";
	}

	//MultipartHttpServletRequest 는 여러개의 파일을 업로드할 때 사용하는데 
	//우리는 왜 사용? 값을 list로 받아올 수 있기 때문인가?
	@PostMapping(value="/centerexcelupload")
	public String excelUplaod(MultipartHttpServletRequest request, HttpSession session, @RequestParam(defaultValue="1")int pageNo, Model model) {
		//request에서 업로드한 파일 얻기, getFile안에 있는 건 이름을 정해주는 건가?
		MultipartFile file = request.getFile("centerExcelFile");
		int userCode = (int)session.getAttribute("userCode");
		int totalRows = centerService.countUploadHistory();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("pager", pager);
		model.addAttribute("historyMap", centerService.getCenterUploadHistory(pager));
		centerService.centerUploadFile(file, 3, userCode);

		return "redirect:/center/centerexcelupload";
	}

	/**
	 * 담당자가 지정되어있지 않은 센터 조회
	 * @author 임유진
	 * @return {List<맵핑가능센터>}
	 * */
	@RequestMapping("/availcenter")
	public @ResponseBody List<Object> getAvailableCenterList(@RequestParam(defaultValue="1") int pageNo){
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(totalRows, 1, totalRows, 1);
		List<CenterVO> allCenterList = centerService.centerList(pager);
		List<Object> result = new ArrayList<>();
		for(CenterVO center:allCenterList) {
			if(center.getUserCode() == 0) {
				result.add(center);
			}
		}
		pager = new Pager(9,4,result.size(), pageNo);
		result.add(pager);
		return result;
	}

	/**
	 * 센터 이미지 등록
	 * @author 임유진
	 * @return {int} 등록된 사진 수
	 * */
	@RequestMapping(value="/addcenterimage", method=RequestMethod.POST)
	public @ResponseBody int addCenterImage(MultipartHttpServletRequest request) {
		String fileDetail = request.getParameter("fileDetail");
		int centerCode = Integer.parseInt(request.getParameter("centerCode"));
		int uploadUserCode = Integer.parseInt(request.getParameter("uploadUserCode"));
		List<MultipartFile> files = request.getFiles("centerImage");

		return centerService.addCenterImage(fileDetail, centerCode, uploadUserCode, files);
	}

	/**
	 * 센터 이미지 조회
	 * @author 임유진
	 * @param {int} 센터 코드
	 * @return {List<FileInfoVO>} 해당 코드의 사진들 리스트 
	 * */
	@RequestMapping("/getcenterimages/{centerCode}")
	public @ResponseBody List<FileInfoVO> getCenterImages(@PathVariable int centerCode) {
		return centerService.getCenterImageNames(centerCode);
	}

	/**
	 * 센터 이미지 정보 수정
	 * @author 임유진
	 * @return {int} 정보 수정된 파일 수 (0 또는 1)
	 * */
	@RequestMapping(value="/updateimage", method=RequestMethod.POST)
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
	 * 센터 이미지 삭제
	 * @author 임유진
	 * @return {int} 삭제된 파일 수
	 * */
	@RequestMapping(value="/deleteimage/{centerCode}", method=RequestMethod.POST)
	public @ResponseBody int deleteImage(@RequestBody String request, @PathVariable int centerCode) {
		List<Integer> fileNoList = new ArrayList<Integer>();
		for(String fileNo:request.split(",")) {
			fileNoList.add(Integer.parseInt(fileNo));
		}
		return centerService.deleteImage(fileNoList, centerCode);
	}
	/**
	 * 센터 이름 중복 확인
	 * @author 이소정
	 * @return {int} 센터명 수
	 * */
	@PostMapping("/centerNameCheck")
	@ResponseBody
	public int centerNameCheck(@RequestParam String centerName) {
		return centerService.centerNameCheck(centerName);
	}
	@PostMapping("/checkCenterTel")
	@ResponseBody
	public int checkCenterTel(@RequestParam String centerTel) {
		return centerService.checkCenterTel(centerTel);
		
	}
}