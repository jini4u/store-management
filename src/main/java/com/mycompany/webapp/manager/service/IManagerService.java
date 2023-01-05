package com.mycompany.webapp.manager.service;

import java.util.List;

import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerService {
	//은별
	//담당자 등록
	int insertManager(ManagerVO mgr);
	
	//담당자 목록 조회
	List<ManagerVO> selectManagerList(ManagerVO mgr);
	
	//담당자 정보 상세조회
	ManagerVO selectManagerDetail(int userCode);
}
