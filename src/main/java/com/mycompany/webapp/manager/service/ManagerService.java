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
	 * 담당자의 userCode로 담당하는 센터들의 센터코드,센터명,주소 조회
	 * @author 임유진
	 * @param {int} 담당자 userCode
	 * @return {List<CenterVO>} 담당 센터 리스트 
	 * */
	@Override
	public List<CenterVO> getCenterByManager(int userCode) {
		return managerRepository.getCenterByManager(userCode);
	}

	/**
	 * 담당자, 센터 맵핑 해제
	 * @author 임유진
	 * @param {int} 담당자 userCode
	 * @param {int}선택된 centerCode
	 * @return {int} 삭제된 행 수
	 * */
	@Override
	public int cancelMapping(int userCode, int centerCode) {
		return managerRepository.cancelMapping(userCode, centerCode);
	}

	/**
	 * 담당자, 센터 맵핑
	 * @author 임유진
	 * @param 담당자 userCode
	 * @param 선택된 centerCode
	 * @return 삽입된 행 수
	 * */
	@Override
	public int mapping(int userCode, int centerCode) {
		return managerRepository.mapping(userCode, centerCode);
	}

	@Override
	public int countAllMgr() {
		System.out.println("검색결과수:"+managerRepository.countAllMgr());
		return managerRepository.countAllMgr();
	}

	@Override
	public List<ManagerVO> managerSearch(Pager pager, String keyword) {
		return managerRepository.managerSearch(pager, keyword);
	}

	@Override
	public int managerCountByKeyword(String keyword) {
		return managerRepository.managerCountByKeyword("%"+keyword+"%");
	}

}