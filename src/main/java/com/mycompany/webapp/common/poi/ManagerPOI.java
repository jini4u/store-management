package com.mycompany.webapp.common.poi;

import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.webapp.manager.vo.ManagerVO;
import com.mycompany.webapp.score.vo.ScoreVO;


/**
 * 담당자 엑셀 파일 일괄 업로드를 위한 클래스
 * @author 고은별
 * 엑셀파일 열 순서: 담당자 코드, 담당자명, 생년월일, 전화번호, email, 팀코드, 입사일자, 퇴사일자
 * */
public class ManagerPOI extends POIClass {
	private static final Logger logger = LoggerFactory.getLogger(ScorePOI.class);

	ManagerVO mgr;

	@Override
	public void handlingData(Cell cell, List<Object> VOList) {
		int col = cell.getAddress().getColumn();
/*		if (cell == null) {
			continue;
		} else {*/
			switch(col) {
			case 1:
				//VO를 만들어주지 않으므로 만듦
				mgr = new ManagerVO();
				int userCode = (int)cell.getNumericCellValue();
				mgr.setUserCode(userCode);
				break;
			case 2:
				String userPassword= cell.getStringCellValue();
				mgr.setUserPassword(userPassword);
				break;
			case 3:
				String userName= cell.getStringCellValue();
				mgr.setUserName(userName);
				break;
			case 4:
				Date userBirth = cell.getDateCellValue();
				mgr.setUserBirth(userBirth);
				break;
			case 5:
				String userTel = cell.getStringCellValue();
				mgr.setUserTel(userTel);
				break;
			case 6: 
				String userEmail = cell.getStringCellValue();
				mgr.setUserEmail(userEmail);
			case 7: 
				int userTeamCode = (int)cell.getNumericCellValue();
				mgr.setUserTeamCode(userTeamCode);
				break;
			case 8: 
				Date userHireDate = cell.getDateCellValue();
				mgr.setUserHireDate(userHireDate);
				break;
			case 9:
				Date userResignDate = cell.getDateCellValue();
				mgr.setUserResignDate(userResignDate);
				VOList.add(mgr);
				break;
			default:
				break;

			}
		}
	}  
