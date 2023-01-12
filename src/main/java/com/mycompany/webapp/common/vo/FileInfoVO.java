package com.mycompany.webapp.common.vo;

import java.util.Date;

public class FileInfoVO {
	private int fileNo;
	private String fileSavedName;
	private String originalName;
	private String fileType;
	private String fileDetail;
	private String filePath;
	private String filePostDate;
	private String fileModifyDate;
	
	private int centerCode;
	private int uploadUserCode;
	private String uploadUserName;
	
	//엑셀 정보 업로드를 위한 필드
	private int insertHistory;
	private int updateHistory;
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileSavedName() {
		return fileSavedName;
	}
	public void setFileSavedName(String fileSavedName) {
		this.fileSavedName = fileSavedName;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileDetail() {
		return fileDetail;
	}
	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilePostDate() {
		return filePostDate;
	}
	public void setFilePostDate(String filePostDate) {
		this.filePostDate = filePostDate;
	}
	public String getFileModifyDate() {
		return fileModifyDate;
	}
	public void setFileModifyDate(String fileModifyDate) {
		this.fileModifyDate = fileModifyDate;
	}
	public int getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(int centerCode) {
		this.centerCode = centerCode;
	}
	public int getUploadUserCode() {
		return uploadUserCode;
	}
	public void setUploadUserCode(int uploadUserCode) {
		this.uploadUserCode = uploadUserCode;
	}
	public String getUploadUserName() {
		return uploadUserName;
	}
	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}
	
	@Override
	public String toString() {
		return "FileInfoVO [fileNo=" + fileNo + ", fileSavedName=" + fileSavedName + ", originalName=" + originalName
				+ ", fileType=" + fileType + ", fileDetail=" + fileDetail + ", filePath=" + filePath + ", filePostDate="
				+ filePostDate + ", fileModifyDate=" + fileModifyDate + ", centerCode=" + centerCode
				+ ", uploadUserCode=" + uploadUserCode + ", uploadUserName=" + uploadUserName + "]";
	}
}
