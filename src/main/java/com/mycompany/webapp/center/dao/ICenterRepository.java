package com.mycompany.webapp.center.dao;

import java.util.List;

import com.mycompany.webapp.center.vo.CenterVO;

public interface ICenterRepository {
	//소정
	//센터코드 +1
	public int insertCenterCode();
	//센터 등록
	public int insertCenter(CenterVO centerVO);
	//센터조회
	public List<CenterVO> centerList();
	

	//유진
	//맵핑 가능 센터 목록 조회
	List<CenterVO> getAvailableCenterList();
}
