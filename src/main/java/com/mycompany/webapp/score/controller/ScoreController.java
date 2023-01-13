package com.mycompany.webapp.score.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.score.service.IScoreService;
import com.mycompany.webapp.score.service.ScoreService;
import com.mycompany.webapp.score.vo.ScoreVO;


/**
 * @ClassName : ScoreController
 * @Description : 점수, 코드 관리 기능 구현 Controller
 * @Modification 
 * @    수정일               수정자                         수정내용
 * @ ===========  =========  =====================
 * @   1/3         임유진      updateDetailCode, updateGroupCode 작성
 * @               정윤선      saveScoreCode,
 * @   1/4         정윤선      insertCode작성, scoreList수정
 * @  1/9		     정윤선	   /score수정
 * @author 임유진, 정윤선
 * **/
@RequestMapping("/score")
@Controller
public class ScoreController {
	private static Logger logger = LoggerFactory.getLogger(ScoreController.class);

	@Autowired
	IScoreService scoreService;
	/*
	 * 정윤선
	 * 엑셀파일 일괄 업로드
	 * */

	@RequestMapping(value="/scoreupload", method=RequestMethod.GET)
	public String scoreupload() {
		return "jsp/score/scoreupload";
	}
	
	@RequestMapping(value="/scorefileupload", method=RequestMethod.POST)
	public List<String> scoreFileUpload(MultipartHttpServletRequest request){
		MultipartFile file = request.getFile("scoreExcelFile");
		List<String> resultList = new ArrayList<String>();
		resultList.add(file.getName());
		return resultList;
	}
	/*
	 *정윤선
	 * DB에 존재하는 값중에 점검년도,분기,항목,상세항목,점수 전체의 정보를 조회
	 * */

	@RequestMapping(value="/scorelist", method = RequestMethod.GET)
	public String centerscoreinquiry(@RequestParam(defaultValue="1") int pageNo,ScoreVO scoreVO, Model model,HttpSession session) {
		int totalRows = scoreService.CountAllList();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
	
		
		//로그인에 처리할 내용-------------------------------------
		session.setAttribute("centerCode", 1);
		session.setAttribute("userCode", 10006);
		//------------------------------------------------

		scoreVO.setCenterCode(1);
		//scoreVo를 getScoreList로 담아 socreList로 만듬
		List<ScoreVO> scoreList = scoreService.getScoreList(scoreVO);
		//scoreList를 view페이지로 보내주기 위해서 model에 담음
		model.addAttribute("scoreList",scoreList);
		
		//비어있는 ScoreVO 생성
		ScoreVO emptyVO = new ScoreVO();
		//getScoreList 하기위해서 centerCode는 무조건 필요하므로 1로 set
		emptyVO.setCenterCode(1);
		//빈 VO를 이용해서 getScoreList : emptyVO에 센터코드만 있고 년도, 시즌 없으니까 쿼리문에서 WHERE절에 if조건으로 안걸려서 전체 점수 정보가 다 담겨있음
		//List<ScoreVO> getScoreList(ScoreVO scoreVO) : 리턴값의 자료형이 List<ScoreVO>임. 
		List<ScoreVO> allScoreList = scoreService.getScoreList(emptyVO);
		//전체 리스트 크기가 0보다 크면 (점수 테이블에 값이 있으면)
		if(allScoreList.size() > 0) {
			
			//제일 최근 정보가 0번이므로 0번의 정보를 담아줌
			int maxYear = allScoreList.get(0).getCheckYear();			
			int maxSeason = allScoreList.get(0).getCheckSeason();
			//뷰페이지로 가져갈수있게 담아주기
			model.addAttribute("maxYear", maxYear);
			model.addAttribute("maxSeason", maxSeason);
			model.addAttribute("pager",pager);
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
		
		//2번째 i문 mm(월)이 0보다 작거나 같으면 yy(년)에 -1 
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

		return "jsp/score/scoreList";
	}



	/*
	 * 정윤선
	 * 점수 수정
	 * */
	
	

	//점수를 변경해줌
//	@RequestMapping(value="/updateScore", method=RequestMethod.POST)
//	public String updateScore(ScoreVO score,Model model){
//		scoreService.updateScore(score);
//		score.setCenterCode(1);
//		System.out.println("점수 : "+scoreService.updateScore(score));
//		model.addAttribute("scoreList",scoreService.getScoreList(score));
//
//		return "redirect:/score/scorelist";
//	}
//	
	//값을 화면에 보내줌
	@RequestMapping(value="/updateSave")
	public String updateGetScore(ScoreVO score, Model model) {
		
		scoreService.updateScore(score);
		//score랑 같은 년도,분기인 점수들의 list. 결국에는 score 이용해서 getScoreList를 하면 되겠죠?
		List<ScoreVO> getScoreList = scoreService.getScoreList(score); 
		model.addAttribute("scoreList",getScoreList);
		
		return "redirect:/score/scorelist";
		
	}

	/*
	 * 정윤선
	 * 점수 등록
	 * (모달창에서)
	 * */   

	@RequestMapping(value="/insertScore", method=RequestMethod.POST)
	public String insertsocre(ScoreVO scoreVO) {
		scoreService.insertScore(scoreVO);

		return "redirect:/score/scorelist";
	}

	/**
	 * 임유진
	 * DB에 존재하는 그룹코드 전체의 정보를 조회,
	 * 코드 관리 화면으로 이동
	 * */
	@RequestMapping(value="/code")
	public String code(Model model) {
		return "jsp/score/code";
	}

	/**
	 * 임유진
	 * 전체 그룹코드들을 조회한다
	 * @return List<Map<그룹코드번호, 코드명, 사용여부>>
	 * */
	@RequestMapping("/getGroupCodes")
	public @ResponseBody List<Map<String, String>> getGroupCodes() {
		return scoreService.getAllGroupCodes();
	}

	/**
	 * 임유진
	 * 해당 그룹코드의 상세코드들을 조회한다
	 * @return List<Map<상세코드번호, 코드명, 사용여부>>
	 * */
	@RequestMapping("/getDetailCodes/{groupCode}")
	public @ResponseBody List<Map<String, Object>> getDetailCodes(@PathVariable String groupCode) {
		return scoreService.getDetailCodes(groupCode);
	}

	/**
	 * 임유진
	 * 상세코드명, 사용여부를 수정한다.
	 * @return 수정된 행 갯수
	 * */
	@RequestMapping(value="/updateDetailCode", method=RequestMethod.POST)
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
	 * 임유진
	 * 그룹코드명, 사용여부를 수정한다.
	 * @return group, 수정된 행 갯수
	 * */   
	@RequestMapping(value="/updateGroupCode", method=RequestMethod.POST)
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
	 * 임유진
	 * 상세코드 추가
	 * @return 입력된 행 갯수
	 * */   
	@RequestMapping(value="/insertDetailCode", method=RequestMethod.POST)
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
	 * 임유진
	 * 그룹코드 추가
	 * @return group, 입력된 행 갯수
	 * */
	@RequestMapping(value="/insertGroupCode", method=RequestMethod.POST)
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