package com.mycompany.webapp.score.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycompany.webapp.score.service.IScoreService;
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
@Controller
public class ScoreController {
	private static Logger logger = LoggerFactory.getLogger(ScoreController.class);

	@Autowired
	IScoreService scoreService;
	/*
	 * 정윤선
	 * 엑셀파일 일괄 업로드
	 * */

	@RequestMapping("/scoreupload")
	public String scoreupload() {
		return "jsp/score/scoreupload";

	}

	/*
	 *정윤선
	 * DB에 존재하는 값중에 점검년도,분기,항목,상세항목,점수 전체의 정보를 조회
	 * */

	@RequestMapping(value="/score", method = RequestMethod.GET)
	public String centerscoreinquiry(ScoreVO scoreVO, Model model,HttpSession session) {
		//로그인에 처리할 내용-------------------------------------
		session.setAttribute("centerCode", 1);
		session.setAttribute("userCode", 10006);
		//------------------------------------------------

		scoreVO.setCenterCode(1);
		
		List<ScoreVO> scoreList = scoreService.getScoreList(scoreVO);
		model.addAttribute("scoreList",scoreList);
		
		//기본날짜 설정
		Calendar now = Calendar.getInstance();
		int yy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH) +1;

		int season=0;
		int year=0;

		//분기 설정
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

		if((mm-3)<=0) {
			year = yy-1;
		}else {
			year = yy;
		}


		model.addAttribute("year", yy);
		model.addAttribute("season", mm);
		//모달창 점수 항목 출력 리스트
		model.addAttribute("usingCodeList", scoreService.usingCodeList());

		return "jsp/score/scoreList";
	}



	/*
	 * 정윤선
	 * 점수 수정
	 * */
	@RequestMapping(value="/updateScore", method=RequestMethod.POST)
	public String updateScore(ScoreVO score,Model model){
		
		scoreService.updateScore(score);
		return "redirect:/score";
	}

	/*
	 * 정윤선
	 * 점수 등록
	 * (모달창에서)
	 * */   

	@RequestMapping(value="/insertScore", method=RequestMethod.POST)
	public String insertsocre(ScoreVO scoreVO) {
		
		scoreService.insertScore(scoreVO);

		return "redirect:/score";
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