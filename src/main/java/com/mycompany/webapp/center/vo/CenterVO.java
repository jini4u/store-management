package com.mycompany.webapp.center.vo;

import java.util.List;


import com.mycompany.webapp.common.vo.FileInfoVO;

public class CenterVO {
	private int centerCode;
	private String centerName;
	private String centerTel;
	private String centerAddress;
	private String centerGuide;
	
	private String centerOpeningDate;
	private String centerClosingDate;
	private String centerCondition;
	
	//담당자 코드
	private int userCode;
	//담당자 이름
	private String userName;
	private List<FileInfoVO> file;
	
	
	public List<FileInfoVO> getFile() {
		return file;
	}
	public void setFile(List<FileInfoVO> file) {
		this.file = file;
	}
	public int getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(int centerCode) {
		this.centerCode = centerCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterTel() {
		return centerTel;
	}
	public void setCenterTel(String centerTel) {
		this.centerTel = centerTel;
	}
	public String getCenterAddress() {
		return centerAddress;
	}
	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}
	public String getCenterGuide() {
		return centerGuide;
	}
	public void setCenterGuide(String centerGuide) {
		this.centerGuide = centerGuide;
	}
	public String getCenterOpeningDate() {
		return centerOpeningDate;
	}
	public void setCenterOpeningDate(String centerOpeningDate) {
		this.centerOpeningDate = centerOpeningDate;
	}
	public String getCenterClosingDate() {
		return centerClosingDate;
	}
	public void setCenterClosingDate(String centerClosingDate) {
		this.centerClosingDate = centerClosingDate;
	}
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCenterCondition() {
		return centerCondition;
	}
	public void setCenterCondition(String centerCondition) throws Exception {
		if(centerCondition.equals("notyet")) {
			centerCondition = "오픈예정";
		} else if(centerCondition.equals("closed")) {
			centerCondition = "폐점";
		} else if(Integer.parseInt(centerCondition) <= 0) {
			centerCondition = "영업중";
		} else {
			centerCondition = "오픈예정";
		}
		this.centerCondition = centerCondition;
	}
	@Override
	public String toString() {
		return "CenterVO [centerCode=" + centerCode + ", centerName=" + centerName + ", centerTel=" + centerTel
				+ ", centerAddress=" + centerAddress + ", centerGuide=" + centerGuide + ", centerOpeningDate="
				+ centerOpeningDate + ", centerClosingDate=" + centerClosingDate + ", centerCondition="
				+ centerCondition + ", userCode=" + userCode + ", userName=" + userName + ", file=" + file + "]";
	}	
}
