package com.mycompany.webapp.common.poi;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.webapp.score.vo.ScoreVO;

/**
 * 점수 엑셀 파일 일괄 업로드를 위한 클래스
 * @author 임유진
 * 엑셀파일 열 순서: 센터코드, 점검년도, 점검시즌, 그룹코드, 상세코드, 점수, 담당자 코드
 * */
public class ScorePOI extends POIClass {
	private static final Logger logger = LoggerFactory.getLogger(ScorePOI.class);
	
	ScoreVO oneScore;

	@Override
	public void handlingData(Cell cell, List<Object> VOList) {
		int col = cell.getAddress().getColumn();
		switch(col) {
		case 1:
			//getNumericCellValue는 기본적으로 double형을 반환해줌
			//(int)cell.getNumericCellValue() 이런식으로 형변환해서 사용
			oneScore = new ScoreVO();
			String centerCode = cell.getStringCellValue();
			oneScore.setCenterCode(Integer.parseInt(centerCode));
			break;
		//문자일 경우
		case 2:
			//cell.getStringCellValue()
			int checkYear = (int)cell.getNumericCellValue();
			oneScore.setCheckYear(checkYear);
			break;
		case 3:
			int checkSeason = (int)cell.getNumericCellValue();
			oneScore.setCheckSeason(checkSeason);
			break;
		case 4:
			String groupCode = cell.getStringCellValue();
			oneScore.setCheckGroupCode(groupCode);
			break;
		case 5:
			String detailCode = cell.getStringCellValue();
			oneScore.setCheckDetailCode(Integer.parseInt(detailCode));
			break;
		case 6:
			int score = (int)cell.getNumericCellValue();
			oneScore.setCheckScore(score);
			break;
		case 7:
			String userCode = cell.getStringCellValue();
			oneScore.setUserCode(Integer.parseInt(userCode));
			//리스트에 완성된 점수VO 넣어주기
			VOList.add(oneScore);
			break;
		default:
			break;	
		}
	}

}
