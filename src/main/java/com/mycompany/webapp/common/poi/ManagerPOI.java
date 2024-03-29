package com.mycompany.webapp.common.poi;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.mycompany.webapp.manager.vo.ManagerVO;

/**
 * 담당자 엑셀 파일 일괄 업로드를 위한 클래스
 * @author 고은별
 * 엑셀파일 열 순서: 담당자 코드, 담당자명, 생년월일, 전화번호, email, 팀코드, 입사일자, 퇴사일자
 * */
public class ManagerPOI extends POIClass {

	ManagerVO mgr;
	
	@Override
	public void handlingData(Cell cell, List<Object> VOList) {
		int col = cell.getAddress().getColumn();
		switch(col) {
		case 1:	
			mgr = new ManagerVO();
			int userCode;
			
			if(cell.getCellType().toString().equals("NUMERIC")) {
				if(cell.getNumericCellValue() == 30000) {
					mgr.setUserCode(30000);
				}else {
					userCode = (int)cell.getNumericCellValue();
					mgr.setUserCode(userCode);
				}
			}else {
				if(cell.getStringCellValue().equals("30000")) {
					mgr.setUserCode(30000);
				}else {
					userCode = Integer.parseInt(cell.getStringCellValue());
					mgr.setUserCode(userCode);
				}		
			}
			break;
		case 2:
			String userPassword;
			if(cell.getCellType().toString().equals("NUMERIC")) {
				userPassword= String.valueOf((int)cell.getNumericCellValue());
			}else {
				userPassword= cell.getStringCellValue();
			}
			mgr.setUserPassword(userPassword);
			System.out.println("담당자 비번: "+userPassword);
			break;
		case 3:
			String userName= cell.getStringCellValue();
			mgr.setUserName(userName);
			System.out.println("담당자 이름: "+userName);
			break;
		case 4:
			String userBirth;
			if(cell.getCellType().toString().equals("NUMERIC")) {
				userBirth =String.valueOf((int)cell.getNumericCellValue());
			}else {
				userBirth =cell.getStringCellValue();
			}
			mgr.setUserBirth(userBirth);
			System.out.println("담당자 생일: "+userBirth);
			break;
		case 5:
			String userTel;
			if(cell.getCellType().toString().equals("NUMERIC")) {
				userTel =String.valueOf((int)cell.getNumericCellValue());
			}else {
				userTel =cell.getStringCellValue();
			}
			mgr.setUserTel(userTel);
			System.out.println("담당자 전화번호: "+userTel);
			break;
		case 6: 
			String userEmail = cell.getStringCellValue();
			mgr.setUserEmail(userEmail);
			System.out.println("담당자 이메일: "+userEmail);
			break;
		case 7: 
			int userTeamCode =(int)cell.getNumericCellValue(); 
			mgr.setUserTeamCode(userTeamCode);
			System.out.println("담당자 팀코드: "+userTeamCode);
			break;
		case 8: 
			String userHireDate;
			if(cell.getCellType().toString().equals("NUMERIC")) {
				userHireDate =String.valueOf((int)cell.getNumericCellValue());
			}else {
				userHireDate =cell.getStringCellValue();
			}
			mgr.setUserHireDate(userHireDate);
			System.out.println("담당자 입사일: "+userHireDate);
			VOList.add(mgr);
			break;
		default:
			break;	
		}
	} 
}  

