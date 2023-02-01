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
		//getNumericCellValue는 기본적으로 double형을 반환해줌
		//(int)cell.getNumericCellValue() 이런식으로 형변환해서 사용
		case 1:
			oneScore = new ScoreVO();
			
			int centerCode;
			
			if(cell.getCellType().toString().equals("NUMERIC")) {
				centerCode = (int)cell.getNumericCellValue();
			} else {
				centerCode = Integer.parseInt(cell.getStringCellValue());
			}
			
			oneScore.setCenterCode(centerCode);
			break;
		case 2:
			int checkYear;
			
			if(cell.getCellType().toString().equals("NUMERIC")) {
				checkYear = (int)cell.getNumericCellValue();
			} else {
				checkYear = Integer.parseInt(cell.getStringCellValue());
			}
			
			oneScore.setCheckYear(checkYear);
			break;
		case 3:
			int checkSeason;

			if(cell.getCellType().toString().equals("NUMERIC")) {
				checkSeason = (int)cell.getNumericCellValue();
			} else {
				checkSeason = Integer.parseInt(cell.getStringCellValue());
			}
			
			oneScore.setCheckSeason(checkSeason);
			break;
		case 4:
			String groupCode;
			
			if(cell.getCellType().toString().equals("NUMERIC")) {
				groupCode = String.valueOf((int)cell.getNumericCellValue());
			} else {
				groupCode = cell.getStringCellValue();
			}
			
			oneScore.setCheckGroupCode(groupCode);
			break;
		case 5:
			int detailCode;
			
			if(cell.getCellType().toString().equals("NUMERIC")) {
				detailCode = (int)cell.getNumericCellValue();
			} else {
				detailCode = Integer.parseInt(cell.getStringCellValue());
			}
			
			oneScore.setCheckDetailCode(detailCode);
			break;
		case 6:
			int score;
			
			if(cell.getCellType().toString().equals("NUMERIC")) {
				score = (int)cell.getNumericCellValue();
			} else {
				score = Integer.parseInt(cell.getStringCellValue());
			}
			
			oneScore.setCheckScore(score);
			break;
		case 7:
			int userCode;
			
			if(cell.getCellType().toString().equals("NUMERIC")) {
				userCode = (int)cell.getNumericCellValue();
			} else {
				userCode = Integer.parseInt(cell.getStringCellValue());
			}
			
			oneScore.setUserCode(userCode);
			//리스트에 완성된 점수VO 넣어주기
			VOList.add(oneScore);
			break;
		default:
			break;	
		}
	}

}
