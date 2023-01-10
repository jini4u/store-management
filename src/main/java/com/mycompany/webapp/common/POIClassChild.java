package com.mycompany.webapp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.common.poi.POIClass;

public class POIClassChild extends POIClass {
	@Override
	public void handlingData(Cell cell) {
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
	}
	
	public static void main(String[] args) {
		POIClass pc = new POIClassChild();
//		pc.readWorkBook(mf);
	}
}
