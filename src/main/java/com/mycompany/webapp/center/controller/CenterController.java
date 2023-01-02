package com.mycompany.webapp.center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.multipart.MultipartFile;
/**
 * @ClassName : centerController.java
 * @Description : 센터에 관한 controller
 * @Modification 
 * @
 * @       수정일                  수정자                 수정내용
 * @ ==============   ============   ===========
 * @
 * @author 이소정
 * **/
@Controller
public class CenterController {

	@RequestMapping(value="/centerPhoto")
	public String managePhotoCenter() {
		return "jsp/center/centerPhoto";
	}
	
	@RequestMapping(value="/centerList")
	public String loadCenter() {
		return "jsp/center/centerList";
	}
	
	@RequestMapping(value="/insertCenter")
		public String insertCenter() {
			return "jsp/center/centerinsert";
	} 
	
	
}