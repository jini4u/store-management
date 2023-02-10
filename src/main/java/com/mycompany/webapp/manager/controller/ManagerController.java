package com.mycompany.webapp.manager.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	//local.properties에 있는 file.path
	@Value("${file.path}")
	private String filePath;

	/* author 은별
	       담당자 목록조회*/
	@RequestMapping(value="/managerlist")
	public String selectManagerList(@RequestParam(defaultValue="1") int pageNo,Model model) {
		int totalRows = managerService.countAllMgr();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<ManagerVO> managerList = managerService.selectManagerList(pager);
		model.addAttribute("managerList", managerList);
		model.addAttribute("pager", pager);
		model.addAttribute("mgrURL","/manager/managerlist");

		logger.info("managerList : " + managerList);
		return "jsp/manager/managerlookup";
	}

	/* author 은별
	  담당자 등록 POST*/
	@ResponseBody
	@PostMapping(value="/managerinsert")
	public int insertManager(ManagerVO mgr) {		
		return managerService.insertManager(mgr);
	}

	/* author 은별
	  담담자 수정 POST*/
	@ResponseBody
	@PostMapping(value="/managerupdate")
	public ManagerVO managerUpdate(@RequestParam(defaultValue="1") int pageNo, ManagerVO mgr) {
		managerService.managerUpdate(mgr);
		return mgr;
	}

	/* author 은별
	  담담자 검색 */
	@GetMapping(value="/managersearch")
	public String managerSearch(@RequestParam(defaultValue="1") int pageNo, @RequestParam("keyword") String keyword,
			@RequestParam("keywordType") String keywordType, Model model){
		int keywordTotalRows = managerService.managerCountByKeyword(keyword, keywordType);
		Pager searchPager = new Pager(10, 10, keywordTotalRows, pageNo);
		model.addAttribute("pager", searchPager);

		List<ManagerVO> mgrSearchList = managerService.managerSearch(searchPager, keyword, keywordType);
		if (mgrSearchList.size() != 0) {
			model.addAttribute("managerList", mgrSearchList);
			model.addAttribute("pager", searchPager);
			model.addAttribute("mgrURL","/manager/managersearch");
		}else {
			model.addAttribute("pager", new Pager(1, 1, 1, 1));
			model.addAttribute("managerListCheck", "empty");
		}
		model.addAttribute("keyword",keyword);
		model.addAttribute("keywordType",keywordType);
		return "jsp/manager/managerlookup";
	}

	/**
	 * 담당자 맵핑 페이지로 이동 
	 * @param {int} 페이지 번호
	 * @param {String} 키워드
	 * @param {String} 키워드 종류
	 * @author 임유진
	 * */
	@RequestMapping(value="/managermapping")
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
	@RequestMapping(value="/getcenters/{userCode}")
	public @ResponseBody List<CenterVO> getCenterByManager(@PathVariable int userCode){
		return managerService.getCenterByManager(userCode);
	}

	/**
	 * 담당자와 센터 간 맵핑 해제
	 * @author 임유진
	 * @param {String} [{userCode:담당자코드, centerCode:센터코드},...] 형태
	 * @return {int} 해제된 맵핑 관계 수
	 * */
	@RequestMapping(value="/cancelmapping", method=RequestMethod.POST)
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
	@RequestMapping(value="/managerfileuploadhistory" , method=RequestMethod.GET)
	public String mgrUploadFileHistory(@RequestParam(defaultValue="1") int pageNo,Model model) {
		int fileTotalRows = managerService.mgrUploadFileTotalCount();
		Pager pager = new Pager(10, 10, fileTotalRows, pageNo);
		model.addAttribute("mgrHistoryMapList", managerService.mgrUploadFileHistory(pager));
		model.addAttribute("pager", pager);
		model.addAttribute("mgrURL","/manager/managerfileuploadhistory");
		return  "jsp/manager/managerExcelUpload";
	}

	/* author 은별
	  담담자 엑셀 파일 업로드 POSt 요청 */
	@RequestMapping(value="/managerfileupload", method=RequestMethod.POST)
	public String managerFileUpload(MultipartHttpServletRequest request, HttpSession session) {
		//MultipartHttpServletRequest을 사용하면 getFile 메소드를 통해 List 형태로 받을 수 있다
		MultipartFile file = request.getFile("mgrExcelFile");
		//getAttribute 하면 object로 받아와지므로 캐스팅 해줘야 한다
		int userCode = (int)session.getAttribute("userCode");
		managerService.mgrUploadFileInfo(file, 3, userCode);
		return "redirect: /manager/managerfileuploadhistory";
	}

	/**
	 * 담당자 조회 결과 엑셀파일로 다운로드
	 * @author 임유진
	 * @param {int} 센터코드
	 * @param {int} 년도
	 * @param {int} 분기
	 * @return {String} 생성된 파일 이름
	 * */
	@RequestMapping("/managerlistdownload")
	public @ResponseBody String centerListDownload(@RequestParam(required=false) String keywordType, @RequestParam(required=false)String keyword) {
		int totalRows = managerService.managerCountByKeyword(keyword, keywordType);
		Pager pager = new Pager(totalRows, 1, totalRows, 1);
		List<ManagerVO> managerList = managerService.managerSearch(pager, keyword, keywordType);

		String fileName = "";
		Date date = new Date();
		if(keyword==null || keyword.equals("") || keyword.equals("null")) {
			fileName = "ManagerList_"+date.getTime()+".xlsx";
		} else {
			fileName = "ManagerList_"+keyword+"_"+date.getTime()+".xlsx";
		}

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("ManagerList");

		int rownum = 0;

		Row row0 = sheet.createRow(rownum);

		CellStyle titleStyle = workbook.createCellStyle();

		titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(BorderStyle.THIN);

		Cell cell0 = row0.createCell(0);
		cell0.setCellValue("담당자코드");
		cell0.setCellStyle(titleStyle);

		Cell cell1 = row0.createCell(1);
		cell1.setCellValue("담당자명");
		cell1.setCellStyle(titleStyle);

		Cell cell2 = row0.createCell(2);
		cell2.setCellValue("생년월일");
		cell2.setCellStyle(titleStyle);

		Cell cell3 = row0.createCell(3);
		cell3.setCellValue("휴대전화번호");
		cell3.setCellStyle(titleStyle);

		Cell cell4 = row0.createCell(4);
		cell4.setCellValue("이메일");
		cell4.setCellStyle(titleStyle);

		Cell cell5 = row0.createCell(5);
		cell5.setCellValue("팀코드");
		cell5.setCellStyle(titleStyle);

		Cell cell6 = row0.createCell(6);
		cell6.setCellValue("입사일자");
		cell6.setCellStyle(titleStyle);

		Cell cell7 = row0.createCell(7);
		cell7.setCellValue("퇴사일자");
		cell7.setCellStyle(titleStyle);

		for(ManagerVO manager:managerList) {
			Row row = sheet.createRow(++rownum);
			int cellnum = 0;
			row.createCell(cellnum++).setCellValue(manager.getUserCode());
			row.createCell(cellnum++).setCellValue(manager.getUserName());
			row.createCell(cellnum++).setCellValue(manager.getUserBirth());
			row.createCell(cellnum++).setCellValue(manager.getUserTel());
			row.createCell(cellnum++).setCellValue(manager.getUserEmail());
			row.createCell(cellnum++).setCellValue(manager.getUserTeamCode());
			row.createCell(cellnum++).setCellValue(manager.getUserHireDate());
			row.createCell(cellnum++).setCellValue(manager.getUserResignDate());
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(filePath, fileName));
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileName;
	}

}