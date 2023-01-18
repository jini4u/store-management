package com.mycompany.webapp.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerRepository {
	//author 은별
	
	//담당자 등록
	int insertManager(ManagerVO mgr);

	int selectMaxManagerNo();
	
	int selectMaxManagerFileNo();
	
	int insertManagerFileData(FileInfoVO fileNo);
	
	List<ManagerVO> selectManagerList(Pager pager);
	
	ManagerVO selectManagerDetail(int userCode);
	
	int updateManagerInfo(ManagerVO mgr);
	
	int countAllMgr();
	
	//키워드별 담당자 검색
	List<ManagerVO> managerSearch(@Param("pager")Pager pager, @Param("keyword") String keyword);
	// 키워드별 담당자 수  
	int managerCountByKeyword(String keyword);
	
	//담당하는 센터 리스트 조회 
	List<CenterVO> getCenterByManager(int userCode);
	//맵핑 해제 
	int cancelMapping(@Param("userCode") int userCode, @Param("centerCode") int centerCode);
	//맵핑 설정 
	int mapping(@Param("userCode") int userCode, @Param("centerCode") int centerCode);
}
