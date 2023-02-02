package com.mycompany.webapp.common.poi;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.webapp.center.vo.CenterVO;

public class CenterPOI extends POIClass{
	private static final Logger logger = LoggerFactory.getLogger(POIClass.class);

	CenterVO center;

	@Override
	public void handlingData(Cell cell, List<Object> VOList) {
		int col = cell.getAddress().getColumn();
		switch (col) {
		case 1:
			center = new CenterVO();

			int centerCode;

			if (cell.getCellType().toString().equals("NUMERIC")) {
				if (cell.getNumericCellValue() == 0) {
					centerCode = 0;
				}else {
					centerCode = (int)cell.getNumericCellValue();
				}
			}else {
				if (cell.getStringCellValue().equals("0")) {
					centerCode = 0;
				}else {
					centerCode = Integer.parseInt(cell.getStringCellValue());
				}
			}
			center.setCenterCode(centerCode);
			break;
		case 2:
			String centerName = cell.getStringCellValue();
			center.setCenterName(centerName);
			break;
		case 3:
			String centerAddress = cell.getStringCellValue();
			center.setCenterAddress(centerAddress);
			break;
		case 4:
			if (cell.getStringCellValue() == null) {
				center.setCenterGuide("");
			}else {
				String centerGuide = cell.getStringCellValue();
				center.setCenterGuide(centerGuide);
			}
			break;
		case 5:
			String centerOpeningDate;
			//타입이 number일 때
			if (cell.getCellType().toString().equals("NUMERIC")) {
				if ((int)cell.getNumericCellValue() == 0) {
					centerOpeningDate = "";
				}else {
					centerOpeningDate = String.valueOf((int)cell.getNumericCellValue());
				}
			}else {
				//string타입으로 값이 null일 때
				if (cell.getStringCellValue().equals("0")) {
					centerOpeningDate = "";
				}else {
					centerOpeningDate = cell.getStringCellValue();
				}
			}
			center.setCenterOpeningDate(centerOpeningDate);
			break;
		case 6:
			String centerClosingDate;
			//타입이 number일 때
			if (cell.getCellType().toString().equals("NUMERIC")) {
				if ((int)cell.getNumericCellValue() == 0) {
					centerClosingDate = "";
				}else {
					centerClosingDate = String.valueOf((int)cell.getNumericCellValue());
				}
			}else {
				if (cell.getStringCellValue().equals("0")) {
					centerClosingDate = "";
				}else {
					centerClosingDate = cell.getStringCellValue();
				}
			}
			center.setCenterClosingDate(centerClosingDate);
			break;
		case 7:
			String centerTel;
			if (cell.getCellType().toString().equals("NUMERIC")) {
				centerTel = String.valueOf((int)cell.getNumericCellValue());
			}else {
				centerTel = cell.getStringCellValue();
			}
			center.setCenterTel(centerTel);
			VOList.add(center);
			break;
		default:
			break;
		}
	}
}
