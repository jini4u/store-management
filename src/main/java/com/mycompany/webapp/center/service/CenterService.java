package com.mycompany.webapp.center.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.center.dao.ICenterRepository;
import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;

@Service
public class CenterService implements ICenterService{

	@Autowired
	ICenterRepository centerRepository;
	
	public int insertCenterCode() {
		return centerRepository.insertCenterCode();
	}

	@Override
	public int insertCenter(CenterVO centerVO) {
		return centerRepository.insertCenter(centerVO);
	}

	@Override
	public List<CenterVO> centerList(Pager pager) {
		return centerRepository.centerList(pager);
	}
		
	//유진
	@Override
	public int countAllCenters() {
		return centerRepository.countAllCenters();
	}

	@Override
	public List<CenterVO> centerList() {
		// TODO Auto-generated method stub
		return centerRepository.centerList();
	}

	@Override
	public List<CenterVO> centerUpdate(CenterVO centerVO) {
		return centerRepository.centerUpdate(centerVO);
	}

	//센터운영상태
	@Override
	public String centerCondition(CenterVO centerVO) {
		return centerRepository.centerCondition(centerVO);
	}
	
	@Override
	public int addCenterImage(FileInfoVO file) {
		return centerRepository.addCenterImage(file);
	}
	
	@Override
	public List<FileInfoVO> getCenterImageNames(int centerCode) {
		return centerRepository.getCenterImageNames(centerCode);
	}
	
	@Override
	public int updateImage(FileInfoVO file) {
		return centerRepository.updateImage(file);
	}
	
	@Override
	public int deleteImage(List<Integer> fileNoList) {
		int result = 0;
		for(int fileNo:fileNoList) {
			result += centerRepository.deleteImage(fileNo);
		}
		return result;
	}
}
