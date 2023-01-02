package com.mycompany.webapp.score.dao;

import java.util.List;

import com.mycompany.webapp.score.vo.ScoreVO;

public interface IScoreRepository {
	
	
	List<ScoreVO> getScoreList();
	/*게시글 정보 목록 조회*/
	List<Score> selectArticleListBy
	
	/*점수 등록*/
	void insertScore(ScoreVO score);
	/*점수 삭제*/
	void deleteScore(ScoreVO score);
	/*점수 수정*/
	void updateScore(ScoreVO score);
	
	
}
