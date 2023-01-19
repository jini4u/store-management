package com.mycompany.webapp.common.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.score.vo.ScoreVO;

/**
 * 업로드한 엑셀 파일 처리를 위한 클래스
 * @author 임유진
 * */
public abstract class POIClass {
	
	/**
	 * 엑셀 파일을 읽어줌
	 * @author 임유진 
	 * @param {MultipartFile} 업로드한 파일
	 * @param {int} 몇번째 인덱스의 열까지 제외하고 읽기 시작할지
	 * @return {List<Object>} 파일의 행 정보를 원하는 VO에 담아 List로 리턴
	 * */
	public List<Object> readWorkBook(MultipartFile mf, int startRow) {
		try {
			//getInputStream: 파일 컨텐트를 읽음
			InputStream fileIS = mf.getInputStream();
			//엑셀파일로 Workbook 인스턴스 생성
			XSSFWorkbook workbook = new XSSFWorkbook(fileIS);
			//0번 인덱스(첫번째)시트를 가져온다
			XSSFSheet sheet = workbook.getSheetAt(0);
			//모든 row 조회를 위한 반복자 획득
			Iterator<Row> rowIter = sheet.iterator();
			//행에 들어있는 정보들을 담을 List
			List<Object> VOList = new ArrayList<Object>();
			//모든행 조회
			int rowCnt = 0;
			while(rowIter.hasNext()) {
				rowCnt++;
				//한줄씩 얻어오기
				Row row = rowIter.next();
				//몇번째줄부터 읽을지
				if(rowCnt > startRow) {
					//모든 cell 조회를 위한 반복자 획득
					Iterator<Cell> cellIter = row.iterator();
					//모든 cell 조회
					while(cellIter.hasNext()) {
						//한칸씩 얻어오기
						Cell cell = cellIter.next();
						//읽은 데이터의 VO들을 List에 담아줌
						handlingData(cell, VOList);
					} //cell 조회 while문 끝
				}
			} //row 조회 while문 끝
			workbook.close();
			fileIS.close();
			return VOList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 셀 정보를 어떻게 읽을지, 읽어서 어떤 객체로 만들지를 다루는 추상메소드
	 * @param {Cell} 엑셀 파일의 셀
	 * @param {List<Object>} 컨트롤러에 있는 객체들을 담는 리스트
	 * */
	public abstract void handlingData(Cell cell, List<Object> VOList);

}
