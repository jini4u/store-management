package com.mycompany.webapp.score.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.common.dao.IFileRepository;
import com.mycompany.webapp.common.poi.POIClass;
import com.mycompany.webapp.common.poi.ScorePOI;
import com.mycompany.webapp.common.vo.FileInfoVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.score.dao.IScoreRepository;
import com.mycompany.webapp.score.vo.ScoreVO;

@Service
public class ScoreService implements IScoreService {
	private static Logger logger = LoggerFactory.getLogger(ScoreService.class);
	
	@Autowired
	IScoreRepository scoreRepository;
	@Autowired
	IFileRepository fileRepository;

	@Value("${file.path}")
	String filePath;
	
	/**
	 * 점수 엑셀 파일 업로드 이력 조회 
	 * @author 임유진
	 * @return {List<Map<String, String>>} 파일 업로드 이력을 담은 Map의 List
	 * */
	@Override
	public List<Map<String, String>> getScoreUploadHistory(int pageNo) {
		Pager pager = new Pager(10, 10, scoreRepository.countScoreUploadHistory(), pageNo);
		
		List<Map<String, Object>> historyList = scoreRepository.getScoreUploadHistory(pager);
		List<Map<String, String>> resultList = new ArrayList<Map<String,String>>();
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
	public Pager getHistoryPager(int pageNo) {
		Pager pager = new Pager(10, 10, scoreRepository.countScoreUploadHistory(), pageNo);
		return pager;
	}
	
	/**
	 * 전체 그룹코드 조회
	 * @author 임유진
	 * @return {List<Map<String, Object>>} 그룹코드, 그룹코드명, 사용여부
	 * */
	@Override
	public List<Map<String, Object>> getAllGroupCodes() {
		return scoreRepository.getAllGroupCodes();
	}

	/**
	 * 그룹코드에 따른 상세코드 조회
	 * @author 임유진
	 * @param {String} 그룹코드
	 * @return {List<Map<String, Object>>} 상세코드, 상세코드명, 사용여부
	 * */
	@Override
	public List<Map<String, Object>> getDetailCodes(String groupCode) {
		return scoreRepository.getDetailCodes(groupCode);
	}

	/**
	 * 상세코드명, 사용여부 수정
	 * @author 임유진
	 * @param {Map<String, String>} 수정한 상세코드, 상세코드명, 사용여부
	 * @return {int} 수정된 상세 코드 수 (0 또는 1)
	 * */
	@Override
	public int updateDetailCode(Map<String, String> detailCodeMap) {
		return scoreRepository.updateDetailCode(detailCodeMap);
	}

	/**
	 * 그룹코드명, 사용여부 수정
	 * @author 임유진
	 * @param {Map<String, String>} 수정한 그룹코드, 그룹코드명, 사용여부
	 * @return {int} 수정된 그룹 코드 수 (0 또는 1)
	 * */
	@Override
	public int updateGroupCode(Map<String, String> groupCodeMap) {
		//그룹코드 사용여부가 N이면 하위 상세코드들도 사용중지되도록
		if(groupCodeMap.get("groupOccupied").equals("N")) {
			Map<String, String> detailMap = new HashMap<String, String>();
			detailMap.put("detailOccupied", groupCodeMap.get("groupOccupied"));
			detailMap.put("groupCode", groupCodeMap.get("groupCode"));
			scoreRepository.updateDetailCode(detailMap);
		}
		return scoreRepository.updateGroupCode(groupCodeMap);
	}

	/**
	 * 상세코드 추가
	 * @author 임유진
	 * @param {Map<String, String>} 추가될 상세코드, 상세코드명, 사용여부
	 * @return {int} 추가된 상세코드 수 (0 또는 1)
	 * */
	@Override
	public int insertDetailCode(Map<String, String> detailCodeMap) {
		return scoreRepository.insertDetailCode(detailCodeMap);
	}

	/**
	 * 그룹코드 추가
	 * @author 임유진
	 * @param {Map<String, String>} 추가될 그룹코드, 그룹코드명, 사용여부
	 * @return {int} 추가된 그룹코드 수 (0 또는 1)
	 * */
	@Override
	public int insertGroupCode(Map<String, String> groupCodeMap) {
		return scoreRepository.insertGroupCode(groupCodeMap);
	}

	//윤선

	//전체 점수 조회
	@Override
	public List<ScoreVO> getScoreList(ScoreVO scoreVO, Pager pager) {
		Map<String, Integer> map = new HashMap<>();
		map.put("centerCode", scoreVO.getCenterCode());
		map.put("checkYear", scoreVO.getCheckYear());
		map.put("checkSeason", scoreVO.getCheckSeason());
		map.put("startRowNo", pager.getStartRowNo());
		map.put("endRowNo", pager.getEndRowNo());
		return scoreRepository.getScoreList(map);
	}

	//점수 수정
	//점수 수정
	@Override
	public int updateScore(ScoreVO score) {
		for(int i=0; i<score.getArrayScore().length; i++) {

			ScoreVO update = new ScoreVO();
			update.setCenterCode(score.getCenterCode());
			update.setCheckYear(score.getCheckYear());
			update.setCheckSeason(score.getCheckSeason());
			//groupcode에서의 값들을 가져오기 위함
			update.setCheckGroupCode(score.getArrayCheckGroupCode()[i]);
			update.setCheckDetailCode(score.getArrayCheckDetailCode()[i]);
			update.setCheckScore(score.getArrayScore()[i]);
			scoreRepository.updateScore(update);
		}
		return score.getArrayScore().length;
	}


	//점수등록
	@Override
	@Transactional
	public int insertScore(ScoreVO scoreVO) {
		for(int i=0; i<scoreVO.getArrayScore().length; i++) {
			ScoreVO vo = new ScoreVO();
			vo.setCenterCode(scoreVO.getCenterCode());
			vo.setUserCode(scoreVO.getUserCode());
			vo.setCheckYear(scoreVO.getCheckYear());
			vo.setCheckSeason(scoreVO.getCheckSeason());
			vo.setCheckGroupCode(scoreVO.getArrayCheckGroupCode()[i]);
			vo.setCheckDetailCode(scoreVO.getArrayCheckDetailCode()[i]);
			vo.setCheckScore(scoreVO.getArrayScore()[i]);
			scoreRepository.insertScore(vo);
		}
		return scoreVO.getArrayScore().length;
	}

	//모달창 점수 항목 출력 리스트
	@Override
	public List<ScoreVO> usingCodeList() {

		return scoreRepository.usingCodeList();
	}
	
	//점수리스트 수 받아오기
	@Override
	public int countAllList() {
		return scoreRepository.countAllList();
	}
	
	@Override
	public int countListByCenterCode(ScoreVO score) {
		return scoreRepository.countListByCenterCode(score);
	}

	@Override
	public List<ScoreVO> getCenterName(int userCode) {
		return scoreRepository.getCenterName(userCode);
	}



	/**
	 * 업로드한 파일을 읽어서 얻은 정보를 DB에 저장, 파일을 저장
	 * @param {MultipartFile} 업로드한 파일
	 * @param {startRow} 몇번째 인덱스의 열까지 제외하고 읽기 시작할지
	 * @return {Map<String, Integer>} <insert, 입력된 행 수>,<update, 수정된 행 수> 가 담긴 맵
	 * */
	@Override
	public Map<String, Integer> uploadFileInfo(MultipartFile file, int startRow, int userCode) {
		
		//리턴할 Map 생성
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("fileNo", 0);
		resultMap.put("userCode", userCode);
		resultMap.put("insert", 0);
		resultMap.put("update", 0);
		
		POIClass poi = new ScorePOI();
		List<Object> VOList = poi.readWorkBook(file, startRow);
		for(Object vo:VOList) {
			ScoreVO score = (ScoreVO)vo;
			//테이블에 같은 년도,시즌,코드의 데이터가 있는지
			int existData = scoreRepository.isDataExist(score);
			//기존 데이터가 없으면
			if(existData == 0) {
				//insert 해주기
				scoreRepository.insertScore(score);
				
				resultMap.replace("insert", resultMap.get("insert"), resultMap.get("insert")+1);
			} else {	//기존 데이터가 있으면
				//update 해주기 
				scoreRepository.updateScore(score);
				
				resultMap.replace("update", resultMap.get("update"), resultMap.get("update")+1);
			}
		}
		Date date = new Date();
		
		String filePathName = filePath+"scoreExcel_"+date.getTime()+"_"+file.getOriginalFilename();
		
		FileInfoVO fileVO = new FileInfoVO();
		fileVO.setFileSavedName("scoreExcel_"+date.getTime()+"_"+file.getOriginalFilename());
		fileVO.setOriginalName(file.getOriginalFilename());
		fileVO.setFileType(file.getContentType());
		fileVO.setFilePath(filePathName);
		fileVO.setUploadUserCode(userCode);
		
		fileRepository.insertFile(fileVO);
		int fileNo = fileVO.getFileNo();
		
		resultMap.replace("fileNo", 0, fileNo);
		
		fileRepository.insertFileUploadHistory(resultMap);
		
		try {
			file.transferTo(new File(filePathName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

	@Override
	public int overlapGroupCode(String groupCode) {
		return scoreRepository.overlapGroupCode(groupCode);
	}

	@Override
	public int overlapDetailCode(String groupContent) {
		return scoreRepository.overlapDetailCode(groupContent);
	}
	


	@Override
	public int overlapGroupDetailContent(String detailcontent) {
		return scoreRepository.overlapGroupDetailContent(detailcontent);

	}

	@Override
	public int overlapGroupDetailCode(String detailCode, String groupCode) {
		int dc = Integer.parseInt(detailCode);
		return scoreRepository.overlapGroupDetailCode(dc,groupCode);
	}

}