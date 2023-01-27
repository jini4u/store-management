package com.mycompany.webapp.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerRepository {
	//author 고은별
	//담당자 등록
	int insertManager(ManagerVO mgr);


	//담당자 최대번호 조회
	int selectMaxManagerNo();
	

	//담당자 최대 파일번호 조회
	int selectMaxManagerFileNo();

	//담당자 파일 등록
	int insertManagerFileData(FileInfoVO fileNo);
	

	//담당자 목록 조회
	List<ManagerVO> selectManagerList(Pager pager);
	

	//담당자 수정
	int updateManagerInfo(ManagerVO mgr);
	

	//담당자 총 인원 수 
	int countAllMgr();
	
	//같은 데이터가 있는지 조회
	int mgrIsDataExist(ManagerVO mgr);
	//담당자 엑셀 업로드 히스토리 
	List<Map<String, Object>> mgrUploadFileHistory();
	
	//키워드별 담당자 검색
	List<ManagerVO> managerSearch(@Param("pager")Pager pager, @Param("keyword") String keyword,@Param("keywordType") String keywordType);
	// 키워드별 담당자 수  
	int managerCountByKeyword(@Param("keyword") String keyword, @Param("keywordType") String keywordType);
	

	//임유진
	//userCode濡� �떞�떦 �꽱�꽣 議고쉶
	//담당하는 센터 리스트 조회 
	List<CenterVO> getCenterByManager(int userCode);
	//맵핑 해제 
	int cancelMapping(@Param("userCode") int userCode, @Param("centerCode") int centerCode);
	//맵핑 설정 
	int mapping(@Param("userCode") int userCode, @Param("centerCode") int centerCode);
}
