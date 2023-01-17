package com.mycompany.webapp.center.controller;

import java.util.ArrayList;

import java.util.List;

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
@RequestMapping("/center")
@Controller
public class CenterController {
	private static Logger logger = LoggerFactory.getLogger(CenterController.class);
	@Autowired
	ICenterService centerService;
	
	@Value("${file.path}")
	private String filePath;

	/**
	 * 
	 * @param 점포 사진을 조회한다.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/centerPhoto")
	public String manageCenterPhoto(@RequestParam(defaultValue="1") int pageNo, Model model) {
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<CenterVO> centerList = centerService.centerList(pager);
		model.addAttribute("centerList", centerList);
		model.addAttribute("pager", pager);
		return "jsp/center/centerphoto";
	}

	@ResponseBody
	@PostMapping(value="/centerInsert")
	public List<CenterVO> insertCenter(@RequestParam(defaultValue="1") int pageNo, CenterVO centerVO) {
//		centerVO.setCenterCode(centerService.getLastCenterCode());
		centerService.insertCenter(centerVO);
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<CenterVO> list = centerService.centerList(pager);
		return list;
	}

	/**
	 * 
	 * @param 점포 리스트를 조회한다.
	 * @param model
	 * @return
	 */
	@GetMapping(value="/centerList")
	public String centerList(@RequestParam(defaultValue="1")int pageNo,@RequestParam(value="keyword", required=false)String keyword, Model model, CenterVO centerVO){
		if (keyword == null) {
		int totalRows = centerService.countAllCenters();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("pager", pager);	
		model.addAttribute("centerList", centerService.findCenter(pager, keyword));
		logger.info( "센터리스트" +centerVO.getCenterName());
		}else{
			int filterTotalRows = centerService.filterCountAllCenters(keyword);
			Pager filterPager = new Pager(10, 10, filterTotalRows, pageNo);
			List<CenterVO> filterCenterList = centerService.findCenter(filterPager, keyword);
			if (filterCenterList.size() != 0) {
				model.addAttribute("centerList", filterCenterList);
				model.addAttribute("pager", filterPager);
			}else {
				model.addAttribute("centerList", filterCenterList);
				model.addAttribute("pager", new Pager(1, 1, 1, 1));
				
			}
		}
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
/*	@ResponseBody
	@PostMapping(value ="/findCenter")
	public List<CenterVO> findCenter(@RequestParam(defaultValue="1")int pageNo, CenterVO centerVO, Model model) {
		int totalRows = centerService.filterCountAllCenters(centerVO.getCenterName());
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("pager", pager);
		System.out.println(centerVO.getCenterName());
		List<CenterVO> centerList = centerService.findCenter(pager,centerVO);
		logger.info(centerList+"");
		return centerList;
	}
	*/
	
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
	 * 센터 이미지 등록
	 * @author 임유진
	 * @return {int} 등록된 사진 수
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
	 * 센터 이미지 조회
	 * @author 임유진
	 * @param {int} 센터 코드
	 * @return {List<해당 코드의 사진>}
	 * */
	@RequestMapping("/getCenterImages/{centerCode}")
	public @ResponseBody List<FileInfoVO> getCenterImages(@PathVariable int centerCode) {
		//센터코드에 맞춰서 파일리스트 리턴해주면 됨
		return centerService.getCenterImageNames(centerCode);
	}
	
	/**
	 * 센터 이미지 정보 수정
	 * @author 임유진
	 * @return {int} 정보 수정된 파일 수 (0 또는 1)
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
	 * 센터 이미지 삭제
	 * @author 임유진
	 * @return {int} 삭제된 파일 수
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