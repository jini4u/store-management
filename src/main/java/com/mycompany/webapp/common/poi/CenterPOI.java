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
			String centerCode = cell.getStringCellValue();
			center.setCenterCode(Integer.parseInt(centerCode));
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
			if ((int)cell.getNumericCellValue() == 0) {
				center.setCenterOpeningDate("");
			}else {
				String centerOpeningDate = String.valueOf((int)cell.getNumericCellValue());
				center.setCenterOpeningDate(centerOpeningDate);
			}
			break;
		case 6:
			if ((int)cell.getNumericCellValue() == 0) {
				center.setCenterClosingDate("");
			}else {
				String centerClosingDate = String.valueOf((int)cell.getNumericCellValue());
				center.setCenterClosingDate(centerClosingDate);
			}
			break;
		case 7:
			String centerTel = cell.getStringCellValue();
			center.setCenterTel(centerTel);
			VOList.add(center);
			break;
		default:
			break;
		}
	}
}
