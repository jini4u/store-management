package com.mycompany.webapp.center.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;

public interface ICenterService {
	
	//센터등록
	public int insertCenter(CenterVO centerVO);
	//센터조회
	public List<CenterVO> centerList(Pager pager);

	//유진
	//전체 센터 수 조회
	int countAllCenters();
	//센터운영여부
	public String centerCondition(CenterVO centerVO);
	
	//센터수정
	public int centerUpdate(CenterVO centerVO);

	//센터 사진 등록
	int addCenterImage(String fileDetail, int centerCode, int uploadUserCode, List<MultipartFile> files);

	//센터 검색
	public List<CenterVO> findCenter(Pager pager, CenterVO centerVO);

	//센터 사진 조회
	List<FileInfoVO> getCenterImageNames(int centerCode);

	//센터 사진 정보 수정(originalName, fileDetail)
	int updateImage(FileInfoVO file, int centerCode, String oldOriginalName);
	
	int deleteImage(List<Integer> fileNoList, int centerCode);
	
	//센터 검색된 수
	int filterCountAllCenters(String centerName);
}
