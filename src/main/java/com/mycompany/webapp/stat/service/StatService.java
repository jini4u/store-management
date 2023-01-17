package com.mycompany.webapp.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.score.vo.ScoreVO;
import com.mycompany.webapp.stat.dao.IStatRepository;

@Service
public class StatService implements IStatService {
	@Autowired
	IStatRepository statRepository;
	
	/**
	 * 	센터 코드를 이용한 센터 평균 점수와 모든 센터들의 점수 조회
	 *  @author 임유진 
	 *  @param {int} 센터 코드 
	 *  @return {Map<String, List<ScoreVO>>} 전체 평균과 센터 평균들을 담은 리스트 
	 * */
	@Override
	public Map<String, List<ScoreVO>> getAvgScores(int centerCode) {
		Map<String, List<ScoreVO>> responseMap = new HashMap<String, List<ScoreVO>>();
		responseMap.put("entireAvg", statRepository.getAvgScoreByCenterCode(0));
		responseMap.put("centerAvg", statRepository.getAvgScoreByCenterCode(centerCode));
		return responseMap;
	}

}
