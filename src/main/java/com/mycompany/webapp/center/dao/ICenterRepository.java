package com.mycompany.webapp.center.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;

public interface ICenterRepository {

	//센터 등록
	public int insertCenter(CenterVO centerVO);
	
	//센터조회
	public List<CenterVO> centerList(Pager pager);
	
	//센터 수정
	public int centerUpdate(CenterVO centerVO);
	
	//센터운영상태
	public String centerCondition(CenterVO centerVO);
	
	//센터 검색
	public List<CenterVO> findCenter(@Param("filterPager")Pager filterPager, @Param("keyword")String keyword);
	
	//유진
	int countAllCenters();
	int addCenterImage(FileInfoVO file);
	
	List<FileInfoVO> getCenterImageNames(int centerCode);
	int updateImage(FileInfoVO file);
	int deleteImage(int fileNo);
	
	//센터 검색된 수 받아오는 것
	int filterCountAllCenters(String keyword);

	//센터코드 총 갯수
	int centerDataExist(CenterVO center);
	
	List<Map<String, Object>> getCenterUploadHistory();
}
