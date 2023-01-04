package com.mycompany.webapp.center.vo;

import java.sql.Date;
import java.util.List;


import com.mycompany.webapp.common.vo.FileInfoVO;

public class CenterVO {
	private int centerCode;
	private String centerName;
	private String centerTel;
	private String centerAddress;
	private String centerGuide;
	private Date centerOpeningDate;
	private Date centerClosingDate;
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
	public Date getCenterOpeningDate() {
		return centerOpeningDate;
	}
	public void setCenterOpeningDate(Date centerClosingDate) {
		this.centerOpeningDate = centerClosingDate;
	}
	public Date getCenterClosingDate() {
		return centerClosingDate;
	}
	public void setCenterClosingDate(Date centerClosingDate) {
		this.centerClosingDate = centerClosingDate;
	}
	@Override
	public String toString() {
		return "CenterVO [centerCode=" + centerCode + ", centerName=" + centerName + ", centerTel=" + centerTel
				+ ", centerAddress=" + centerAddress + ", centerGuide=" + centerGuide + ", centerOpeningDate="
				+ centerOpeningDate + ", centerClosingDate=" + centerClosingDate + "]";
	}
	
}
