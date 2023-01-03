
package com.mycompany.webapp.score.dao;

import java.util.List;

import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

public interface IScoreRepository {
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	List<ScoreVO> getScoreList();
	
	/*점수 등록*/
	void insertScore(ScoreVO score);
	/*점수 삭제*/
	void deleteScore(ScoreVO score);
	/*점수 수정,저장*/
	int saveScore(ScoreVO score);
}

