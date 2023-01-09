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
	int insertDetailCode(Map<String, String> detailCodeMap);
	int insertGroupCode(Map<String, String> groupCodeMap);
	
	//윤선
	List<ScoreVO> getScoreList(ScoreVO scoreVO);
	
	
	int insertScore(ScoreVO score);

	List<ScoreVO> usingCodeList();
	int deleteScore(ScoreVO score);
	int saveScore(ScoreVO score);

}
