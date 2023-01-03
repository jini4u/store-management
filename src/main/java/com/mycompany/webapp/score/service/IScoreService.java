package com.mycompany.webapp.score.service;

import java.util.List;
import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

public interface IScoreService {
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	List<ScoreVO> getScoreList();
	void insertScore(ScoreVO score);
	void deleteScore(ScoreVO score);
	int saveScore(ScoreVO score);
}
