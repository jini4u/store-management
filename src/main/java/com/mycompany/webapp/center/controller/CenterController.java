package com.mycompany.webapp.center.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
			pager = new Pager(12, 10, totalRows, pageNo);
			centerList = centerService.centerList(pager);
		}else {
			String keywordType = "CN";
			int totalRows = centerService.filterCountAllCenters(keyword, keywordType);
			if (totalRows != 0) {
				pager = new Pager(12, 10, totalRows, pageNo);
				centerList = centerService.findCenter(pager, keyword, keywordType);
			}else {
				model.addAttribute("pager", new Pager(1, 1, 1, 1));
				model.addAttribute("centerListN" , "empty");
			}
		}
		model.addAttribute("keyword", keyword);
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
			}else {
				model.addAttribute("pager", new Pager(1, 1, 1, 1));
				model.addAttribute("centerListN" , "empty");
			}
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("keywordType", keywordType);
		return "jsp/center/centerlist";
	}

	/**
	 * 센터 정보 수정
	 * @author 이소정
	 * @param {CenterVO} 수정할 센터VO
	 * @return {CenterVO} 수정된 센터VO
	 * */
	@ResponseBody
	@PostMapping(value ="/centerupdate")
	public CenterVO centerUpdate(CenterVO centerVO){
		centerService.centerUpdate(centerVO);
		return centerVO;
	}


	/**
	 * 센터 정보 일괄 업로드
	 * */
	@GetMapping(value="/centerexcelupload")
	public String excelUplaod(@RequestParam(defaultValue="1")int pageNo, Model model) {
		int totalRows = centerService.countUploadHistory();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		model.addAttribute("pager", pager);
		model.addAttribute("historyMapList", centerService.getCenterUploadHistory(pager));
		return "jsp/center/centerexcelupload";
	}

	@PostMapping(value="/centerexcelupload")
	public String excelUplaod(MultipartHttpServletRequest request, HttpSession session, @RequestParam(defaultValue="1")int pageNo, Model model) {
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
	 * @param {int} 현재 페이지 번호
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
	@PostMapping("/centernamecheck")
	@ResponseBody
	public int centerNameCheck(@RequestParam String centerName) {
		return centerService.centerNameCheck(centerName);
	}
	
	
	@PostMapping("/checkcentertel")
	@ResponseBody
	public int checkCenterTel(@RequestParam String centerTel) {
		return centerService.checkCenterTel(centerTel);

	}

	/**
	 * 센터 조회 결과 엑셀파일로 다운로드
	 * @author 임유진
	 * @param {String} 키워드유형
	 * @param {String} 키워드
	 * @return {String} 생성된 파일 이름
	 * */
	@RequestMapping("/centerlistdownload")
	public @ResponseBody String centerListDownload(@RequestParam(required=false) String keywordType, @RequestParam(required=false)String keyword) {
		int totalRows = centerService.filterCountAllCenters(keyword, keywordType);
		Pager pager = new Pager(totalRows, 1, totalRows, 1);
		List<CenterVO> centerList = centerService.findCenter(pager, keyword, keywordType);
		
		String fileName = "";
		Date date = new Date();
		if(keyword==null || keyword.equals("")) {
			fileName = "CenterList_"+date.getTime()+".xlsx";
		} else {
			fileName = "CenterList_"+keyword+"_"+date.getTime()+".xlsx";
		}

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("CenterList");

		int rownum = 0;

		Row row0 = sheet.createRow(rownum);

		CellStyle titleStyle = workbook.createCellStyle();
		
		titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(BorderStyle.THIN);
		
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue("센터코드");
		cell0.setCellStyle(titleStyle);

		Cell cell1 = row0.createCell(1);
		cell1.setCellValue("센터명");
		cell1.setCellStyle(titleStyle);

		Cell cell2 = row0.createCell(2);
		cell2.setCellValue("전화번호");
		cell2.setCellStyle(titleStyle);

		Cell cell3 = row0.createCell(3);
		cell3.setCellValue("주소");
		cell3.setCellStyle(titleStyle);
		
		Cell cell4 = row0.createCell(4);
		cell4.setCellValue("오시는길");
		cell4.setCellStyle(titleStyle);
		
		Cell cell5 = row0.createCell(5);
		cell5.setCellValue("오픈일");
		cell5.setCellStyle(titleStyle);
		
		Cell cell6 = row0.createCell(6);
		cell6.setCellValue("폐점일");
		cell6.setCellStyle(titleStyle);
		
		Cell cell7 = row0.createCell(7);
		cell7.setCellValue("운영여부");
		cell7.setCellStyle(titleStyle);
		
		for(CenterVO center:centerList) {
			Row row = sheet.createRow(++rownum);
			int cellnum = 0;
			row.createCell(cellnum++).setCellValue(center.getCenterCode());
			row.createCell(cellnum++).setCellValue(center.getCenterName());
			row.createCell(cellnum++).setCellValue(center.getCenterTel());
			row.createCell(cellnum++).setCellValue(center.getCenterAddress());
			row.createCell(cellnum++).setCellValue(center.getCenterGuide());
			row.createCell(cellnum++).setCellValue(center.getCenterOpeningDate());
			row.createCell(cellnum++).setCellValue(center.getCenterClosingDate());
			row.createCell(cellnum++).setCellValue(center.getCenterCondition());
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(filePath, fileName));
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}