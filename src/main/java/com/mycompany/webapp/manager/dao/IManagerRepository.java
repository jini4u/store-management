package com.mycompany.webapp.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerRepository {
	//author 怨좎�蹂�
	
	//�떞�떦�옄 �벑濡� 
	int insertManager(ManagerVO mgr);

	//�떞�떦�옄 肄붾뱶 理쒕� 踰덊샇 
	int selectMaxManagerNo();
	
	//�뙆�씪肄붾뱶 理쒕� 踰덊샇 
	int selectMaxManagerFileNo();
	
	//�뙆�씪 �씪愿꾩뾽濡쒕뱶
	int insertManagerFileData(FileInfoVO fileNo);
	
	//�떞�떦�옄 紐⑸줉 議고쉶
	List<ManagerVO> selectManagerList(Pager pager);
	
	//�떞�떦�옄 �긽�꽭 議고쉶
	ManagerVO selectManagerDetail(int userCode);
	
	//�떞�떦�옄 �젙蹂� �닔�젙
	int updateManagerInfo(ManagerVO mgr);
	
	//�떞�떦�옄 �쟾泥� �씤�썝 �닔
	int countAllMgr();
	
	//키워드별 담당자 검색
	List<ManagerVO> managerSearch(String keyword);
	
	//�쑀吏�
	//userCode濡� �떞�떦 �꽱�꽣 議고쉶
	List<CenterVO> getCenterByManager(int userCode);
	//userCode,centerCode濡� 留듯븨�빐�젣
	int cancelMapping(@Param("userCode") int userCode, @Param("centerCode") int centerCode);
	//留듯븨
	int mapping(@Param("userCode") int userCode, @Param("centerCode") int centerCode);

}
