package com.mycompany.webapp.manager.vo;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.mycompany.webapp.center.vo.CenterVO;

import lombok.NonNull;

public class ManagerVO {
	//담당자
	private int userCode;
	private String userPassword;
	
	@Pattern(regexp="[가-힣]{1,}", message="이름 입력")
	private String userName;
	
	@NotNull
	private String userBirth;
	
	@NotNull
	private String userTel;
	private String userEmail;
	
	@NotNull
	private int userTeamCode;
	
	@NotNull
	private String userHireDate;
	private String userResignDate;
	
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

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
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

	public String getUserHireDate() {
		return userHireDate;
	}

	public void setUserHireDate(String userHireDate) {
		this.userHireDate = userHireDate;
	}

	public String getUserResignDate() {
		return userResignDate;
	}

	public void setUserResignDate(String userResignDate) {
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
