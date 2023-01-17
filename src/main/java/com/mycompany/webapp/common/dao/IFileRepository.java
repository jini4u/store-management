package com.mycompany.webapp.common.dao;

import java.util.Map;

import com.mycompany.webapp.common.vo.FileInfoVO;

/**
 * 파일 일괄업로드 관리를 위한 인터페이스 
 * @author 임유진 
 * */
public interface IFileRepository {
	//파일 업로드시 history 테이블으로 이력 관리 
	int insertFileUploadHistory(Map<String, Integer> historyMap);
	//파일 업로드시 fileinfo 테이블에 파일 정보 저장 
	int insertFile(FileInfoVO file);
	//기존 파일명으로 테이블에 저장된 정보 조회 
	FileInfoVO getFileInfoByOriginalName (String originalFileName);
}
