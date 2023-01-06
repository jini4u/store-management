package com.mycompany.webapp.center.dao;

import java.util.List;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;

public interface ICenterRepository {
	//소정
	//센터코드 +1
	public int insertCenterCode();
	//센터 등록
	public int insertCenter(CenterVO centerVO);
	//센터조회
	public List<CenterVO> centerList(Pager pager);
	

	//유진
	int countAllCenters();
}
