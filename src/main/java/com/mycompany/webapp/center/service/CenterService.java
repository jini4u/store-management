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
	
	public int getLastCenterCode() {
		return centerRepository.getLastCenterCode();
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
	public int centerUpdate(CenterVO centerVO) {
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
	public List<String> getCenterImageNames(int centerCode) {
		return centerRepository.getCenterImageNames(centerCode);

	}

	@Override
	public List<CenterVO> findCenter(Pager pager, CenterVO centerVO) {
		return centerRepository.findCenter(pager ,centerVO);
	}


}
