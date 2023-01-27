package com.mycompany.webapp.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.manager.dao.IManagerRepository;
import com.mycompany.webapp.score.controller.ScoreController;
import com.mycompany.webapp.score.dao.IScoreRepository;
import com.mycompany.webapp.score.vo.ScoreVO;
import com.mycompany.webapp.stat.dao.IStatRepository;

@Service
public class StatService implements IStatService {
	private static Logger logger = LoggerFactory.getLogger(StatService.class);
	
	@Autowired
	private IStatRepository statRepository;
	@Autowired
	private IScoreRepository scoreRepository;
	@Autowired
	private IManagerRepository managerRepository;
	
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
	
	/**
	 * 점수코드별 담당 센터들 점수 조회
	 * @author 임유진
	 * @param {String} 그룹코드
	 * @param {int} 상세코드
	 * @param {int} 담당자 코드
	 * @return {Map<String, List<ScoreVO>>} 요청한 코드의 담당센터별 평균 점수들을 담은 Map 
	 * */
	@Override
	public Map<String, List<ScoreVO>> getAvgScoreByCheckCode(String groupCode, int detailCode, int userCode) {
		Map<String, List<ScoreVO>> responseMap = new HashMap<String, List<ScoreVO>>();
		List<CenterVO> centers = managerRepository.getCenterByManager(userCode);
		for(CenterVO center:centers) {
			responseMap.put("centerAvg"+center.getCenterName(), statRepository.getAvgScoreByCheckCode(groupCode, detailCode, userCode, center.getCenterCode()));
		}
		return responseMap;
	}
	
	/**
	 * 센터 서브 통계 (최고점수항목, 최저점수항목, 해당분기 담당자)
	 * @author 임유진
	 * @param {int} 센터코드
	 * @param {int} 년도
	 * @param {int} 분기
	 * @return {Map<String, String>} 각 정보를 담은 Map
	 * */
	@Override
	public Map<String, String> getCenterSubStat(int centerCode, int checkYear, int checkSeason) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("bestCodeName", statRepository.getBestCenterScoreCodeInSeason(centerCode, checkYear, checkSeason));
		responseMap.put("countBestCode", String.valueOf(statRepository.countBestCenterScoreCodeInSeason(centerCode, checkYear, checkSeason)));
		
		responseMap.put("worstCodeName", statRepository.getWorstCenterScoreCodeInSeason(centerCode, checkYear, checkSeason));
		responseMap.put("countWorstCode", String.valueOf(statRepository.countWorstCenterScoreCodeInSeason(centerCode, checkYear, checkSeason)));
		
		responseMap.put("managerName", statRepository.getCenterManagerInSeason(centerCode, checkYear, checkSeason));
		return responseMap;
	}
	
	/**
	 * 담당자 서브 통계 (최고점수항목, 해당분기우수담당자, 해당분기신입사원수)
	 * @author 임유진
	 * @param {int} 담당자코드
	 * @param {int} 년도
	 * @param {int} 분기
	 * @return {Map<String, String>} 각 정보를 담은 Map
	 * */
	@Override
	public Map<String, String> getManagerSubStat(int userCode, int checkYear, int checkSeason) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("bestCodeName", statRepository.getBestManagerScoreCodeInSeason(userCode, checkYear, checkSeason));
		responseMap.put("countBestCode", String.valueOf(statRepository.countBestManagerScoreCodeInSeason(userCode, checkYear, checkSeason)));
		
		responseMap.put("bestManager", statRepository.getBestManagerInSeason(checkYear, checkSeason));
		responseMap.put("countBestManager", String.valueOf(statRepository.countBestManagerInSeason(checkYear, checkSeason)));
		
		String fromDate = String.valueOf(checkYear-1)+"/12/31";
		String toDate = String.valueOf(checkYear+1)+"/01/01";
		responseMap.put("countNewManager", String.valueOf(statRepository.countNewManagerInSeason(fromDate, toDate, checkSeason)));
		return responseMap;
	}
	
	@Override
	public Map<String, String> getCodeSubStat(int userCode, int checkYear, int checkSeason) {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("bestCenter", statRepository.getBestCenterInSeason(userCode, checkYear, checkSeason));
		responseMap.put("bestCode", statRepository.getBestCodeInSeason(userCode, checkYear, checkSeason));
		responseMap.put("worstCode", statRepository.getWorstCodeInSeason(userCode, checkYear, checkSeason));
		return responseMap;
	}
}
