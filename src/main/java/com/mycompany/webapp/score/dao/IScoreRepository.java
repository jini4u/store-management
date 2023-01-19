
package com.mycompany.webapp.score.dao;

import java.util.List;

import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 점수 관리
 * ScoreMapper.xml과 매핑되어있음
 * */
@Repository
public interface IScoreRepository {
	//유진
	//전체 그룹코드 조회 
	List<Map<String, Object>> getAllGroupCodes();
	//그룹코드에 따른 상세코드 조회 
	List<Map<String, Object>> getDetailCodes(String groupCode);
	//상세코드 수정 
	int updateDetailCode(Map<String, String> detailCodeMap);
	//그룹코드 수정 
	int updateGroupCode(Map<String, String> groupCodeMap);
	//상세코드 등록 
	int insertDetailCode(Map<String, String> detailCodeMap);
	//그룹코드 등록 
	int insertGroupCode(Map<String, String> groupCodeMap);
	//점수가 존재여부 확인 
	int isDataExist(ScoreVO scoreVO);
	//점수 엑셀 파일 업로드 이력 조회 
	List<Map<String, Object>> getScoreUploadHistory();
	
	//윤선
	List<ScoreVO> getScoreList(ScoreVO scoreVO);
	
	/*점수 등록*/
	int insertScore(ScoreVO score);
	/*해당년도 리스트*/	
	List<ScoreVO> comboboxList(ScoreVO score);
	
	/*모달 점수 리스트 */
	List<ScoreVO>usingCodeList();
	/*점수 수정,저장*/
	int updateScore(ScoreVO score);
	/*점수리스트 수 받아오기*/
	int CountAllList();
	/*username 받아오기*/
	List<ScoreVO> getCenterName(ScoreVO userCode);
}

