package com.mycompany.webapp.score.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.score.service.IScoreService;
import com.mycompany.webapp.score.vo.ScoreVO;


/**
 * @ClassName : ScoreController
 * @Description : 점수, 코드 관리 기능 구현 Controller
 * @author 임유진, 정윤선
 * **/
@RequestMapping("/score")
@Controller
public class ScoreController {
	private static Logger log = LoggerFactory.getLogger(ScoreController.class);

	@Autowired
	IScoreService scoreService;
	/*
	 * 정윤선
	 * 엑셀파일 일괄 업로드
	 * */

	@RequestMapping(value="/scoreupload", method=RequestMethod.GET)
	public String scoreupload(Model model) {
		model.addAttribute("historyMapList", scoreService.getScoreUploadHistory());
		return "jsp/score/scoreupload";
	}

	/**
	 * 엑셀 파일 업로드 POST 요청을 처리
	 * @author 임유진
	 * @return 엑셀 파일 업로드 화면으로 redirect
	 * */
	@RequestMapping(value="/scorefileupload", method=RequestMethod.POST)
	public String scoreFileUpload(MultipartHttpServletRequest request, HttpSession session){
		//request에서 업로드한 파일 얻기
		MultipartFile file = request.getFile("scoreExcelFile");
		int userCode = (int)session.getAttribute("userCode");
		//service에서 인덱스 3까지는 무시하고 처리하도록 함
		scoreService.uploadFileInfo(file, 3, userCode);
		return "redirect:/score/scoreupload";
	}

	/*
	 *정윤선
	 * DB에 존재하는 값중에 점검년도,분기,항목,상세항목,점수 전체의 정보를 조회
	 * */

	@RequestMapping(value="/scorelist", method = RequestMethod.GET)
	public String centerscoreinquiry(@RequestParam(defaultValue="1") int pageNo,@RequestParam(defaultValue="0")int centerCode, @RequestParam(defaultValue="0")int checkYear, @RequestParam(defaultValue="0")int checkSeason,Model model,HttpSession session) {

		int userCode = 10004;

		ScoreVO scoreVO = new ScoreVO();
		scoreVO.setCenterCode(centerCode);
		scoreVO.setCheckYear(checkYear);
		scoreVO.setCheckSeason(checkSeason);

		/*		
	 	세션에서 가져와서 값을 넣을 수 있도록 변경----------------------
		int centerCode = (Integer) session.getAttribute("centerCode");
		int userCode = (Integer) session.getAttribute("userCode");
		------------------------------------------------  */	

		//scoreVo를 getScoreList로 담아 socreList로 만듬
		int totalRows = scoreService.countListByCenterCode(scoreVO);
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<ScoreVO> scoreList = scoreService.getScoreList(scoreVO, pager);
		//scoreList를 view페이지로 보내주기 위해서 model에 받음
		model.addAttribute("scoreList",scoreList);
		model.addAttribute("pager", pager);	
		model.addAttribute("centerName",scoreService.getCenterName(userCode));
		model.addAttribute("userCode",userCode);


		//비어있는 ScoreVO 생성
		ScoreVO emptyVO = new ScoreVO();
		//getScoreList 하기위해서 centerCode는 무조건 필요하므로 1로 set
		emptyVO.setCenterCode(1);
		//빈 VO를 이용해서 getScoreList : emptyVO에 센터코드만 있고 년도, 시즌 없으니까 쿼리문에서 WHERE절에 if조건으로 안걸려서 전체 점수 정보가 다 담겨있음
		List<ScoreVO> allScoreList = scoreService.getScoreList(emptyVO, pager);
		//전체 리스트 크기가 0보다 크면 (점수 테이블에 값이 있으면)
		if(allScoreList.size() > 0) {

			//제일 최근 정보가 0번이므로 0번의 정보를 담아줌
			int maxYear = allScoreList.get(0).getCheckYear();  
			int maxSeason = allScoreList.get(0).getCheckSeason();
			//뷰페이지로 가져갈수있게 담아주기
			model.addAttribute("maxYear", maxYear);
			model.addAttribute("maxSeason", maxSeason);
		}

		Calendar now = Calendar.getInstance();
		int yy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH) +1;

		int season=0;
		int year=0;

		//분기 설정
		//1.만약 mm(월) 0보다크거나 3보다 작거나같으면 season 은1분기....
		if( (mm-3)>0 &&(mm-3)<=3) {
			season = 1;
		}else if((mm-3) > 3 && (mm-3) <= 6) {
			season = 2;
		}else if((mm-3) > 6 && (mm-3) <= 9){	
			season = 3;
		}else if((mm-3) <= 12){	
			season = 4;
		}else{
			season = 0;
		}

		//2번째 i문 mm(월)이 3보다 작거나 같으면 yy(년)에 -1 
		//그게 아니라면 현재 년도가 나오면 됨
		if((mm-3)<=0) {
			year = yy-1;
		}else {
			year = yy;
		}
		model.addAttribute("year",year);
		model.addAttribute("season",season);
		//모달창 점수 항목 출력 리스트
		model.addAttribute("usingCodeList", scoreService.usingCodeList());
		//센터 버튼에 센터 이름 출력
		model.addAttribute("centerName",scoreService.getCenterName(userCode));

		return "jsp/score/scoreList";
	}

	/*
	 * 정윤선
	 * 점수 수정
	 * 값을 화면에 보내줌
	 * */

	@RequestMapping(value="/updateScore", method=RequestMethod.POST)
	public String updateGetScore(int[] arrayScore, int[] arrayDetailCode,
			String[] arrayGroupCode, ScoreVO scoreVO,HttpServletRequest request) {		
		
		scoreVO.setArrayScore(arrayScore);
		/*System.out.println(Arrays.toString(scoreVO.getArrayScore()));*/
		scoreVO.setArrayCheckGroupCode(arrayGroupCode);
		scoreVO.setArrayCheckDetailCode(arrayDetailCode);
		
		scoreService.updateScore(scoreVO);
		return "redirect:/score/scorelist?centerCode="+scoreVO.getCenterCode()+"&checkYear="+scoreVO.getCheckYear()+"&checkSeason="+scoreVO.getCheckSeason();
	}
	/*
	 * 정윤선
	 * 점수 등록
	 * (모달창에서)
	 * */ 
	
	@RequestMapping(value="/insertscore", method=RequestMethod.POST)
	public String insertsocre(int[] arrayScore, int[] arrayDetailCode,
								String[] arrayGroupCode, ScoreVO scoreVO, HttpServletRequest request, Model model) {

		scoreVO.setArrayScore(arrayScore);
		scoreVO.setArrayCheckGroupCode(arrayGroupCode);
		scoreVO.setArrayCheckDetailCode(arrayDetailCode);
		//다 담기
		scoreService.insertScore(scoreVO);
		

		return "redirect:/score/scorelist?centerCode="+scoreVO.getCenterCode();
	}	

	/*
	 * 정윤선
	 * 버튼을 누르면 해당 센터(담당자 별 센터) 점수 리스트 설정
	 * */  
	@RequestMapping(value="/getcenters/{userCode}")
	public @ResponseBody List<ScoreVO> getCenterName(@PathVariable int userCode,Model model){
		return scoreService.getCenterName(userCode);

	}
	/**
	 * DB에 존재하는 그룹코드 전체의 정보를 조회, 코드 관리 화면으로 이동
	 * @author 임유진
	 * */
	@RequestMapping(value="/code")
	public String code(Model model) {
		return "jsp/score/code";
	}

	/**
	 * 전체 그룹코드들을 조회한다
	 * @author 임유진
	 * @return {List<Map<String, Object>>} 그룹코드번호, 코드명, 사용여부를 담은 Map의 List 
	 * */
	@RequestMapping("/getgroupcodes")
	public @ResponseBody List<Map<String, Object>> getGroupCodes() {
		return scoreService.getAllGroupCodes();
	}

	/**
	 * 해당 그룹코드의 상세코드들을 조회한다
	 * @author 임유진
	 * @param {String} 그룹코드명 
	 * @return {List<Map<String, Object>>} 상세코드번호, 코드명, 사용여부를 담은 Map의 List
	 * */
	@RequestMapping("/getdetailcodes/{groupCode}")
	public @ResponseBody List<Map<String, Object>> getDetailCodes(@PathVariable String groupCode) {
		return scoreService.getDetailCodes(groupCode);
	}

	/**
	 * 상세코드명, 사용여부를 수정한다.
	 * @author 임유진
	 * @return {int} 수정된 행 갯수
	 * */
	@RequestMapping(value="/updatedetailcode", method=RequestMethod.POST)
	public @ResponseBody int updateDetailCode(MultipartHttpServletRequest request) {
		//쿼리문 실행에 필요한 정보들을 담을 map 생성
		Map<String, String> detailCodeMap = new HashMap<String, String>();
		//요청하면서 넘어온 정보들 담아주기
		detailCodeMap.put("detailCode", request.getParameter("detailCode"));
		detailCodeMap.put("detailContent", request.getParameter("detailContent"));
		detailCodeMap.put("detailOccupied", request.getParameter("detailOccupied").toUpperCase());
		detailCodeMap.put("groupCode", request.getParameter("groupCode"));

		return scoreService.updateDetailCode(detailCodeMap);
	}

	/**
	 * 그룹코드명, 사용여부를 수정한다.
	 * @author 임유진
	 * @return {List<String>} group, 수정된 행 갯수
	 * */   
	@RequestMapping(value="/updategroupcode", method=RequestMethod.POST)
	public @ResponseBody List<String> updateGroupCode(MultipartHttpServletRequest request) {
		Map<String, String> groupCodeMap = new HashMap<>();

		groupCodeMap.put("groupCode", request.getParameter("groupCode"));
		groupCodeMap.put("groupContent", request.getParameter("groupContent"));
		groupCodeMap.put("groupOccupied", request.getParameter("groupOccupied").toUpperCase());

		List<String> result = new ArrayList<>();
		result.add("group");
		result.add(Integer.toString(scoreService.updateGroupCode(groupCodeMap)));
		return result;
	}

	/**
	 * 상세코드 추가
	 * @author 임유진
	 * @return {int} 입력된 행 갯수
	 * */   
	@RequestMapping(value="/insertdetailcode", method=RequestMethod.POST)
	public @ResponseBody int insertDetailCode(MultipartHttpServletRequest request) {
		//쿼리문 실행에 필요한 정보들을 담을 map 생성
		Map<String, String> detailCodeMap = new HashMap<String, String>();
		//요청하면서 넘어온 정보들 담아주기
		detailCodeMap.put("detailCode", request.getParameter("detailCode"));
		detailCodeMap.put("detailContent", request.getParameter("detailContent"));
		detailCodeMap.put("detailOccupied", request.getParameter("detailOccupied").toUpperCase());
		detailCodeMap.put("groupCode", request.getParameter("groupCode"));   

		return scoreService.insertDetailCode(detailCodeMap);
	}

	/**
	 * 그룹코드 추가
	 * @author 임유진
	 * @return {List<String>} group, 입력된 행 갯수
	 * */
	@RequestMapping(value="/insertgroupcode", method=RequestMethod.POST)
	public @ResponseBody List<String> insertGroupCode(MultipartHttpServletRequest request) {
		Map<String, String> groupCodeMap = new HashMap<>();

		groupCodeMap.put("groupCode", request.getParameter("groupCode"));
		groupCodeMap.put("groupContent", request.getParameter("groupContent"));
		groupCodeMap.put("groupOccupied", request.getParameter("groupOccupied").toUpperCase());

		List<String> result = new ArrayList<>();
		result.add("group");
		result.add(Integer.toString(scoreService.insertGroupCode(groupCodeMap)));

		return result;
	}
}