package com.mycompany.webapp.manager.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.center.vo.CenterVO;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.vo.ManagerVO;

public interface IManagerService {
	/* author 은별
	 * 담당자 등록 */
	int insertManager(ManagerVO mgr);
	
	/* author 은별
	 * 담당자 목록 조회 */
	List<ManagerVO> selectManagerList(Pager pager);
	
	/* author 은별
	 * 담당자 수정 */
	int managerUpdate(ManagerVO mgr);
	
	/* author 유진
	 * 담당자 매핑 */
	List<CenterVO> getCenterByManager(int userCode);
	int cancelMapping(int userCode, int centerCode);
	int mapping(int userCode, int centerCode);
	
	int countAllMgr();
	
	/* author 은별
	 * 담당자 검색 */
	//키워드별 담당자 검색
	List<ManagerVO> managerSearch(Pager pager, String keyword, String keywordType);
	// 키워드별 담당자 수  
	int managerCountByKeyword(String keyword, String keywordType);
	
	/* author 은별
	 * 담당자 일괄 업로드*/
	Map<String, Integer> mgrUploadFileInfo(MultipartFile file, int startRow, int userCode);	/* author 은별
	 * 담당자 엑셀 업로드 히스토리 */
	List<Map<String, String>> mgrUploadFileHistory();


	 
}
