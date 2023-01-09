package com.mycompany.webapp.center.service;

import java.util.List;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;

public interface ICenterService {
	
	//센터코드+1
	public int insertCenterCode();
	//센터등록
	public int insertCenter(CenterVO centerVO);
	//센터조회
	public List<CenterVO> centerList(Pager pager);

	//유진
	//전체 센터 수 조회
	int countAllCenters();
	
	//센터 사진 등록
	int addCenterImage(FileInfoVO file);
	
	//센터 사진 조회
	List<FileInfoVO> getCenterImageNames(int centerCode);
}
