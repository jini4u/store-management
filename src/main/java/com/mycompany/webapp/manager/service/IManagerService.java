package com.mycompany.webapp.manager.service;

import java.util.List;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerService {
	/* author 은별
	 * 담당자 등록 */
	int insertManager(ManagerVO mgr);
	
	/* author 은별
	 * 담당자 등록 */
	List<ManagerVO> selectManagerList(Pager pager);
	
	/* author 은별
	 * 담당자 등록 */
	ManagerVO selectManagerDetail(int userCode);
	
	/* author 은별
	 * 담당자 등록 */
	int managerUpdate(ManagerVO mgr);
	
	/* author 유진
	 * 담당자 매핑 */
	List<CenterVO> getCenterByManager(int userCode);
	int cancelMapping(int userCode, int centerCode);
	int mapping(int userCode, int centerCode);
	
	int countAllMgr();
	
	/* author 은별
	 * 담당자 검색 */
	//키워드별 담당자 검색
	List<ManagerVO> managerSearch(Pager pager, String keyword);
	// 키워드별 담당자 수  
		int managerCountByKeyword(String keyword);

		
	 
}
