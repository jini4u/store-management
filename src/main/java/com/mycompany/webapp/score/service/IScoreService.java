package com.mycompany.webapp.score.service;

import java.util.List;
import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IScoreService {
	//유진
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
	int updateGroupCode(Map<String, String> groupCodeMap);
	int insertDetailCode(Map<String, String> detailCodeMap);
	int insertGroupCode(Map<String, String> groupCodeMap);
	
	Map<String, Integer> uploadFileInfo(MultipartFile file, int startRow);
	List<Map<String, String>> getScoreUploadHistory();
	
	//윤선
	List<ScoreVO> getScoreList(ScoreVO scoreVO);
	
	
	int insertScore(ScoreVO score);

	List<ScoreVO> usingCodeList();
	int updateScore(ScoreVO score);

}
