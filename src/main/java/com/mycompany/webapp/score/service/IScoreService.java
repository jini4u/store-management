package com.mycompany.webapp.score.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.score.vo.ScoreVO;

public interface IScoreService {
	//유진
	List<Map<String, Object>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
	int updateGroupCode(Map<String, String> groupCodeMap);
	int insertDetailCode(Map<String, String> detailCodeMap);
	int insertGroupCode(Map<String, String> groupCodeMap);
	
	Map<String, Integer> uploadFileInfo(MultipartFile file, int startRow, int userCode);
	List<Map<String, String>> getScoreUploadHistory(int pageNo);
	
	Pager getHistoryPager(int pageNo);
	
	//윤선
	List<ScoreVO> getScoreList(ScoreVO scoreVO, Pager pager);
	//점수등록	
	int insertScore(ScoreVO score);
	//모달 점수 등록
	List<ScoreVO> usingCodeList();
	//점수 수정
	int updateScore(ScoreVO score);
	//페이징처리를 위한 갯수
	int countAllList();
	int countListByCenterCode(ScoreVO score);
	//센터리스트 가져오기
	List<ScoreVO> getCenterName(int userCode);
	int overlapGroupCode(String groupCode);
	int overlapDetailCode(String groupContent);

	int overlapGroupDetailCode(String detailCode, String groupCode);
	int overlapGroupDetailContent(String detailcontent);


}
