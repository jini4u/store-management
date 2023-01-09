package com.mycompany.webapp.manager.service;

import java.util.List;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerService {
	/* author 怨좎�蹂�
	 * �떞�떦�옄 �벑濡� */
	int insertManager(ManagerVO mgr);
	
	/* author 怨좎�蹂�
	 * �떞�떦�옄 紐⑸줉 議고쉶 */
	List<ManagerVO> selectManagerList(Pager pager);
	
	/* author 怨좎�蹂�
	 * �떞�떦�옄 �젙蹂� �긽�꽭議고쉶 */
	ManagerVO selectManagerDetail(int userCode);
	
	/* author 怨좎�蹂�
	 * �떞�떦�옄 �젙蹂� �닔�젙*/
	int managerUpdate(ManagerVO mgr);
	
	//�쑀吏�
	List<CenterVO> getCenterByManager(int userCode);
	int cancelMapping(int userCode, int centerCode);
	int mapping(int userCode, int centerCode);
	
	int countAllMgr();
	
	/* author 은별
		검색 
	 * */
	//키워드별 담당자 수
	int selectManagerNumByKeyword(String keyword);
		
	//키워드별 담당자 검색
	List<ManagerVO> searchManagerListByKeyword(String keyword, int page);
		
	 
}
