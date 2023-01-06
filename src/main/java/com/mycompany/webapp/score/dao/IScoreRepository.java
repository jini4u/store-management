
package com.mycompany.webapp.score.dao;

import java.util.List;

import com.mycompany.webapp.score.vo.ScoreVO;
import java.util.Map;
<<<<<<< HEAD

=======
import org.apache.ibatis.annotations.Mapper;
>>>>>>> branch 'master' of https://github.com/jini4u/store-management.git

<<<<<<< HEAD
/**
 * 점수 관리
 * ScoreMapper.xml과 매핑되어있음
 * 
 * */
=======
>>>>>>> branch 'master' of https://github.com/jini4u/store-management.git

public interface IScoreRepository {
	//유진
	List<Map<String, String>> getAllGroupCodes();
	List<Map<String, Object>> getDetailCodes(String groupCode);
	
	int updateDetailCode(Map<String, String> detailCodeMap);
	int updateGroupCode(Map<String, String> groupCodeMap);
	int insertDetailCode(Map<String, String> detailCodeMap);
	int insertGroupCode(Map<String, String> groupCodeMap);
	
	//윤선
	List<ScoreVO> getScoreList(int centerCode);
	
	/*점수 등록*/
	int insertScore(ScoreVO score);
	/*모달 점수 리스트 */
	List<ScoreVO>usingCodeList();
	/*점수 삭제*/
	int deleteScore(ScoreVO score);
	/*점수 수정,저장*/
	int saveScore(ScoreVO score);
}

