package com.mycompany.webapp.score.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.mycompany.webapp.score.service.IScoreService;
import com.mycompany.webapp.score.service.ScoreService;
import com.mycompany.webapp.score.vo.ScoreVO;

/**
 * @ClassName : ScoreController
 * @Description : 점수, 코드 관리 기능 구현 Controller
 * @Modification 
 * @    수정일            	수정자                 	  	수정내용
 * @ ===========  =========  =====================
 * @	1/3			임유진		updateDetailCode, updateGroupCode 작성
 * @author 임유진, 정윤선
 * **/

@Controller
public class ScoreController {

	@Autowired
	IScoreService scoreService;
	
	@RequestMapping("/scoreupload")
	public String scoreupload() {
		return "jsp/score/scoreupload";
		
	}
	
	@RequestMapping("/score")
	public String centerscoreinquiry(Model model) {
		model.addAttribute("scoreList",scoreService.getScoreList());
				return "jsp/score/scoreList";
	}

	@RequestMapping(value="/saveScore",method = RequestMethod.POST)
	public String saveScore(ScoreVO score){
		scoreService.saveScore(score);
		 return "redirect: /score";
	}
	
	/*
	 * 임유진
	 * DB에 존재하는 그룹코드 전체의 정보를 조뢰,
	 * 코드 관리 화면으로 이동
	 * */
	@RequestMapping(value="/code")
	public String code(Model model) {
		return "jsp/score/code";
	}
	
	/*
	 * 임유진
	 * 전체 그룹코드들을 조회한다
	 * @return List<Map<그룹코드번호, 코드명, 사용여부>>
	 * */
	@RequestMapping("/getGroupCodes")
	public @ResponseBody List<Map<String, String>> getGroupCodes() {
		return scoreService.getAllGroupCodes();
	}
	
	/*
	 * 임유진
	 * 해당 그룹코드의 상세코드들을 조회한다
	 * @return List<Map<상세코드번호, 코드명, 사용여부>>
	 * */
	@RequestMapping("/getDetailCodes/{groupCode}")
	public @ResponseBody List<Map<String, Object>> getDetailCodes(@PathVariable String groupCode) {
		return scoreService.getDetailCodes(groupCode);
	}
	
	/*
	 * 임유진
	 * 상세코드명, 사용여부를 수정한다.
	 * @return 수정된 행 갯수
	 * */
	@RequestMapping(value="/updateDetailCode", method=RequestMethod.POST)
	public @ResponseBody List<String> updateDetailCode(MultipartHttpServletRequest request) {
		//쿼리문 실행에 필요한 정보들을 담을 map 생성
		Map<String, String> detailCodeMap = new HashMap<String, String>();
		//요청하면서 넘어온 정보들 담아주기
		detailCodeMap.put("detailCode", request.getParameter("detailCode"));
		detailCodeMap.put("detailContent", request.getParameter("detailContent"));
		detailCodeMap.put("detailOccupied", request.getParameter("detailOccupied").toUpperCase());
		detailCodeMap.put("groupCode", request.getParameter("groupCode"));
		//쿼리문 결과를 담을 list 생성
		List<String> result = new ArrayList<>();
		//종류를 알리는 문자 추가
		result.add("updateDetail");
		//쿼리 결과 string으로 변환해 추가
		result.add(Integer.toString(scoreService.updateDetailCode(detailCodeMap)));

		return result;
	}
	
	/*
	 * 임유진
	 * 그룹코드명, 사용여부를 수정한다.
	 * @return 수정된 행 갯수
	 * */	
	@RequestMapping(value="/updateGroupCode", method=RequestMethod.POST)
	public @ResponseBody List<String> updateGroupCode(MultipartHttpServletRequest request) {
		Map<String, String> groupCodeMap = new HashMap<>();
		
		groupCodeMap.put("groupCode", request.getParameter("groupCode"));
		groupCodeMap.put("groupContent", request.getParameter("groupContent"));
		groupCodeMap.put("groupOccupied", request.getParameter("groupOccupied").toUpperCase());
		
		List<String> result = new ArrayList<>();
		result.add("updateGroup");
		result.add(Integer.toString(scoreService.updateGroupCode(groupCodeMap)));
		return result;
	}
}
