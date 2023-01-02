package com.mycompany.webapp.score.service;

import java.util.List;
import java.util.Map;

public interface IScoreService {
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
}
