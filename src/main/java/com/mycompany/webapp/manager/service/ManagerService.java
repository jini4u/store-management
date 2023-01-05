package com.mycompany.webapp.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
