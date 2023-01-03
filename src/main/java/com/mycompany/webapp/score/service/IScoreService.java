package com.mycompany.webapp.score.service;

import java.util.List;
import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

public interface IScoreService {
	//유진
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
	int updateGroupCode(Map<String, String> groupCodeMap);
	
	//윤선
	List<ScoreVO> getScoreList();
	void insertScore(ScoreVO score);
	void deleteScore(ScoreVO score);
	int saveScore(ScoreVO score);
}
