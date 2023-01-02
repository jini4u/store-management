package com.mycompany.webapp.score.service;

import java.util.List;

import com.mycompany.webapp.score.vo.ScoreVO;

public interface IScoreService {

	List<ScoreVO> getScoreList();
	void insertScore(ScoreVO score);
	void deleteScore(ScoreVO score);
	void updateScore(ScoreVO score);
}
