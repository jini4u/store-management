package com.mycompany.webapp.score.vo;

public class ScoreVO {
	
	
	private int centerCode;
	private int userCode;
	private int checkYear;
	private int checkSeason;
	private String checkGroupCode;
	private int checkDetailCode;
	private int checkScore;
	
	
	@Override
	public String toString() {
		return "ScoreVO [centerCode=" + centerCode + ", userCode=" + userCode + ", checkYear=" + checkYear
				+ ", checkSeason=" + checkSeason + ", checkGroupCode=" + checkGroupCode + ", checkDetailCode="
				+ checkDetailCode + ", checkScore=" + checkScore + "]";
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
	public int getCheckDetailCode() {
		return checkDetailCode;
	}
	public void setCheckDetailCode(int checkDetailCode) {
		this.checkDetailCode = checkDetailCode;
	}
	public int getCheckScore() {
		return checkScore;
	}
	public void setCheckScore(int checkScore) {
		this.checkScore = checkScore;
	}
	
	
	
	
	
	
	
	
	
	
	
	
/*@Modification InDation
 * 
 * 수정일 		 	수정자	수정내용
 * ======		=====	=========
 * 2023.01.02	정윤선	최초 생성
 * */
}
