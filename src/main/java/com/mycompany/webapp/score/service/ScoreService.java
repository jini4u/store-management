package com.mycompany.webapp.score.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.score.dao.IScoreRepository;

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
}
