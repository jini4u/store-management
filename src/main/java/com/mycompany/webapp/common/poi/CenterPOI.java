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
			if (cell.getStringCellValue().equals("0")) {
				center.setCenterCode(0);
				System.out.println("여기 들어와?");
			}else {
			String centerCode = cell.getStringCellValue();
			center.setCenterCode(Integer.parseInt(centerCode));
			System.out.println("여기 들어와?2");
			}
			break;
		case 2:
			String centerName = cell.getStringCellValue();
			center.setCenterName(centerName);
			System.out.println("센터이름"+centerName);
			break;
		case 3:
			String centerAddress = cell.getStringCellValue();
			center.setCenterAddress(centerAddress);
			System.out.println("센터주소"+centerAddress);
			break;
		case 4:
			if (cell.getStringCellValue() == null) {
				center.setCenterGuide("");
				System.out.println("센터오시는길2");
			}else {
				String centerGuide = cell.getStringCellValue();
				center.setCenterGuide(centerGuide);
				System.out.println("센터오시는길"+centerGuide);
			}
			break;
		case 5:
			if ((int)cell.getNumericCellValue() == 0) {
				center.setCenterOpeningDate("");
				System.out.println("센터 오픈일2");
			}else {
				String centerOpeningDate = String.valueOf((int)cell.getNumericCellValue());
				center.setCenterOpeningDate(centerOpeningDate);
				System.out.println("센터 오픈일2"+centerOpeningDate);
			}
			break;
		case 6:
			if ((int)cell.getNumericCellValue() == 0) {
				center.setCenterClosingDate("");
				System.out.println("센터 폐점일1");
			}else {
				String centerClosingDate = String.valueOf((int)cell.getNumericCellValue());
				center.setCenterClosingDate(centerClosingDate);
				System.out.println("센터 폐점일"+centerClosingDate);
			}
			break;
		case 7:
			String centerTel = cell.getStringCellValue();
			center.setCenterTel(centerTel);
			System.out.println("센터 전화번호"+centerTel);
			VOList.add(center);
			break;
		default:
			break;
		}
	}
}
