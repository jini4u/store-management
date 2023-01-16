package com.mycompany.webapp.manager.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.mycompany.webapp.center.vo.CenterVO;

public class ManagerVO {
	//담당자
	private int userCode;
	private String userPassword;
	private String userName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date userBirth;
	
	private String userTel;
	private String userEmail;
	private int userTeamCode;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date userHireDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date userResignDate;
	
	private List<CenterVO> centerList;
	
	private String keyword;

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserTeamCode() {
		return userTeamCode;
	}

	public void setUserTeamCode(int userTeamCode) {
		this.userTeamCode = userTeamCode;
	}

	public Date getUserHireDate() {
		return userHireDate;
	}

	public void setUserHireDate(Date userHireDate) {
		this.userHireDate = userHireDate;
	}

	public Date getUserResignDate() {
		return userResignDate;
	}

	public void setUserResignDate(Date userResignDate) {
		this.userResignDate = userResignDate;
	}

	public List<CenterVO> getCenterList() {
		return centerList;
	}

	public void setCenterList(List<CenterVO> centerList) {
		this.centerList = centerList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "ManagerVO [userCode=" + userCode + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", userBirth=" + userBirth + ", userTel=" + userTel + ", userEmail=" + userEmail + ", userTeamCode="
				+ userTeamCode + ", userHireDate=" + userHireDate + ", userResignDate=" + userResignDate
				+ ", centerList=" + centerList + ", keyword=" + keyword + "]";
	}

	
}
