package com.mycompany.webapp.stat.controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mycompany.webapp.center.service.ICenterService;
import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.manager.service.IManagerService;
import com.mycompany.webapp.score.service.IScoreService;
import com.mycompany.webapp.score.vo.ScoreVO;
import com.mycompany.webapp.stat.service.IStatService;

/**
 * 통계 정보 제공을 위한 Controller
 * */
@Controller
public class StatController {
	static final Logger logger=LoggerFactory.getLogger(StatController.class);
	
	@Autowired
	private ICenterService centerService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IScoreService scoreService;
	@Autowired
	private IStatService statService;
	
	/**
	 * 통계 화면에서 각 항목들에 필요한 리스트를 담아 뷰페이지 리턴 
	 * @author 임유진
	 * */
	@RequestMapping(value="/statistics")
	public String statistics(Model model) {
		int countAllCenters = centerService.countAllCenters();
		Pager centerPager = new Pager(countAllCenters, 1, countAllCenters, 1);
		model.addAttribute("centerList", centerService.centerList(centerPager));
		
		int countAllManagers = managerService.countAllMgr();
		Pager managerPager = new Pager(countAllManagers, 1, countAllManagers, 1);
		model.addAttribute("managerList", managerService.selectManagerList(managerPager));
		
		model.addAttribute("codeList", statService.getCodes());
		return "jsp/statistics";
	}
	
	/**
	 * 	센터 코드를 이용한 센터 평균 점수와 모든 센터들의 점수 조회
	 *  @author 임유진 
	 *  @param {int} 센터 코드 
	 *  @return {Map<String, List<ScoreVO>>} 전체 평균과 센터 평균들을 담은 리스트 
	 * */
	@RequestMapping("/centerAvgScore/{centerCode}")
	public @ResponseBody Map<String, List<ScoreVO>> getCenterAvgScores(@PathVariable int centerCode){
		return statService.getCenterAvgScores(centerCode);
	}
	
	/**
	 * 담당자 코드를 이용한 담당자 평균 점수와 모든 담당자의 점수 조회
	 * @author 임유진
	 * @param {int} 담당자 코드
	 * @return {Map<String, List<ScoreVO>>} 전체 평균과 담당자 평균들을 담은 리스트
	 * */
	@RequestMapping("/managerAvgScore/{userCode}")
	public @ResponseBody Map<String, List<ScoreVO>> getManagerAvgScores(@PathVariable int userCode){
		return statService.getManagerAvgScores(userCode);
	}
	
	@RequestMapping("/codeAvgScore/{userCode}")
	public @ResponseBody Map<String, List<ScoreVO>> getCodeAvgScores(@PathVariable int userCode, @RequestParam(name="group") String groupCode, @RequestParam(name="detail") int detailCode){
		return statService.getAvgScoreByCheckCode(groupCode, detailCode, userCode);
	}
	
	@RequestMapping("/centerSubStat/{centerCode}")
	public @ResponseBody Map<String, String> getCenterSubStat(@PathVariable int centerCode, @RequestParam(name="year") int checkYear, @RequestParam(name="season") int checkSeason){
		return statService.getCenterSubStat(centerCode, checkYear, checkSeason);
	}
	
	@RequestMapping("/managerSubStat/{userCode}")
	public @ResponseBody Map<String, String> getManagerSubStat(@PathVariable int userCode, @RequestParam(name="year") int checkYear, @RequestParam(name="season") int checkSeason){
		return statService.getManagerSubStat(userCode, checkYear, checkSeason);
	}
	
	@RequestMapping("/codeSubStat/{userCode}")
	public @ResponseBody Map<String, String> getCodeSubStat(@PathVariable int userCode, @RequestParam(name="year") int checkYear, @RequestParam(name="season") int checkSeason){
		return statService.getCodeSubStat(userCode, checkYear, checkSeason);
	}
}
