
package com.mycompany.webapp.score.dao;

import java.util.List;

import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

/**
 * 점수 관리
 * ScoreMapper.xml과 매핑되어있음
 * 
 * */
public interface IScoreRepository {
	//유진
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
	int updateGroupCode(Map<String, String> groupCodeMap);
	int insertDetailCode(Map<String, String> detailCodeMap);
	int insertGroupCode(Map<String, String> groupCodeMap);
	
	//윤선
	List<ScoreVO> getScoreList();
	
	/*점수 등록*/
	void insertScore(ScoreVO score);
	/*점수 삭제*/
	void deleteScore(ScoreVO score);
	/*점수 수정,저장*/
	int saveScore(ScoreVO score);
}

