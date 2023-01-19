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
	//점수등록	
	int insertScore(ScoreVO score);
	//모달 점수 등록
	List<ScoreVO> usingCodeList();
	//점수 수정
	int updateScore(ScoreVO score);
	//페이징처리를 위한 갯수
	int CountAllList();
	//센터리스트 가져오기
	List<ScoreVO> getCenterName(ScoreVO userCode);

}
