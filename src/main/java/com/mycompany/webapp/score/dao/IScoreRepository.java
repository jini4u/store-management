
package com.mycompany.webapp.score.dao;

import java.util.List;

import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

public interface IScoreRepository {
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
	List<ScoreVO> getScoreList();
	/*게시글 정보 목록 조회*/
	//List<Score> selectArticleListBy
	
	/*점수 등록*/
	void insertScore(ScoreVO score);
	/*점수 삭제*/
	void deleteScore(ScoreVO score);
	/*점수 수정*/
	void updateScore(ScoreVO score);
}

