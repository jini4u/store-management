package com.mycompany.webapp.score.vo;

import java.util.Arrays;
import java.util.List;

public class ScoreVO {
	private int checkYear;
	private int checkSeason;
	private String checkGroupCode;
	private String checkGroupContent;
	private String checkDetailContent;
	private int checkScore;
	
	private int checkDetailCode;
	private int centerCode;
	private int userCode;
	
	private String[] arrayCheckGroupCode;
	private int[] arrayCheckDetailCode;
	private int[] arrayScore;
	
	private List<ScoreVO> scoreVOList;
	
	public int getCheckYear() {
		return checkYear;
	}
	public void setCheckYear(int checkYear) {
		this.checkYear = checkYear;
	}
	public int getCheckSeason() {
		return checkSeason;
	}
	public void setCheckSeason(int checkSeason) {
		this.checkSeason = checkSeason;
	}
	public String getCheckGroupCode() {
		return checkGroupCode;
	}
	public void setCheckGroupCode(String checkGroupCode) {
		this.checkGroupCode = checkGroupCode;
	}
	public String getCheckGroupContent() {
		return checkGroupContent;
	}
	public void setCheckGroupContent(String checkGroupContent) {
		this.checkGroupContent = checkGroupContent;
	}
	public String getCheckDetailContent() {
		return checkDetailContent;
	}
	public void setCheckDetailContent(String checkDetailContent) {
		this.checkDetailContent = checkDetailContent;
	}
	public int getCheckScore() {
		return checkScore;
	}
	public void setCheckScore(int checkScore) {
		this.checkScore = checkScore;
	}
	public int getCheckDetailCode() {
		return checkDetailCode;
	}
	public void setCheckDetailCode(int checkDetailCode) {
		this.checkDetailCode = checkDetailCode;
	}
	public int getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(int centerCode) {
		this.centerCode = centerCode;
	}
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public String[] getArrayCheckGroupCode() {
		return arrayCheckGroupCode;
	}
	public void setArrayCheckGroupCode(String[] arrayCheckGroupCode) {
		this.arrayCheckGroupCode = arrayCheckGroupCode;
	}
	public int[] getArrayCheckDetailCode() {
		return arrayCheckDetailCode;
	}
	public void setArrayCheckDetailCode(int[] arrayCheckDetailCode) {
		this.arrayCheckDetailCode = arrayCheckDetailCode;
	}
	public int[] getArrayScore() {
		return arrayScore;
	}
	public void setArrayScore(int[] arrayScore) {
		this.arrayScore = arrayScore;
	}
	public List<ScoreVO> getScoreVOList() {
		return scoreVOList;
	}
	public void setScoreVOList(List<ScoreVO> scoreVOList) {
		this.scoreVOList = scoreVOList;
	}
	
	@Override
	public String toString() {
		return "ScoreVO [checkYear=" + checkYear + ", checkSeason=" + checkSeason + ", checkGroupCode=" + checkGroupCode
				+ ", checkGroupContent=" + checkGroupContent + ", checkDetailContent=" + checkDetailContent
				+ ", checkScore=" + checkScore + ", checkDetailCode=" + checkDetailCode + ", centerCode=" + centerCode
				+ ", userCode=" + userCode + ", arrayCheckGroupCode=" + Arrays.toString(arrayCheckGroupCode)
				+ ", arrayCheckDetailCode=" + Arrays.toString(arrayCheckDetailCode) + ", arrayScore="
				+ Arrays.toString(arrayScore) + "]";
	}
	
	
/*@Modification InDation
 * 
 * 수정일 		 	수정자	수정내용
 * ======		=====	=========
 * 2023.01.02	정윤선	최초 생성
 * 2023.01.04	정윤선	private String[] arrayCheckGroupCode;
						private int[] arrayCheckDetailCode;
						private int[] arrayScore;
 * 
 * */
}
