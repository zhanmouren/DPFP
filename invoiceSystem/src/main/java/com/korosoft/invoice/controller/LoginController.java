package com.korosoft.invoice.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.korosoft.invoice.bean.LoginBean;
import com.korosoft.invoice.config.WebMvcConfig;
import com.korosoft.invoice.service.Contans;
import com.korosoft.invoice.service.LoginService;
import com.korosoft.invoice.util.JwtUtils;
import com.korosoft.invoice.vo.ResponseData;
import com.korosoft.invoice.config.MyLog;

@RestController
public class LoginController {

		@Autowired
		private LoginService loginservice;		
	
		 @Autowired
		 HttpServletRequest request;
		    
		 @Autowired
		 HttpServletResponse response;
		 
		    
	
	 @RequestMapping("/") 
	public String index(@SessionAttribute(WebMvcConfig.SESSION_KEY) String account,Model model){
	 
	  model.addAttribute(account); 
	  return "login.jsp"; 
	  
	}
	 

		@MyLog("登陆")
		@RequestMapping("/login.api")
	    public ResponseData login(@RequestBody LoginBean info,HttpSession session){			
	    	System.out.println("登录名"+info.getLoginName()+"密码"+info.getPassword());
	    	if(info.getLoginName()==null||info.getLoginName()=="") {
	    		System.out.println("登录名不能为空");
	    		session.setAttribute("message","登录名或密码错误，请重新输入!");
	    		return ResponseData.faill("用户名或密码不能为空！");
	    	}
	    	if(info.getPassword()==null||info.getLoginName()=="") {
	    		System.out.println("密码不能为空");
	    		return ResponseData.faill("用户名或密码不能为空！");
	    	}
	    	LoginBean user=loginservice.login(info);
	        if(user!=null){
	        	System.out.println("登录成功！"+user.getUsername());
	        	session.setAttribute("user", user);
	        	session.setAttribute("loginName", user.getUsername());
	        	 String token = JwtUtils.createJWT(Contans.EXPIRED_MINUTE, user);
	        	 user.setToken(token);
	        	 return ResponseData.success(user,"登录成功");
	        }else {
	        	System.out.println("登录失败！"+user);
	        	request.setAttribute("message","登录名或密码错误，请重新输入!");
	        	return ResponseData.faill("用户名或密码错误，请检查！");
	        }	       
	    }
		
		
	    @RequestMapping("/logout")
	    public ResponseData logout() {
	        HttpSession session = request.getSession();
	        session.removeAttribute("user");
	        return ResponseData.success("退出成功！");
	    }


	
}


