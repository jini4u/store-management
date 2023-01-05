package com.mycompany.webapp.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.manager.dao.IManagerRepository;
import com.mycompany.webapp.manager.vo.ManagerVO;

@Service
public class ManagerService implements IManagerService {
	
	@Autowired
	IManagerRepository managerRepository;
	
	/* author 고은별
	 * 담당자 등록 */
	@Override
	public int insertManager(ManagerVO mgr) {
		mgr.setUserCode(managerRepository.selectMaxManagerNo()+1);
		return managerRepository.insertManager(mgr);
	}
	
	/* author 고은별
	 * 담당자 목록 조회 */
	@Override
	public List<ManagerVO> selectManagerList(ManagerVO mgr) {
		return managerRepository.selectManagerList();
	}
	
	/* author 고은별
	 * 담당자 정보 상세조회 */
	@Override
	public ManagerVO selectManagerDetail(int userCode) {
		return managerRepository.selectManagerDetail(userCode);
	}

	
	/**
	 * @author 임유진
	 * 담당자의 userCode로 담당하는 센터들의 센터코드,센터명,주소 조회
	 * @param 담당자 userCode
	 * @return List<담당CenterVO>
	 * */
	@Override
	public List<CenterVO> getCenterByManager(int userCode) {
		return managerRepository.getCenterByManager(userCode);
	}
	
	/**
	 * @author 임유진
	 * 담당자, 센터 맵핑 해제
	 * @param 담당자 userCode
	 * @param 선택된 centerCode
	 * @return 삭제된 행 수
	 * */
	@Override
	public int cancelMapping(int userCode, int centerCode) {
		return managerRepository.cancelMapping(userCode, centerCode);
	}
}
