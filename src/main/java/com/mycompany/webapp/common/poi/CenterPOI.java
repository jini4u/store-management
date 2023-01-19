package com.mycompany.webapp.common.poi;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.webapp.center.vo.CenterVO;

public class CenterPOI extends POIClass{
	private static final Logger logger = LoggerFactory.getLogger(POIClass.class);

	CenterVO center;
	@Override
	public void handlingData(Cell cell, List<Object> VOList) {
		int col = cell.getAddress().getColumn();
		switch (col) {
		case 1:
			//00001이렇게 시작되는 건 String으로 인식된다
			center = new CenterVO();
			String centerCode = cell.getStringCellValue();
			System.out.println("들어오나1?" + centerCode);
			center.setCenterCode(Integer.parseInt(centerCode));
			break;
		case 2:
			String centerName = cell.getStringCellValue();
			System.out.println("들어오나2?" + centerName);
			center.setCenterName(centerName);
			break;
		case 3:
			String centerAddress = cell.getStringCellValue();
			System.out.println("들어오나3?" + centerAddress);
			center.setCenterAddress(centerAddress);
			break;
		case 4:
			if (cell.getStringCellValue() == null) {
				System.out.println("들어오나4?" + cell.getStringCellValue());
				center.setCenterGuide("");
			}else {
				String centerGuide = cell.getStringCellValue();
				System.out.println("들어오나5?" + centerGuide);
				center.setCenterGuide(centerGuide);
			}
			break;
		case 5:
			if ((int)cell.getNumericCellValue() == 0) {
				System.out.println("들어오나6?" + cell.getNumericCellValue());
				center.setCenterOpeningDate("");
			}else {
				String centerOpeningDate = String.valueOf((int)cell.getNumericCellValue());
				System.out.println("들어오나7?" + centerOpeningDate);
				center.setCenterOpeningDate(centerOpeningDate);
			}
			break;
		case 6:
			if ((int)cell.getNumericCellValue() == 0) {
				System.out.println("들어오나8?" + (int)cell.getNumericCellValue());
				center.setCenterClosingDate("");
			}else {
				String centerClosingDate = String.valueOf((int)cell.getNumericCellValue());
				System.out.println("들어오나9?" + centerClosingDate);
				center.setCenterClosingDate(centerClosingDate);
			}
			break;
		case 7:
			String centerTel = cell.getStringCellValue();
			System.out.println("들어오나10?" + centerTel);
			center.setCenterTel(centerTel);
			VOList.add(center);
			break;
		default:
			break;
		}
	}
}
