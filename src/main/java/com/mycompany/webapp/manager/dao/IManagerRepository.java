package com.mycompany.webapp.manager.dao;

import java.util.List;

import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerRepository {
	//autor 고은별
	
	//담당자 등록 
	int insertManager(ManagerVO mgr);

	//담당자 코드 최대 번호 
	int selectMaxManagerNo();
	
	//파일코드 최대 번호 
	int selectMaxManagerFileNo();
	
	//파일 일괄업로드
	int insertManagerFileData(FileInfoVO fileNo);
	
	//담당자 목록 조회
	List<ManagerVO> selectManagerList();
	
	//담당자 상세 조회
	ManagerVO selectManagerDetail(int userCode);
	
	//담당자 정보 수정
	int updateManagerInfo(ManagerVO mgr);
	
	//담당자 전체 인원 수
	int selectManagerTotalNum();
	
	//키워드 별 담당자 인원 수
	int selectManagerNumByKeyword(String keyword);
	
	//키워드 별 담당자 검색
	List<ManagerVO> searchManagerListByKeyword(String keyword);
	
}
