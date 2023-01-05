package com.mycompany.webapp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIClass {
	
	/**
	 * @ .xlsx 파일을 읽어오는 메소드
	 * @param filePath 파일이 저장되어있는 폴더의 절대경로
	 * @param fileName 읽어올 파일 이름, .xlsx까지 포함
	 * */
	void readWorkBook(String filePath, String fileName) {
		try {
			//경로, 이름 이용 파일에 대한 입력스트림 생성
			FileInputStream file = new FileInputStream(new File(filePath, fileName));
			//엑셀파일로 Workbook 인스턴스 생성
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//0번 인덱스(첫번째)시트를 가져온다
			XSSFSheet sheet = workbook.getSheetAt(0);
			//모든 row 조회를 위한 반복자 획득
			Iterator<Row> rowIter = sheet.iterator();
			//모든행 조회
			while(rowIter.hasNext()) {
				//한줄씩 얻어오기
				Row row = rowIter.next();
				//모든 cell 조회를 위한 반복자 획득
				Iterator<Cell> cellIter = row.iterator();
				//모든 cell 조회
				while(cellIter.hasNext()) {
					//한칸씩 얻어오기
					Cell cell = cellIter.next();
					//셀 내용 타입에 따라 처리
					//getCellType은 ENUM: NUMERIC, STRING, BOOLEAN, ERROR
					switch(cell.getCellType()) {
					//숫자일 경우
					case NUMERIC:
						//getNumericCellValue는 기본적으로 double형을 반환해줌
						//(int)cell.getNumericCellValue()) 이런식으로 형변환해서 사용
						break;
					//문자일 경우
					case STRING:
						//cell.getStringCellValue()
						break;
					}
				} //cell 조회 while문 끝
			} //row 조회 while문 끝
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
