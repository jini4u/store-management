package com.mycompany.webapp.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.manager.service.IManagerService;
import com.mycompany.webapp.manager.vo.ManagerVO;

@Controller
public class ManagerController {
   static final Logger logger=LoggerFactory.getLogger(ManagerController.class);
   
   //은별
   @Autowired 
   IManagerService managerService;
   
   //담당자 목록 조회
   @RequestMapping(value="/managerList")
   public String selectManagerList(Model model, ManagerVO mgr) {
      List<ManagerVO> managerList = managerService.selectManagerList();
      logger.info("managerList : " + managerList);
      model.addAttribute("managerList", managerList);
      return "jsp/manager/managerlookup";
   }
   
   //담당자 상세 조회
   @RequestMapping(value="/managerDetail")
   public String selectManagerDetail(Model model, @PathVariable int userCode) {
      ManagerVO mgrDetails = managerService.selectManagerDetail(userCode);
      model.addAttribute("managerVO",mgrDetails);
      return "jsp/manager/managerdetail";
   }
   
   //담당자 등록 GET
   @GetMapping(value="/managerInsert")
   public String insertManager(Model model) {
      return "jsp/manager/managerlookup";
   }
   
   //담당자 등록 POST
   @ResponseBody
   @PostMapping(value="/managerInsert")
   public List<ManagerVO> insertManager(ManagerVO mgr) {
      managerService.insertManager(mgr);
      List<ManagerVO> mgrlist = managerService.selectManagerList();
      return mgrlist;
   }
   
   //담당자 매핑
   @RequestMapping(value="/managerMapping")
   public String managerMapping() {
      return "jsp/manager/managermapping";
   }
}