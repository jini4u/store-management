package com.mycompany.webapp.manager.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.dao.IFileRepository;
import com.mycompany.webapp.common.poi.ManagerPOI;
import com.mycompany.webapp.common.poi.POIClass;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.controller.ManagerController;
import com.mycompany.webapp.manager.dao.IManagerRepository;
import com.mycompany.webapp.manager.vo.ManagerVO;

@Service
public class ManagerService implements IManagerService {
	static final Logger logger=LoggerFactory.getLogger(ManagerService.class);
	
	@Autowired
	IManagerRepository managerRepository;

	@Autowired
	IFileRepository fileRepository;

	/* author 은별 
	 * 담당자 등록  */
	@Override
	public int insertManager(ManagerVO mgr) {
		return managerRepository.insertManager(mgr);
	}

	/* author 은별 
	 * 담당자 목록 조회  */
	@Override
	public List<ManagerVO> selectManagerList(Pager pager) {
		List<ManagerVO> managerList = managerRepository.selectManagerList(pager);
		return managerList;
	}



	/* author 은별
	 * 담당자 수정 */
	@Override
	public int managerUpdate(ManagerVO mgr) {
		return managerRepository.updateManagerInfo(mgr);
	}

	/**
	 * 담당자의 userCode로 담당하는 센터들의 센터코드,센터명,주소 조회
	 * @author 임유진
	 * @param {int} 담당자 userCode
	 * @return {List<CenterVO>} 담당 센터 리스트 
	 * */
	@Override
	public List<CenterVO> getCenterByManager(int userCode) {
		return managerRepository.getCenterByManager(userCode);
	}

	/**
	 * 담당자, 센터 맵핑 해제
	 * @author 임유진
	 * @param {int} 담당자 userCode
	 * @param {int}선택된 centerCode
	 * @return {int} 삭제된 행 수
	 * */
	@Override
	public int cancelMapping(int userCode, int centerCode) {
		return managerRepository.cancelMapping(userCode, centerCode);
	}

	/**
	 * 담당자, 센터 맵핑
	 * @author 임유진
	 * @param 담당자 userCode
	 * @param 선택된 centerCode
	 * @return 삽입된 행 수
	 * */
	@Override
	public int mapping(int userCode, int centerCode) {
		return managerRepository.mapping(userCode, centerCode);
	}

	/*  @author 고은별
	 * 	담당자 전체 수 카운트
	 */
	@Override
	public int countAllMgr() {
		return managerRepository.countAllMgr();
	}

	
	/*  @author 고은별
	 * 	담당자 검색
	 */
	@Override
	public List<ManagerVO> managerSearch(Pager pager, String keyword, String keywordType) {
		return managerRepository.managerSearch(pager, keyword, keywordType);
	}
	
	/*  @author 고은별
	 * 	키워드별 담당자 수 카운트
	 */
	@Override
	public int managerCountByKeyword(String keyword, String keywordType) {
		return managerRepository.managerCountByKeyword(keyword,keywordType);
	}
	
	/*  @author 고은별
	 * 	담당자 일괄 업로드
	 */
	@Value("${file.path}")
	String filePath;
	@Override
	public Map<String, Integer> mgrUploadFileInfo(MultipartFile file, int startRow, int userCode) {
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("fileNo", 0);
		resultMap.put("userCode", userCode);
		resultMap.put("insert", 0);
		resultMap.put("update", 0);
		
		
		POIClass poi = new ManagerPOI();
		//한 행이 object, 엑셀을 읽어와서 리스트에 담아주
		List<Object> VOList = poi.readWorkBook(file, startRow);
		for(Object vo:VOList) {
			//managerVO로 형변환, 엑셀값이 vo에 담김
			ManagerVO mgr = (ManagerVO)vo;
			int mgrExisData = managerRepository.mgrIsDataExist(mgr);
			if(mgr.getUserCode() == 30000) {
				managerRepository.insertManager(mgr);
				//replace(K key, V oldValue, V newValue): 저장된 key의 value가 oldValue와 동일할 때만 newValue로 변경해준다
				//replace로 바꿔서 히스토리에 저장
				resultMap.replace("insert", resultMap.get("insert"), resultMap.get("insert")+1);
			} else {
				managerRepository.updateManagerInfo(mgr);
				resultMap.replace("update", resultMap.get("update"), resultMap.get("update")+1);
			}
		}
		Date date = new Date();
		
		String filePathName = filePath+"managerExcel_"+date.getTime()+"_"+file.getOriginalFilename();
		
		FileInfoVO fileVO = new FileInfoVO();
		fileVO.setFileSavedName("managerExcel_"+date.getTime()+"_"+file.getOriginalFilename());
		fileVO.setOriginalName(file.getOriginalFilename());
		fileVO.setFileType(file.getContentType());
		fileVO.setFilePath(filePathName);
		//로그인 구현 후 수정
		fileVO.setUploadUserCode(userCode);
		
		fileRepository.insertFile(fileVO);
		resultMap.replace("fileNo", 0, fileVO.getFileNo());
		resultMap.replace("userCode", 0, fileVO.getUploadUserCode());
		
		//업로드 히스토리 등록
		fileRepository.insertFileUploadHistory(resultMap);
		
		try {               
			//받아온 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo() 메서드를 이용해서 업로드 처리 해야함
			file.transferTo(new File(filePathName));
		} catch (Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/*  @author 고은별
	 * 	담당자 엑셀 업로드 히스토리
	 */
	@Override
	public List<Map<String, String>> mgrUploadFileHistory(Pager pager) {
		List<Map<String, Object>> mgrHistoryList = managerRepository.mgrUploadFileHistory(pager);
		List<Map<String, String>> mgrResultList = new ArrayList<Map<String, String>>();
		for(Map<String,Object> history:mgrHistoryList) {
				String postDate = (String)history.get("postDate");
				String userName = (String)history.get("userName");
				String originalName = (String)history.get("originalName");
				String fileSavedName = (String)history.get("fileSavedName");
				String insert = String.valueOf(history.get("insert"));
				String update = String.valueOf(history.get("update"));
				
				Map<String, String> mgrResultMap = new HashMap<String, String>();
				mgrResultMap.put("postDate", postDate);
				mgrResultMap.put("userName", userName);
				mgrResultMap.put("originalName", originalName);
				mgrResultMap.put("fileSavedName", fileSavedName);
				mgrResultMap.put("result", "입력: "+insert+"건, 수정: "+update+"건");
				
				mgrResultList.add(mgrResultMap);
			}
			return mgrResultList;
	}
	
	/* author 은별
	 * 엑셀 업로드한 총수*/
	public int mgrUploadFileTotalCount() {
		return managerRepository.mgrUploadFileTotalCount();
	}


}

