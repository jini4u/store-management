package com.mycompany.webapp.center.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.center.dao.ICenterRepository;
import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;

@Service
public class CenterService implements ICenterService{

	@Autowired
	ICenterRepository centerRepository;
	
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
		
	//유진
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
	 * @param String 파일 내부/외부 정보
	 * @param int 센터코드
	 * @param int 업로드하는 담당자 번호
	 * @param List<file> 사진 파일 리스트
	 * @return int 등록된 사진 수
	 * @exception 파일 저장 오류시, return 0
	 * */
	@Override
	public int addCenterImage(String fileDetail, int centerCode, int uploadUserCode, List<MultipartFile> files) {
		int result = 0;
		
		for(MultipartFile file: files) {
			FileInfoVO newFile = new FileInfoVO();
			newFile.setFileDetail(fileDetail);
			newFile.setCenterCode(centerCode);
			newFile.setUploadUserCode(uploadUserCode);
			newFile.setOriginalName(file.getOriginalFilename());
			String fileSavedName = "centerCode_"+centerCode+"+originalName_"+file.getOriginalFilename();
			newFile.setFileSavedName(fileSavedName);
			newFile.setFileType(file.getContentType());
			newFile.setFilePath(filePath);
			try {
				//파일 저장
				file.transferTo(new File(filePath+fileSavedName));
			} catch (Exception e) {
				return 0;
			}
			result += centerRepository.addCenterImage(newFile);
		}
		
		return result;
	}
	
	/**
	 * @author 임유진
	 * 센터 이미지 조회
	 * @param int 센터 코드
	 * @return List<해당 코드의 사진>
	 * */
	@Override
	public List<FileInfoVO> getCenterImageNames(int centerCode) {
		return centerRepository.getCenterImageNames(centerCode);
	}
	
	/**
	 * @author 임유진
	 * 센터 이미지 정보 수정
	 * @param FileInfoVO 수정할 내용이 담긴 파일
	 * @param int 센터 코드
	 * @param String 원래 파일 이름
	 * @return 정보 수정된 파일 수 (0 또는 1)
	 * */
	@Override
	public int updateImage(FileInfoVO file, int centerCode, String oldOriginalName) {
		String oldSavedName = "centerCode_"+centerCode+"+originalName_"+oldOriginalName;
		File oldFile = new File(filePath+oldSavedName);
		String newSavedName = "centerCode_"+centerCode+"+originalName_"+file.getOriginalName();
		
		file.setFileSavedName(newSavedName);
		File newFile = new File(filePath+newSavedName);

		byte[] buf = new byte[1024];
		FileInputStream is = null;
		FileOutputStream os = null;
		
		if(!oldFile.renameTo(newFile)) {
			try {
			buf = new byte[1024];
			is = new FileInputStream(oldFile);
			os = new FileOutputStream(newFile);
			
			int read = 0;
			while((read=is.read(buf,0,buf.length)) != -1) {
				os.write(buf, 0, read);
			}
			
			is.close();
			os.close();
			oldFile.delete();
			} catch (Exception e) {
				return 0;
			}
		}
		
		return centerRepository.updateImage(file);
	}
	
	/**
	 * @author 임유진
	 * 센터 이미지 삭제
	 * @param List<int> 삭제할 파일 번호 리스트
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
	 * @author 이소정
	 * 검색된 센터 정보 리스트
	 * @param Pager 검색된 페이지 수
	 * *@param String 검색한 센터명
	 * @return 검색된 센터 정보 리스트
	 * */
	@Override
	public List<CenterVO> findCenter(Pager filterPager, String keyword) {
		return centerRepository.findCenter(filterPager, keyword);
	}
	
	/**
	 * @author 이소정
	 * 검색된 센터 정보 수(합칠 수 있는지 확인)
	 * @param String 검색한 센터명
	 * @return 검색된 센터 정보 수
	 * */
	@Override
	public int filterCountAllCenters(String keyword) {
		return centerRepository.filterCountAllCenters(keyword);
	}

	


}
