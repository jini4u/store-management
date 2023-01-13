package com.mycompany.webapp.common.dao;

import java.util.Map;

import com.mycompany.webapp.common.vo.FileInfoVO;

public interface IFileRepository {
	//유진
	int insertFileUploadHistory(Map<String, Integer> historyMap);
	int insertFile(FileInfoVO file);
	FileInfoVO getFileInfoByOriginalName (String originalFileName);
}
