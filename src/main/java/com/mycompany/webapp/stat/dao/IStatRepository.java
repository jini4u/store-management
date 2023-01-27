package com.mycompany.webapp.stat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.score.vo.ScoreVO;

@Repository
public interface IStatRepository {
	//센터별 평균 점수. 0인경우 전체 평균
	List<ScoreVO> getAvgScoreByCenterCode(int centerCode);
	//담당자별 평균 점수. 0인경우 전체 평균
	List<ScoreVO> getAvgScoreByUserCode(int userCode);
	//점수 코드별 담당 센터들 평균
	List<ScoreVO> getAvgScoreByCheckCode(@Param("groupCode") String groupCode, @Param("detailCode") int detailCode, @Param("userCode") int userCode, @Param("centerCode") int centerCode);
	//센터별 최고 점수 항목, 갯수
	String getBestCenterScoreCodeInSeason(@Param("centerCode") int centerCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	int countBestCenterScoreCodeInSeason(@Param("centerCode") int centerCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	//센터별 최저 점수 항목, 갯수
	String getWorstCenterScoreCodeInSeason(@Param("centerCode") int centerCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	int countWorstCenterScoreCodeInSeason(@Param("centerCode") int centerCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	//센터별 해당 분기 담당자
	String getCenterManagerInSeason(@Param("centerCode") int centerCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	//담당자별 최고 점수 항목, 갯수
	String getBestManagerScoreCodeInSeason(@Param("userCode") int userCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	int countBestManagerScoreCodeInSeason(@Param("userCode") int userCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	//선택 분기 우수담당자, 인원수
	String getBestManagerInSeason(@Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	int countBestManagerInSeason(@Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	//선택 분기 신규 담당자 수
	int countNewManagerInSeason(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("checkSeason") int checkSeason);
	
	String getBestCenterInSeason(@Param("userCode") int userCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	String getBestCodeInSeason(@Param("userCode") int userCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
	String getWorstCodeInSeason(@Param("userCode") int userCode, @Param("checkYear") int checkYear, @Param("checkSeason") int checkSeason);
}
