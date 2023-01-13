
package com.mycompany.webapp.score.dao;

import java.util.List;

import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;

/**
 * 점수 관리
 * ScoreMapper.xml과 매핑되어있음
 * 
 * */
public interface IScoreRepository {
	//유진
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
	int updateGroupCode(Map<String, String> groupCodeMap);
	int insertDetailCode(Map<String, String> detailCodeMap);
	int insertGroupCode(Map<String, String> groupCodeMap);
	
	int isDataExist(ScoreVO scoreVO);
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
}

