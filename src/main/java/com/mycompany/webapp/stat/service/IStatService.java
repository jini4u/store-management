package com.mycompany.webapp.stat.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.score.vo.ScoreVO;

public interface IStatService {
	Map<String, List<ScoreVO>> getCenterAvgScores(int centerCode);
	Map<String, List<ScoreVO>> getManagerAvgScores(int userCode);
	List<Map<String, Object>> getCodes();
	Map<String, List<ScoreVO>> getAvgScoreByCheckCode(String groupCode, int detailCode, int userCode);
	
	Map<String, String> getCenterSubStat(int centerCode, int checkYear, int checkSeason);
	Map<String, String> getManagerSubStat(int userCode, int checkYear, int checkSeason);
	Map<String, String> getCodeSubStat(int userCode, int checkYear, int checkSeason);
}
