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
	
	/* autor 고은별
	 * 담당자 등록 */
	@Override
	public int insertManager(ManagerVO userCode) {
		return managerRepository.insertManager(userCode);
	}
	
	/* autor 고은별
	 * 담당자 목록 조회 */
	@Override
	public List<ManagerVO> selectManagerList() {
		return managerRepository.selectManagerList();
	}
	
	/* autor 고은별
	 * 담당자 정보 상세조회 */
	@Override
	public ManagerVO selectManagerDetail(int userCode) {
		return managerRepository.selectManagerDetail(userCode);
	}

}
