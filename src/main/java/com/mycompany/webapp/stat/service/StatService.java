package com.mycompany.webapp.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.score.controller.ScoreController;
import com.mycompany.webapp.score.dao.IScoreRepository;
import com.mycompany.webapp.score.vo.ScoreVO;
import com.mycompany.webapp.stat.dao.IStatRepository;

@Service
public class StatService implements IStatService {
	private static Logger logger = LoggerFactory.getLogger(StatService.class);
	
	@Autowired
	IStatRepository statRepository;
	@Autowired
	IScoreRepository scoreRepository;
	
	/**
	 * 	센터 코드를 이용한 센터 평균 점수와 모든 센터들의 점수 조회
	 *  @author 임유진 
	 *  @param {int} 센터 코드 
	 *  @return {Map<String, List<ScoreVO>>} 전체 평균과 센터 평균들을 담은 리스트 
	 * */
	@Override
	public Map<String, List<ScoreVO>> getCenterAvgScores(int centerCode) {
		Map<String, List<ScoreVO>> responseMap = new HashMap<String, List<ScoreVO>>();
		responseMap.put("entireAvg", statRepository.getAvgScoreByCenterCode(0));
		responseMap.put("centerAvg", statRepository.getAvgScoreByCenterCode(centerCode));
		return responseMap;
	}

	/**
	 * 담당자 코드를 이용한 담당자 평균 점수와 모든 담당자의 점수 조회
	 * @author 임유진
	 * @param {int} 담당자 코드
	 * @return {Map<String, List<ScoreVO>>} 전체 평균과 담당자 평균들을 담은 리스트
	 * */
	@Override
	public Map<String, List<ScoreVO>> getManagerAvgScores(int userCode) {
		Map<String, List<ScoreVO>> responseMap = new HashMap<String, List<ScoreVO>>();
		responseMap.put("entireAvg", statRepository.getAvgScoreByUserCode(0));
		responseMap.put("managerAvg", statRepository.getAvgScoreByUserCode(userCode));
		return responseMap;
	}
	
	/**
	 * 모든 점수 코드를 조회 
	 * @author 임유진 
	 * @return {List<Map<String, Object>>} 모든 그룹코드와 하위 상세코드들을 담은 리스트 
	 * */
	@Override
	public List<Map<String, Object>> getCodes() {
		List<Map<String, Object>> responseMap = scoreRepository.getAllGroupCodes();
		for(Map<String, Object> groupCodeMap:responseMap) {
			String groupCode = (String) groupCodeMap.get("CHECK_GROUP_CODE");
			List<Map<String, Object>> detailCodes = scoreRepository.getDetailCodes(groupCode);
			groupCodeMap.put("detailCodes", detailCodes);
		}
		return responseMap;
	}
}
