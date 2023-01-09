package com.mycompany.webapp.center.service;

import java.util.List;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;

public interface ICenterService {
	
	//센터코드+1
	public int insertCenterCode();
	//센터등록
	public int insertCenter(CenterVO centerVO);
	//센터조회
	public List<CenterVO> centerList(Pager pager);
	
	//테스트용
	public List<CenterVO>centerList();

	int countAllCenters();
	
	//센터운영여부
	public String centerCondition(CenterVO centerVO);
	
	//센터수정
	public List<CenterVO> centerUpdate(CenterVO centerVO);
}
