package com.mycompany.webapp.score.dao;

import java.util.List;
import java.util.Map;

public interface IScoreRepository {
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
}
