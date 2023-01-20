package com.mycompany.webapp.common.dao;

import com.mycompany.webapp.manager.vo.ManagerVO;

public interface ILoginRepository {
   //author 고은별
   //회원 조회
   ManagerVO selectMember(int userCode );
   //회원 정보 수정
   void updateMember(ManagerVO member);
   //비밀번호 조회
   String getPassword(String userCode);
}