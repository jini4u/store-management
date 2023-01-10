package com.mycompany.webapp.common.poi;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 점수 엑셀 파일 일괄 업로드를 위한 클래스
 * @author 임유진
 * 엑셀파일 열 순서: 센터코드, 점검년도, 점검시즌, 그룹코드, 상세코드, 점수, 담당자 코드
 * */
public class ScorePOI extends POIClass {

	@Override
	public void handlingData(Cell cell) {
		int col = cell.getAddress().getColumn();
		switch(col) {
		case 1:
			//getNumericCellValue는 기본적으로 double형을 반환해줌
			//(int)cell.getNumericCellValue() 이런식으로 형변환해서 사용
			String centerCode = cell.getStringCellValue();
			System.out.print(centerCode+" ");
			break;
		//문자일 경우
		case 2:
			//cell.getStringCellValue()
			int checkYear = (int)cell.getNumericCellValue();
			System.out.print(checkYear+" ");
			break;
		case 3:
			int checkSeason = (int)cell.getNumericCellValue();
			System.out.print(checkSeason+" ");
			break;
		case 4:
			String groupCode = cell.getStringCellValue();
			System.out.print(groupCode+" ");
			break;
		case 5:
			String detailCode = cell.getStringCellValue();
			System.out.print(detailCode+" ");
			break;
		case 6:
			int score = (int)cell.getNumericCellValue();
			System.out.print(score+" ");
			break;
		case 7:
			int userCode = (int)cell.getNumericCellValue();
			System.out.println(userCode);
			break;
		}
	}

}
