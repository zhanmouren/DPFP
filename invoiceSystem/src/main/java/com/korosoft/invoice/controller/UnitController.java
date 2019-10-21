package com.korosoft.invoice.controller;



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

import com.korosoft.invoice.authority.annotation.StaffAttribute;
import com.korosoft.invoice.bean.LoginBean;
import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;
import com.korosoft.invoice.service.Contans;
import com.korosoft.invoice.service.UnitService;
import com.korosoft.invoice.vo.ResponseData;




@Controller
@RequestMapping("/unit")
public class UnitController {

	@Autowired 
	private UnitService unitservice;
    
	@ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    private ResponseData listAll(@RequestBody Map<String,String> map,HttpSession session,HttpServletRequest request, @StaffAttribute(Contans.USER) LoginBean user) {
		request.setAttribute(Contans.CACHE_NAME, user.getLoginName());
    	if(map.get("queryInfo").length()>0) {
    		List<UnitDataBean> list = unitservice.findUnitByName(map.get("queryInfo"));
    		for(UnitDataBean bean:list){
    			//System.out.println(goods.getId()+":" +goods.getName()+":"+DateUtil.formatDate(goods.getUptime(),DateUtil.SHORT));
    			System.out.println(bean.getCtm_name()+"==="
    					+bean.getB_id()+"==="+bean.getTotal());
    		}
    		return ResponseData.success(list, "单位资料查询成功");
    	}else {
    		System.out.println("查询全部！");
    		List<UnitDataBean> list = unitservice.findAllUnit();
    		return ResponseData.success(list, "单位资料查询成功");
    	}  	
    	
    }
	
	@ResponseBody
    @RequestMapping(value = "/findbyid", method = RequestMethod.POST)
    private ResponseData findId(@RequestBody UnitDataBean form,HttpSession session,HttpServletRequest request) {
    	if(form.getB_id() !=null||form.getB_id()>0) {
    		UnitDataBean data = unitservice.findById(form.getB_id());
    		return ResponseData.success(data, "单位信息查询成功");
    	}else {   		
    		return ResponseData.faill("单位id不能为空！");
    	}  	
    	
    }
	
	@ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private ResponseData deleteUnit(@RequestBody Map<String,int[]> map,HttpSession session,HttpServletRequest request) {
		System.out.println(map.get("b_id")[0]);
    	if(map.get("b_id") ==null||map.get("b_id").length<1) {
    		return ResponseData.faill("id不能为空！");   		
    	}  	
    	return unitservice.deleteUnit(map.get("b_id"));
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ResponseData AddUnit(@RequestBody UnitDataBean form,HttpSession session,HttpServletRequest request) {
    	if(form.getCtm_name() ==null||form.getCtm_name()=="") {
    		return ResponseData.faill("单位名称不能为空！");
    	}
    	if(form.getCtm_addr() ==null||form.getCtm_addr()=="") {
    		return ResponseData.faill("单位地址不能为空！");
    	}
    	if(form.getTax_num() ==null||form.getTax_num()=="") {
    		return ResponseData.faill("税号不能为空！");
    	}
    	if(form.getLink_tel() ==null||form.getLink_tel()=="") {
    		return ResponseData.faill("电话不能为空！");
    	}
    	if(form.getBank_name() ==null||form.getBank_name()=="") {
    		return ResponseData.faill("银行名称不能为空！");
    	}
    	if(form.getBank_num() ==null||form.getBank_num()=="") {
    		return ResponseData.faill("银行帐号不能为空！");
    	}    	
    	return unitservice.UnitAdd(form);
    }
	
	@ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private ResponseData updateUnit(@RequestBody UnitDataBean form,HttpSession session,HttpServletRequest request) {
		System.out.println("ID"+form.getB_id());
    	if(form.getCtm_name() ==null||form.getCtm_name()=="") {
    		return ResponseData.faill("单位名称不能为空！");
    	}
    	if(form.getCtm_addr() ==null||form.getCtm_addr()=="") {
    		return ResponseData.faill("单位地址不能为空！");
    	}
    	if(form.getTax_num() ==null||form.getTax_num()=="") {
    		return ResponseData.faill("税号不能为空！");
    	}
    	if(form.getLink_tel() ==null||form.getLink_tel()=="") {
    		return ResponseData.faill("电话不能为空！");
    	}
    	if(form.getBank_name() ==null||form.getBank_name()=="") {
    		return ResponseData.faill("银行名称不能为空！");
    	}
    	if(form.getBank_num() ==null||form.getBank_num()=="") {
    		return ResponseData.faill("银行帐号不能为空！");
    	}    	
    	return unitservice.updateUnit(form);
    }
	
	@ResponseBody
    @RequestMapping(value = "user/findUserByid", method = RequestMethod.POST)
    private ResponseData UnitUserlistAll(@RequestBody UnitUserBean bean,HttpSession session,HttpServletRequest request) {
		if(bean.getUnit_id()<1){
			return ResponseData.faill("没有绑定用户，请先添加!");
		}else {
			 List<UnitUserBean> list = unitservice.findUnitUserById(bean.getUnit_id());
	    	return ResponseData.success(list, "单位用户查询成功"); 
		}
		    	 	
    }
	
	@ResponseBody
    @RequestMapping(value = "user/findCtmNum", method = RequestMethod.POST)
    private ResponseData findUserByCtmNum(@RequestBody Map<String,String> map,HttpSession session,HttpServletRequest request) {
		if(map.get("ctmnum")==null||map.get("ctmnum")==""){
			return ResponseData.faill("户号不能为空，请检查!");
		}else {
			 List<UnitUserBean> list = unitservice.findUnitUserByCtmNum(map.get("ctmnum"));
			 if(list==null||list.size()==0) {
				 return ResponseData.faill("没有找到该户号，请检查!");
			 }else {
				 return ResponseData.success(list, "用户查询成功"); 
			 }
				 	    	
		}
		    	 	
    }
	
	@ResponseBody
    @RequestMapping(value = "user/addUser", method = RequestMethod.POST)
    private ResponseData addUser(@RequestBody Map<String,String> map,HttpSession session,HttpServletRequest request) {
		if(map.get("ctmnum")==null||map.get("ctmnum")==""){
			return ResponseData.faill("户号不能为空，请检查!");
		}
		int unit_id = Integer.parseInt(map.get("unit_id"));
		if(unit_id<1){
			return ResponseData.faill("单位号码不能为空，请检查!");
		}
		return  unitservice.addUser(unit_id,map.get("ctmnum"));
				 	    					    	 	
    }
    
	@ResponseBody
    @RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
    private ResponseData updateUser(@RequestBody Map<String,int[]> map,HttpSession session,HttpServletRequest request) {
		System.out.println(map.get("ctmnum")[0]);
    	if(map.get("ctmnum") ==null||map.get("ctmnum").length<1) {
    		return ResponseData.faill("用户id不能为空！");   		
    	}  	
    	return unitservice.updateUser(map.get("ctmnum"));
    }
	
	@ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    private ResponseData ImportTxt(@RequestBody Map<String,String> map,HttpSession session,HttpServletRequest request) {
		 String filePath = "D:\\客户编码.txt";
 		return unitservice.ImportUnit(filePath); 
    	
    }
    
   
    
   
}