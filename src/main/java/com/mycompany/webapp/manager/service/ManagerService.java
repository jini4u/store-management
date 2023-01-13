package com.mycompany.webapp.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.dao.IManagerRepository;
import com.mycompany.webapp.manager.vo.ManagerVO;

@Service
public class ManagerService implements IManagerService {
	
	@Autowired
	IManagerRepository managerRepository;
	
	/* author 은별 
	 * 담당자 등록  */
	@Override
	public int insertManager(ManagerVO mgr) {
		return managerRepository.insertManager(mgr);
	}
	
	/* author 은별 
	 * 담당자 목록 조회  */
	@Override
	public List<ManagerVO> selectManagerList(Pager pager) {
		List<ManagerVO> managerList = managerRepository.selectManagerList(pager);
	/*	for(int i=0;i<managerList.size();i++) {
			ManagerVO manager = managerList.get(i);
			int userCode = manager.getUserCode();
			List<CenterVO> centerList = getCenterByManager(userCode);
			manager.setCenterList(centerList);
		}*/
		return managerList;
	}
	
	/* author 은별
	 * 담당자 상세 조회 */
	@Override
	public ManagerVO selectManagerDetail(int userCode) {
		return managerRepository.selectManagerDetail(userCode);
	}
	
	/* author 은별
	 * 담당자 수정 */
	@Override
	public int managerUpdate(ManagerVO mgr) {
		return managerRepository.updateManagerInfo(mgr);
	}
	

	
	/**
	 * @author �엫�쑀吏�
	 * �떞�떦�옄�쓽 userCode濡� �떞�떦�븯�뒗 �꽱�꽣�뱾�쓽 �꽱�꽣肄붾뱶,�꽱�꽣紐�,二쇱냼 議고쉶
	 * @param �떞�떦�옄 userCode
	 * @return List<�떞�떦CenterVO>
	 * */
	@Override
	public List<CenterVO> getCenterByManager(int userCode) {
		return managerRepository.getCenterByManager(userCode);
	}
	
	/**
	 * @author �엫�쑀吏�
	 * �떞�떦�옄, �꽱�꽣 留듯븨 �빐�젣
	 * @param �떞�떦�옄 userCode
	 * @param �꽑�깮�맂 centerCode
	 * @return �궘�젣�맂 �뻾 �닔
	 * */
	@Override
	public int cancelMapping(int userCode, int centerCode) {
		return managerRepository.cancelMapping(userCode, centerCode);
	}
	
	/**
	 * @author �엫�쑀吏�
	 * �떞�떦�옄, �꽱�꽣 留듯븨
	 * @param �떞�떦�옄 userCode
	 * @param �꽑�깮�맂 centerCode
	 * @return �궫�엯�맂 �뻾 �닔
	 * */
	@Override
	public int mapping(int userCode, int centerCode) {
		return managerRepository.mapping(userCode, centerCode);
	}

/*	@Override
	public List<ManagerVO> managerList(Pager pager) {
		return managerRepository.managerList(pager);
	}*/

	@Override
	public int countAllMgr() {
		return managerRepository.countAllMgr();
	}

@Override
public List<ManagerVO> managerSearch(Pager pager, ManagerVO mgr) {
	return managerRepository.managerSearch(pager, mgr);
}







	

	
}
