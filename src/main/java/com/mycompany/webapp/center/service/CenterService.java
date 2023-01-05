package com.mycompany.webapp.center.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.center.dao.ICenterRepository;
import com.mycompany.webapp.center.vo.CenterVO;

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
	public List<CenterVO> centerList() {
		return centerRepository.centerList();
	}
	
	/**
	 * @author 임유진
	 * @return List<맵핑 가능 센터>
	 * */
	@Override
	public List<CenterVO> getAvailableCenterList() {
		return centerRepository.getAvailableCenterList();
	}


	
}
