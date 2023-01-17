package com.mycompany.webapp.stat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mycompany.webapp.score.vo.ScoreVO;

@Repository
public interface IStatRepository {
	List<ScoreVO> getAvgScoreByCenterCode(int centerCode);
	List<ScoreVO> getAvgScoreByUserCode(int userCode);
}
