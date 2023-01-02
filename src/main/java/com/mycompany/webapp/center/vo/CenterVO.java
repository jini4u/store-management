package com.mycompany.webapp.center.vo;

import java.util.Date;
import java.util.List;

import com.mycompany.webapp.common.vo.FileInfoVO;

public class CenterVO {
	
	private int cneterCode;
	private String centerName;
	private String tel;
	private String address;
	private String guide;
	private Date centerOpeningDate;
	private Date centerClosingDate;
	private String centerNote;
	private List<FileInfoVO> file;
	public int getCneterCode() {
		return cneterCode;
	}
	public void setCneterCode(int cneterCode) {
		this.cneterCode = cneterCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	public Date getCenterOpeningDate() {
		return centerOpeningDate;
	}
	public void setCenterOpeningDate(Date centerOpeningDate) {
		this.centerOpeningDate = centerOpeningDate;
	}
	public Date getCenterClosingDate() {
		return centerClosingDate;
	}
	public void setCenterClosingDate(Date centerClosingDate) {
		this.centerClosingDate = centerClosingDate;
	}
	public String getCenterNote() {
		return centerNote;
	}
	public void setCenterNote(String centerNote) {
		this.centerNote = centerNote;
	}
	public List<FileInfoVO> getFile() {
		return file;
	}
	public void setFile(List<FileInfoVO> file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "CenterVO [cneterCode=" + cneterCode + ", centerName=" + centerName + ", tel=" + tel + ", address="
				+ address + ", guide=" + guide + ", centerOpeningDate=" + centerOpeningDate + ", centerClosingDate="
				+ centerClosingDate + ", centerNote=" + centerNote + ", file=" + file + "]";
	}
	
}
