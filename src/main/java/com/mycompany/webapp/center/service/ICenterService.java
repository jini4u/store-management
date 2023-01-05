package com.mycompany.webapp.center.service;

import java.util.List;

import com.mycompany.webapp.center.vo.CenterVO;

public interface ICenterService {
	
	//센터코드+1
	public int insertCenterCode();
	//센터등록
	public int insertCenter(CenterVO centerVO);
	//센터조회
	public List<CenterVO> centerList();
	
	//유진
	//맵핑가능 센터 리스트 조회
	List<CenterVO> getAvailableCenterList();
}
