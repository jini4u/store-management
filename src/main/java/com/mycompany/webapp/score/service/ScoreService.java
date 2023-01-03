package com.mycompany.webapp.score.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.score.dao.IScoreRepository;
import com.mycompany.webapp.score.vo.ScoreVO;

@Service
public class ScoreService implements IScoreService {

	@Autowired
	IScoreRepository scoreRepository;
	
	@Override
	public List<Map<String, String>> getAllGroupCodes() {
		return scoreRepository.getAllGroupCodes();
	}

	@Override
	public List<Map<String, Object>> getDetailCodes(String groupCode) {
		return scoreRepository.getDetailCodes(groupCode);
	}
	
	
	
	
	
	//아래는 구현해야함
	@Override
	public List<ScoreVO> getScoreList() {
		return scoreRepository.getScoreList();
	}

	@Override
	public void insertScore(ScoreVO score) {
		scoreRepository.saveScore(score);
	}

	@Override
	public void deleteScore(ScoreVO score) {
	}

	@Override
	public int saveScore(ScoreVO score) {
		return scoreRepository.saveScore(score);
	}
}
