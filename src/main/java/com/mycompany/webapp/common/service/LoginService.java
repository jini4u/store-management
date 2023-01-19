package com.mycompany.webapp.common.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.webapp.common.dao.ILoginRepository;
import com.mycompany.webapp.manager.vo.ManagerVO;

public class LoginService implements ILoginService {
	
	@Autowired
	ILoginRepository loginRepository;
	
	/*  @author 고은별
	 * 	회원 조회
	 */
	@Override
	public ManagerVO selectMember(int userCode) {
		return loginRepository.selectMember(userCode);
	}
	/*  @author 고은별
	 * 	회원 수정
	 */
	@Override
	public void updateMember(ManagerVO member) {
		loginRepository.updateMember(member);
		
	}
	/*  @author 고은별
	 * 	회원 비밀번호 조회
	 */
	@Override
	public String getPassword(String userCode) {
		return loginRepository.getPassword(userCode);
	}

}
