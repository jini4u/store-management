package com.mycompany.webapp.center.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.center.controller.CenterController;
import com.mycompany.webapp.center.dao.ICenterRepository;
import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.dao.IFileRepository;
import com.mycompany.webapp.common.poi.CenterPOI;
import com.mycompany.webapp.common.poi.POIClass;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;

@Service
public class CenterService implements ICenterService{
	private static Logger logger = LoggerFactory.getLogger(CenterService.class);
	
	@Autowired
	ICenterRepository centerRepository;

	@Autowired
	IFileRepository fileRepository;
	
	@Value("${file.path}")
	private String filePath;

	/**
	 * @author 이소정
	 * 센터 정보 등록
	 * @param centerVO
	 * @return 등록된 센터 수 (0또는 1)
	 * */
	@Override
	public int insertCenter(CenterVO centerVO) {
		return centerRepository.insertCenter(centerVO);
	}
	/**
	 * @author 이소정
	 * 센터 정보 리스트
	 * @param pager 페이징 정보
	 * @return 센터 정보 리스트
	 * */
	@Override
	public List<CenterVO> centerList(Pager pager) {
		return centerRepository.centerList(pager);
	}

	/**
	 * DB에 존재하는 센터 수 조회 
	 * @author 임유진
	 * @return {int} 전체 센터 수 
	 * */
	@Override
	public int countAllCenters() {
		return centerRepository.countAllCenters();
	}

	/**
	 * @author 이소정
	 * 센터 정보 수정
	 * @param CenterVO 센터 정보
	 * @return 수정된 센터 수 (0또는 1)
	 * */
	@Override
	public int centerUpdate(CenterVO centerVO) {
		return centerRepository.centerUpdate(centerVO);
	}


	/**
	 * 센터 이미지 등록
	 * @author 임유진
	 * @param {String} 파일 내부/외부 정보
	 * @param {int} 센터코드
	 * @param {int} 업로드하는 담당자 번호
	 * @param {List<MultipartFile>} 사진 파일 리스트
	 * @return {int} 등록된 사진 수
	 * @exception 파일 저장 오류시, return -1
	 * */
	@Override
	public int addCenterImage(String fileDetail, int centerCode, int uploadUserCode, List<MultipartFile> files) {
		int result = 0;

		for(MultipartFile file: files) {
			//파일 인스턴스 생성 
			FileInfoVO newFile = new FileInfoVO();
			//정보 채워줌 
			newFile.setFileDetail(fileDetail);
			newFile.setCenterCode(centerCode);
			newFile.setUploadUserCode(uploadUserCode);
			newFile.setOriginalName(file.getOriginalFilename());
			//centerCode_0000000+originalName_파일이름 형태로 파일시스템에 저장 
			String fileSavedName = "centerCode_"+centerCode+"+originalName_"+file.getOriginalFilename();
			newFile.setFileSavedName(fileSavedName);
			newFile.setFileType(file.getContentType());
			newFile.setFilePath(filePath+fileSavedName);
			try {
				//파일 저장
				file.transferTo(new File(filePath+fileSavedName));
			} catch (Exception e) {
				return -1;
			}
			result += centerRepository.addCenterImage(newFile);
		}

		return result;
	}

	/**
	 * 센터 이미지 조회
	 * @author 임유진
	 * @param {int} 센터 코드
	 * @return {List<FileInfoVO>} 해당 센터 사진 리스트 
	 * */
	@Override
	public List<FileInfoVO> getCenterImageNames(int centerCode) {
		return centerRepository.getCenterImageNames(centerCode);
	}

	/**
	 * 센터 이미지 정보 수정
	 * @author 임유진
	 * @param {FileInfoVO} 수정할 내용이 담긴 파일VO
	 * @param {int} 센터 코드
	 * @param {String} 원래 파일 이름
	 * @return {int} 정보 수정된 파일 수 (0 또는 1)
	 * */
	@Override
	public int updateImage(FileInfoVO file, int centerCode, String oldOriginalName) {
		//저장되어 있는 이름으로 파일 객체 생성 
		String oldSavedName = "centerCode_"+centerCode+"+originalName_"+oldOriginalName;
		File oldFile = new File(filePath+oldSavedName);
		//새로 저장될 이름으로 파일 객체 생성 
		String newSavedName = "centerCode_"+centerCode+"+originalName_"+file.getOriginalName();
		File newFile = new File(filePath+newSavedName);
		//파일VO에 있는 savedName 변경 
		file.setFileSavedName(newSavedName);

		//새로운 이름으로 변경 
		if(!oldFile.renameTo(newFile)) {
			//renameTo가 실패할 경우 
			//버퍼, 입출력 스트림 생성 
			byte[] buf = new byte[1024];
			FileInputStream is = null;
			FileOutputStream os = null;

			try {
				//입출력 스트림에 원래파일, 새파일 
				is = new FileInputStream(oldFile);
				os = new FileOutputStream(newFile);

				int read = 0;
				//버퍼 크기만큼씩 입력을 읽어서 출력으로 써주기 
				while((read=is.read(buf,0,buf.length)) != -1) {
					os.write(buf, 0, read);
				}

				is.close();
				os.close();
				//원래 파일 삭제 
				oldFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
				return 100;
			}
		}
		return centerRepository.updateImage(file);
	}

	/**
	 * 센터 이미지 삭제
	 * @author 임유진
	 * @param {List<int>} 삭제할 파일 번호 리스트
	 * @return 삭제된 파일 수
	 * */
	@Override
	public int deleteImage(List<Integer> fileNoList, int centerCode) {
		int result = 0;
		List<FileInfoVO> centerFileList = centerRepository.getCenterImageNames(centerCode);
		for(int fileNo:fileNoList) {
			for(FileInfoVO centerFile:centerFileList) {
				if(centerFile.getFileNo() == fileNo) {
					File centerImage = new File(filePath+centerFile.getFileSavedName());
					centerImage.delete();
				}
			}
			result += centerRepository.deleteImage(fileNo);
		}
		return result;
	}

	/**
	 * 검색된 센터 정보 리스트
	 * @author 이소정
	 * @param Pager 검색된 페이지 수
	 * @param String 검색한 센터명
	 * @return 검색된 센터 정보 리스트
	 * */
	@Override
	public List<CenterVO> findCenter(Pager filterPager, String keyword, String keywordType) {
		return centerRepository.findCenter(filterPager, keyword, keywordType);
	}

	/**
	 * 검색된 센터 정보 수(합칠 수 있는지 확인)
	 * @author 이소정
	 * @param String 검색한 센터명
	 * @return 검색된 센터 정보 수
	 * */
	@Override
	public int filterCountAllCenters(String keyword, String keywordType) {
		return centerRepository.filterCountAllCenters(keyword, keywordType);
	}
	@Override
	public List<Map<String, String>> getCenterUploadHistory(Pager pager) {
		List<Map<String, Object>> historyList = centerRepository.getCenterUploadHistory(pager);
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

		for(Map<String, Object> history:historyList) {
			String postDate = (String)history.get("postDate");
			String userName = (String)history.get("userName");
			String originalName = (String)history.get("originalName");
			String insert = String.valueOf(history.get("insert"));
			String update = String.valueOf(history.get("update"));

			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("postDate", postDate);
			resultMap.put("userName", userName);
			resultMap.put("originalName", originalName);
			resultMap.put("result", "입력: "+insert+"건, 수정: "+update+"건");

			resultList.add(resultMap);

		}
		return resultList;
	}
	@Override
	public Map<String, Integer> centerUploadFile(MultipartFile file, int startRow, int userCode) {
		//리턴할 Map생성
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("fileNo", 0);
		resultMap.put("userCode", 0);
		resultMap.put("insert", 0);
		resultMap.put("update", 0);

		POIClass poi = new CenterPOI();
		List<Object> VOList = poi.readWorkBook(file, startRow);
		for(Object vo : VOList) {
			CenterVO center = (CenterVO)vo;

			//기존 데이터가 없으면 
			if (center.getCenterCode() == 0) {
				centerRepository.insertCenter(center);
				System.out.println();
				//replace(첫번쨰는 key, 두번째는 oldValue, 세번째는 newValue) ->저장된 key의 value가 oldvalue
				//와 동일할 때만 newvalue로 변경, 교체가 되면 true를 리턴, 동일하지 않느면 교체되지 않고 false 리턴
				resultMap.replace("insert", resultMap.get("insert"), resultMap.get("insert")+1);
			}else {
				centerRepository.centerUpdate(center);
				resultMap.replace("update", resultMap.get("update"), resultMap.get("update")+1);
			}
		}
		Date date = new Date();

		String filePathName = filePath+"centerExcel_"+date.getTime()+"_"+file.getOriginalFilename();
		
		FileInfoVO fileInfoVO = new FileInfoVO();
		fileInfoVO.setFileSavedName("centerExcel_"+date.getTime()+"_"+file.getOriginalFilename());
		fileInfoVO.setOriginalName(file.getOriginalFilename());
		fileInfoVO.setFileType(file.getContentType());
		fileInfoVO.setFilePath(filePathName);

		
		fileInfoVO.setUploadUserCode(userCode);
		System.out.println("fileInfoVo" + fileInfoVO);

		fileRepository.insertFile(fileInfoVO);

		resultMap.replace("fileNo",0, fileInfoVO.getFileNo());
		resultMap.replace("userCode",0, fileInfoVO.getUploadUserCode());

		fileRepository.insertFileUploadHistory(resultMap);

		try {
			//이친구는 왜 하는거징? -> 업로드한 파일 데이터를  지정한 파일에 저장한다
			file.transferTo(new File(filePathName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}
	/**
	 * 센터 일괄 업로드 파일 수
	 * @author 이소정
	 * @return 센터 일괄 업로드 파일 수
	 * */
	public int countUploadHistory() {
		return centerRepository.countUploadHistory();
	}
	/**
	 * 중복된 센터 이름 확인
	 * @author 이소정
	 * @param String 센터 이름
	 * @return 저장된 센터 이름 있으면 1, 없으면 0
	 * */
	@Override
	public int centerNameCheck(String centerName) {
		int result = centerRepository.centerNameCheck(centerName);
		return result;
	}
	/**
	 * 중복된 전화번호 확인
	 * @author 이소정
	 * @param String 전화번호
	 * @return 저장된 전화번호 있으면 1, 없으면 0
	 * */
	@Override
	public int checkCenterTel(String centerTel) {
		return centerRepository.checkCenterTel(centerTel);
	}

}
