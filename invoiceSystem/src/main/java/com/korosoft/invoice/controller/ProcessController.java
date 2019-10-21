package com.korosoft.invoice.controller;



import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.korosoft.invoice.bean.ProcessBean;
import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;
import com.korosoft.invoice.service.ProcessService;
import com.korosoft.invoice.service.UnitService;
import com.korosoft.invoice.vo.ResponseData;




@Controller
@RequestMapping("/unit/process")
public class ProcessController {

	@Autowired 
	private ProcessService Processservice;
    
	@ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    private ResponseData findAll(@RequestBody Map<String,String> map,HttpServletRequest request) {
		/*
		 * if(map.get("loginName")==null||map.get("loginName")=="") { return
		 * ResponseData.faill("登录名称不能为空！"); }
		 */
		if(map.get("select_state")==null||map.get("select_state")=="") {
			return ResponseData.faill("state参数不能为空！");
		}
		if(map.get("ctmnum")==null||map.get("ctmnum")=="") {
			List<ProcessBean> list =Processservice.findAll(map.get("select_state"));
			return ResponseData.success(list, "审核列表查询成功!");
			
		}else {
			List<ProcessBean> list =Processservice.findAllByCtmNum( map.get("select_state"), map.get("ctmnum"));
			return ResponseData.success(list, "审核列表查询成功!");
		}
		
    	
    }
	
	
    
   
    
   
}