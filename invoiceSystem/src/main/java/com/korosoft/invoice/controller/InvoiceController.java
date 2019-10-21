package com.korosoft.invoice.controller;




import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.korosoft.invoice.authority.annotation.LoginToken;
import com.korosoft.invoice.authority.annotation.StaffAttribute;
import com.korosoft.invoice.bean.DateYMDBean;
import com.korosoft.invoice.bean.ExtendInfoBean;
import com.korosoft.invoice.bean.FinalUserMsgBean;
import com.korosoft.invoice.bean.HistoryBillBean;
import com.korosoft.invoice.bean.IndexReportBean;
import com.korosoft.invoice.bean.InvoiceDetailListBean;
import com.korosoft.invoice.bean.InvoiceFormBean;
import com.korosoft.invoice.bean.InvoiceLogBean;
import com.korosoft.invoice.bean.InvoiceRecordBean;
import com.korosoft.invoice.bean.InvoiceRedBean;
import com.korosoft.invoice.bean.InvoiceResultDataBean;
import com.korosoft.invoice.bean.InvoiceSalesBean;
import com.korosoft.invoice.bean.InvoiceSalesDetBean;
import com.korosoft.invoice.bean.InvoiceStatusBean;
import com.korosoft.invoice.bean.JbaseBean;
import com.korosoft.invoice.bean.LoginBean;
import com.korosoft.invoice.bean.UserMsgResultBean;
import com.korosoft.invoice.bean.UserMsgfpResultBean;
import com.korosoft.invoice.bean.downURLBean;
import com.korosoft.invoice.config.MyLog;
import com.korosoft.invoice.dto.dzfpConfigDto;
import com.korosoft.invoice.service.Contans;
import com.korosoft.invoice.service.InvoiceService;
import com.korosoft.invoice.util.AesUtil;
import com.korosoft.invoice.util.HttpRequest;
import com.korosoft.invoice.vo.ResponseData;
import com.korosoft.invoice.bean.ResultData;
import com.korosoft.invoice.bean.SpecialFormBean;
import com.korosoft.invoice.bean.SpecialInvoiceBean;
import com.korosoft.invoice.bean.SpecialInvoiceDetBean;
import com.korosoft.invoice.bean.UnitDataBean;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
 




@RestController
public class InvoiceController {   
	@Autowired
	private InvoiceService invoiceService;
	
	
	@Value("${app.etcloud.openInvoiceUrl}")
	protected String openInvoiceUrl;
	
	@Value("${app.etcloud.openSpecialInvoiceUrl}")
	protected String openSpecialInvoiceUrl;
	
	@Value("${app.etcloud.cancelSpecialInvoiceUrl}")
	protected String cancelSpecialInvoiceUrl;
	
	@Value("${app.etcloud.openUrlPeople}")
	protected String openUrlPeople;
	
	
    
    /*
     * date:2019-09-16
     * funtion:用户查询基本信息和发票信息（根据时间段查询）专票和普票
     * author:xiaozhan
     * param:户号，年月
     */  
    @LoginToken
    @MyLog("根据时间段查询用户查询基本信息和发票信息")
    @ResponseBody
    @RequestMapping(value = "/selectUserMsg.api", method = RequestMethod.POST)
    public ResponseData selectUserMsg(@RequestBody JbaseBean form,HttpSession session,HttpServletRequest request,@StaffAttribute(Contans.USER) LoginBean user) throws Exception{
    	request.setAttribute(Contans.CACHE_NAME, user.getLoginName());	
    	Object loginNameObj= session.getAttribute("loginName");
    	String loginName="";
    	if(loginNameObj!=null) {
    		loginName=loginNameObj.toString();	
    	} 
    	 if( loginNameObj==null ) {
     		return ResponseData.faill(1001, "会话已过期,请重新登录！");
     	 }
        	if(form.getHno()==null) {
	        	return ResponseData.faill("户号不能为空");
	        }
	        if(form.getStartTime()==null) {
	        	return ResponseData.faill("开始时间不能为空");
	        }
	        if(form.getEndTime()==null) {
	        	return ResponseData.faill("结束时间不能为空");
	        }
	        if(form.getInvoiceType()==null) {
	        	return ResponseData.faill("发票类型不能为空");
	        }
	        if(form.getPage()==null) {
	        	return ResponseData.faill("当前页数不能为空");
	        }
	        if(form.getPageCount()==null) {
	        	return ResponseData.faill("每页条数不能为空");
	        }
	        //先根据hno查询jbase表中的kpfg  F都不能，P电子，Z专用
//	        List<JbaseBean> kpfgList=invoiceService.getuserkpfg(form.getHno());	     
//	        if(kpfgList.size()>0 && kpfgList.get(0).getKpfg().equals("F")) {
//	        	 return ResponseData.faill("该用户没有录入开票资料，请先录入后再开票！");
//	        }
//	        if(kpfgList.size()>0 && kpfgList.get(0).getKpfg().equals("P") && form.getInvoiceType().equals("0")) {
//	        	 return ResponseData.faill("该用户为电子普票用户，请前往电子票页面进行开票！");
//	        }
//	        if(kpfgList.size()>0 && kpfgList.get(0).getKpfg().equals("Z") && form.getInvoiceType().equals("2")){
//	        	 return ResponseData.faill("该用户为专票用户，请前往专用发票页面进行开票！");
//	        }
//	        //如果从基本用户信息表中没查到用户信息
//	         if(kpfgList.size()<=0) {	        	 
//	        	 //从大中客户信息表中查询
//	            List<JbaseBean> DKkpfgList=invoiceService.getuserkpfg(form.getHno());
//	            if(DKkpfgList.size()>0 && DKkpfgList.get(0).getKpfg().equals("F")) {
//		        	 return ResponseData.faill("该用户没有录入开票资料，请先录入后再开票！");
//		        }
//		        if(DKkpfgList.size()>0 && DKkpfgList.get(0).getKpfg().equals("P") && form.getInvoiceType().equals("0")) {
//		        	 return ResponseData.faill("该用户为电子普票用户，请前往电子票页面进行开票！");
//		        }
//		        if(DKkpfgList.size()>0 && DKkpfgList.get(0).getKpfg().equals("Z") && form.getInvoiceType().equals("2")){
//		        	 return ResponseData.faill("该用户为专票用户，请前往专用发票页面进行开票！");
//		        }else {
//		        	 return ResponseData.faill("未查到该用户信息");
//		        }
//	         }
	        
    		if(form.getHno()!=null && form.getStartTime()!=null && form.getEndTime()!=null) {
              //根据户号查询用户的基本信息(sql server)   		
    		  List<UserMsgResultBean> UserMsgResultBeanList=invoiceService.getusermsgbyno(form);
    		  List<UserMsgfpResultBean> UserMsgfpResultBeanList=new ArrayList<UserMsgfpResultBean>();
    		 //根据开始的年月和结束的年月户号查询用户的普票基本信息(mysql)
    		  if(form.getInvoiceType().equals("2")) {
    		     UserMsgfpResultBeanList=invoiceService.getuserfpmsgbyno(form);
    		  }else {
    			  //根据开始的年月和结束的年月户号查询用户的专票基本信息(mysql)
    			  UserMsgfpResultBeanList= invoiceService.getuserzpmsgbyno(form);
    		  }
    		  //定义一个变量判断是普通用户还是大中客户(普通用户为0,大中用户为1)
    		   int userFlag=0;  //默认为普通用户
    		   		  
    		  //新建一个空的List集合接收它  		  
    		  List<FinalUserMsgBean> FinalUserMsgBeanList=new  ArrayList<FinalUserMsgBean>();
    		  
    		  if(UserMsgResultBeanList.size()<=0) {   			  
    			  //开始从大中用户信息表中查询用户的基本信息
    			  UserMsgResultBeanList=invoiceService.getDZusermsgbyno(form);
    			  if(UserMsgResultBeanList.size()<=0) {
    				  return ResponseData.faill("该用户在这个区间不存在账单信息");
    			  }else {
    				  //标记为大中用户
    				  userFlag=1;
    			  }
    			 
    		  }
    		  
    		   if(UserMsgResultBeanList.size()<=0  &&UserMsgfpResultBeanList.size()<=0) {
    			  return ResponseData.faill("不存在该用户任何信息");
    		  }else{
    			 
    			  //只存在用户信息，不存在发票信息的话
    			  if(UserMsgResultBeanList.size()>0 && UserMsgfpResultBeanList.size()<=0){
    				//  FinalUserMsgBean finalUserMsgBean=new FinalUserMsgBean();
    				  for(int i=0;i<UserMsgResultBeanList.size();i++) { 
    					  FinalUserMsgBean finalUserMsgBean=new FinalUserMsgBean();
    					  //用户的基本信息
    					  finalUserMsgBean.setHno(UserMsgResultBeanList.get(i).getHno());
    					  finalUserMsgBean.setName(UserMsgResultBeanList.get(i).getName());
    					  finalUserMsgBean.setPhone(UserMsgResultBeanList.get(i).getPhone());
    					  finalUserMsgBean.setNewaddr(UserMsgResultBeanList.get(i).getNewaddr());
    					  finalUserMsgBean.setMoblie(UserMsgResultBeanList.get(i).getMoblie());
    					  if(UserMsgResultBeanList.get(i).getCdate()!=null) {
    					   finalUserMsgBean.setCdate(UserMsgResultBeanList.get(i).getCdate().substring(0, 10));  
    					  }
    					  
    					  finalUserMsgBean.setPrice(UserMsgResultBeanList.get(i).getPrice());
    					  finalUserMsgBean.setCnj(UserMsgResultBeanList.get(i).getCnj());
    					  finalUserMsgBean.setPwf(UserMsgResultBeanList.get(i).getPwf());
    					  finalUserMsgBean.setLjprice(UserMsgResultBeanList.get(i).getLjprice());  					 
    					  finalUserMsgBean.setQuanty(UserMsgResultBeanList.get(i).getQuanty());
    					  if(userFlag==0) {
    						  finalUserMsgBean.setUprice(UserMsgResultBeanList.get(i).getUprice());
        					  finalUserMsgBean.setPriceo(UserMsgResultBeanList.get(i).getPriceo());   					  
        					  finalUserMsgBean.setQuantyo(UserMsgResultBeanList.get(i).getQuantyo());
        					  finalUserMsgBean.setQuantyoo(UserMsgResultBeanList.get(i).getQuantyoo());
        					  finalUserMsgBean.setPriceoo(UserMsgResultBeanList.get(i).getPriceoo());
    					  }
    					 
    					  
    					  finalUserMsgBean.setDzfp_state(0);
    					  finalUserMsgBean.setZp_state(0);
    					  FinalUserMsgBeanList.add(finalUserMsgBean);
    				  }    				  
    				 
    			  }
    			  //存在用户信息,也存在发票信息，主要是为了查询发票信息
    			if(UserMsgResultBeanList.size()>0 && UserMsgfpResultBeanList.size()>0){
    				for(int i=0;i<UserMsgResultBeanList.size();i++) { 
    			//定义一个标志位，这个作为如果发票不存在不存在的话，更新标志位,true代表的是开过票
    		 		boolean FPFlag=false;
    		 			for(int j=0;j<UserMsgfpResultBeanList.size();j++) {	  				       			         			    	  
        		        //存在的话，说明已经开过票了，就是开票信息存在    			    	  
        				  if(UserMsgResultBeanList.get(i).getHno().equals(UserMsgfpResultBeanList.get(j).getCtm_num()) && UserMsgResultBeanList.get(i).getCdate().substring(0, 10).equals(UserMsgfpResultBeanList.get(j).getMonth_id().substring(0, 10))) {        					
        					  FPFlag=true;
        					  FinalUserMsgBean finalUserMsgBeannew=new FinalUserMsgBean(); 
            				  //用户的基本信息
            				  finalUserMsgBeannew.setHno(UserMsgResultBeanList.get(i).getHno());
            				  finalUserMsgBeannew.setName(UserMsgResultBeanList.get(i).getName());
            				  finalUserMsgBeannew.setPhone(UserMsgResultBeanList.get(i).getPhone());
            				  finalUserMsgBeannew.setNewaddr(UserMsgResultBeanList.get(i).getNewaddr());
            				  finalUserMsgBeannew.setMoblie(UserMsgResultBeanList.get(i).getMoblie());
        					  if(UserMsgResultBeanList.get(i).getCdate()!=null) {
        						  finalUserMsgBeannew.setCdate(UserMsgResultBeanList.get(i).getCdate().substring(0,10));  
        					  }
            				  
            				  finalUserMsgBeannew.setPrice(UserMsgResultBeanList.get(i).getPrice());
            				  finalUserMsgBeannew.setCnj(UserMsgResultBeanList.get(i).getCnj());
            				  finalUserMsgBeannew.setPwf(UserMsgResultBeanList.get(i).getPwf());
            				  finalUserMsgBeannew.setLjprice(UserMsgResultBeanList.get(i).getLjprice());
            				  finalUserMsgBeannew.setQuanty(UserMsgResultBeanList.get(i).getQuanty());
            				  
            				  if(userFlag==0) {
            				  finalUserMsgBeannew.setUprice(UserMsgResultBeanList.get(i).getUprice());
            				  finalUserMsgBeannew.setPriceo(UserMsgResultBeanList.get(i).getPriceo());
            				  finalUserMsgBeannew.setPriceoo(UserMsgResultBeanList.get(i).getPriceoo());
            				  finalUserMsgBeannew.setQuantyo(UserMsgResultBeanList.get(i).getQuantyo());
            				  finalUserMsgBeannew.setQuantyoo(UserMsgResultBeanList.get(i).getQuantyoo());
            				  
            				  }
        					  //用户的开票信息
        					  finalUserMsgBeannew.setCtm_name(UserMsgfpResultBeanList.get(j).getCtm_name());
        					  if(UserMsgfpResultBeanList.get(j).getMonth_id()!=null) {
        						  finalUserMsgBeannew.setMonth_id(UserMsgfpResultBeanList.get(j).getMonth_id().substring(0, 10)); 
        					  }
        					 
        					  finalUserMsgBeannew.setInvoice_num(UserMsgfpResultBeanList.get(j).getInvoice_num());
        					  finalUserMsgBeannew.setInvoice_code(UserMsgfpResultBeanList.get(j).getInvoice_code());
        					  finalUserMsgBeannew.setInvoice_url(UserMsgfpResultBeanList.get(j).getInvoice_url());
        					  finalUserMsgBeannew.setCode_num(UserMsgfpResultBeanList.get(j).getCode_num());
        					  finalUserMsgBeannew.setBatch_num(UserMsgfpResultBeanList.get(j).getBatch_num());
        					  finalUserMsgBeannew.setKp_man(UserMsgfpResultBeanList.get(j).getKp_man());
        					  finalUserMsgBeannew.setKp_time(UserMsgfpResultBeanList.get(j).getKp_time());
        					  finalUserMsgBeannew.setKp_price(UserMsgfpResultBeanList.get(j).getKp_price());
        					
        					  if(UserMsgfpResultBeanList.get(j).getDzfp_state()==null) {
        					    finalUserMsgBeannew.setDzfp_state(0);
        					  }else {
        					   finalUserMsgBeannew.setDzfp_state(UserMsgfpResultBeanList.get(j).getDzfp_state());
        					  }
        					  if(UserMsgfpResultBeanList.get(j).getZp_state()==null) {
        					     finalUserMsgBeannew.setZp_state(0);
        					  }else {
        					   finalUserMsgBeannew.setZp_state(UserMsgfpResultBeanList.get(j).getZp_state());
        					  }
       					  
        					  FinalUserMsgBeanList.add(finalUserMsgBeannew);
        					break;
        				  }
        			  //不存在的话，说明还没开过票	
        				 else {
        					 FPFlag=false;       					 
        				  }
        			  }
    		 		   //如果这个缴费账单不存在发票的信息
    			      if(FPFlag==false) {
    			    	  FinalUserMsgBean finalUserMsgBeannew=new FinalUserMsgBean(); 
           				  //用户的基本信息
           				  finalUserMsgBeannew.setHno(UserMsgResultBeanList.get(i).getHno());
           				  finalUserMsgBeannew.setName(UserMsgResultBeanList.get(i).getName());
           				  finalUserMsgBeannew.setPhone(UserMsgResultBeanList.get(i).getPhone());
           				  finalUserMsgBeannew.setNewaddr(UserMsgResultBeanList.get(i).getNewaddr());
           				  finalUserMsgBeannew.setMoblie(UserMsgResultBeanList.get(i).getMoblie());
       					  if(UserMsgResultBeanList.get(i).getCdate()!=null) {
       						finalUserMsgBeannew.setCdate(UserMsgResultBeanList.get(i).getCdate().substring(0, 10));  
       					  }
           				  
           				  finalUserMsgBeannew.setPrice(UserMsgResultBeanList.get(i).getPrice());
           				  finalUserMsgBeannew.setCnj(UserMsgResultBeanList.get(i).getCnj());
           				  finalUserMsgBeannew.setPwf(UserMsgResultBeanList.get(i).getPwf());
           				  finalUserMsgBeannew.setLjprice(UserMsgResultBeanList.get(i).getLjprice());
           				  finalUserMsgBeannew.setQuanty(UserMsgResultBeanList.get(i).getQuanty());
           				  if(userFlag==0) {
           				  finalUserMsgBeannew.setUprice(UserMsgResultBeanList.get(i).getUprice());
           				  finalUserMsgBeannew.setPriceo(UserMsgResultBeanList.get(i).getPriceo());
           				  finalUserMsgBeannew.setPriceoo(UserMsgResultBeanList.get(i).getPriceoo());
           				  finalUserMsgBeannew.setQuantyo(UserMsgResultBeanList.get(i).getQuantyo());
           				  finalUserMsgBeannew.setQuantyoo(UserMsgResultBeanList.get(i).getQuantyoo());
           				  
           				  }
           				  
    					  finalUserMsgBeannew.setDzfp_state(0);
   					      finalUserMsgBeannew.setZp_state(0);
   					      FinalUserMsgBeanList.add(finalUserMsgBeannew);
    			      }
    		     }
    		   }
    		  
    		  }
    		  Sort(FinalUserMsgBeanList, "getCdate",  "desc");//正序就填null
    		  //根据当前页数和每页条数取ArrayList的数值
    		    //获取下标
    		  int startIndex=(form.getPage()-1)*form.getPageCount();
    		  int listsize=FinalUserMsgBeanList.size();
    		  //新建一个空的List集合接收它  		  
    		  List<FinalUserMsgBean> FinalUserMsgBeanListNew=new  ArrayList<FinalUserMsgBean>();
    		  if(Math.floor(listsize/form.getPageCount())>=form.getPage()) {
    			  listsize=startIndex+form.getPageCount();
    		  }
    		  for(int n=startIndex;n<listsize;n++) {
    			  FinalUserMsgBeanListNew.add(FinalUserMsgBeanList.get(n));
    		  }
    		  return ResponseData.success(0,FinalUserMsgBeanList.size(),FinalUserMsgBeanListNew, "成功请求数据");
    		}else {
    			return ResponseData.faill("参数为空");
    		}
			  		
    }
   
    /*
     * date:2019-09-17
     * funtion:开票,主要封装数据给营收系统,未加密前的数据
     * author:xiaozhan
     * param:ctm_num 户号 month_id 开始年月  dzfp_type 标识
     */
    @LoginToken
    @MyLog("普票开票，红冲")
    @ResponseBody
    @RequestMapping(value = "/openInvoice.api", method = RequestMethod.POST)
    public ResponseData openInvoice(@RequestBody JbaseBean form,HttpSession session,HttpServletRequest request,@StaffAttribute(Contans.USER) LoginBean user) throws Exception{
    	request.setAttribute(Contans.CACHE_NAME, user.getLoginName());	
    	Object loginNameObj= session.getAttribute("loginName");
    	String loginName="";
    	if(loginNameObj!=null) {
    		loginName=loginNameObj.toString();	
    	} 
    	 if( loginNameObj==null ) {
     		return ResponseData.faill(1001, "会话已过期,请重新登录！");
     	 }
        Object userObj= session.getAttribute("user");
    	if(form.getHno()==null || form.getHno()=="") {
        	return ResponseData.faill("户号不能为空");
        }
        if(form.getMonth_id()==null || form.getMonth_id()=="") {
        	return ResponseData.faill("年月不能为空");
        }
        if(form.getInvoiceNature()==null ||form.getInvoiceNature()=="") {
        	return ResponseData.faill("开票性质不能为空");
        } 
       // session.getAttribute("loginName"); 
        //String转date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date monthDate = df.parse(form.getMonth_id());
        form.setMonthDate(monthDate);
              
        
       //检查发票表中是否存在该用户的发票信息
        List<UserMsgfpResultBean> UserMsgfpResultBeanList =invoiceService.getUserFPInfobyno(form);
        //说明已经开过票
        if(UserMsgfpResultBeanList.size()>0 && UserMsgfpResultBeanList.get(0).getDzfp_state()==1 && form.getInvoiceNature().equals("0")) {
        	 return ResponseData.faill("这个账单已经开过票，请先红冲后，再次开票");
        }
        //红冲的时候检查有没有开过票
        if(UserMsgfpResultBeanList.size()<=0 && form.getInvoiceNature().equals("1")) {
       	  return ResponseData.faill("还未开过票，开票才可以红冲");
       }
        //开票红冲过程
        else {      
        //配置文件信息
        dzfpConfigDto dzfp = new dzfpConfigDto();
		Properties prop = new Properties();
		prop.load(new InputStreamReader(TaskScheduler.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8"));
		
		 dzfp.setSL(prop.getProperty("dzfp.SL").trim());	
		 dzfp.setGroup(prop.getProperty("dzfp.GROUP").trim());
		 dzfp.setGSBM(prop.getProperty("dzfp.GSBM").trim());
		 dzfp.setChannel(prop.getProperty("dzfp.Channel").trim());
		 dzfp.setDiskCode(prop.getProperty("dzfp.DiskCode").trim());
		 dzfp.setOrgCode(prop.getProperty("dzfp.OrgCode").trim());
		 dzfp.setUkeyCode(prop.getProperty("dzfp.UkeyCode").trim());
		 dzfp.setSkr(prop.getProperty("dzfp.skr").trim());
		 dzfp.setReviewer(prop.getProperty("dzfp.Reviewer").trim());
		 dzfp.setOpenPeople(prop.getProperty("dzfp.openPeople").trim());
		
		 //原销售单批次
		 String oldBillBatch=new String();
          //原销售单号
		 String oldBillCode=new String();
		 
		 
		 //开发票单的单据批次号
		 String billBatchNo=new String();
		 //开发票单的销售单号
		 String fpbillCode=new String();
		 
		 //如果是开票,生成销售单号billCode,规则是每天从0000001开始
		 
			 String todaynow=getNow();
			 String todayymd=getYMD();
			 DateYMDBean dateYMDBean=new DateYMDBean();
			 dateYMDBean.setTodayymd(todayymd);
			 //拿出当天中最大的单号
			  fpbillCode=invoiceService.getMaxBillCode(dateYMDBean);
			
			 //如果单号不存在的话
			 if("".equals(fpbillCode) || fpbillCode==null) {
				//则设置每天的第一个单号
				 fpbillCode="FP"+todaynow+"0000001";
				 billBatchNo=todayymd+"0000001";
			 }else {
				 String billCode=getDigitalToStr(fpbillCode);
				 fpbillCode="FP"+todaynow+billCode;
				 billBatchNo=todayymd+billCode;
			 }
			 
	   //红冲的话，获取到 原销售单批次oldBillBatch和 旧的销售单号oldBillCode	 
		 if(form.getInvoiceNature().equals("1")) {			 			
			 oldBillBatch=UserMsgfpResultBeanList.get(0).getBatch_num();
			 oldBillCode=UserMsgfpResultBeanList.get(0).getCode_num();					
		  }		
		//负数，红冲的时候要用到
		 int iFlag=-1;
		    
        //创建一个空的销售主单
        InvoiceSalesBean invoiceSalesBean=new InvoiceSalesBean();
        
        //商品bean
        InvoiceSalesDetBean invoiceSalesDetBean=new InvoiceSalesDetBean();
        
        //垃圾费bean
        InvoiceSalesDetBean invoiceSalesljDetBean=new InvoiceSalesDetBean();
        //污水费bean
        InvoiceSalesDetBean invoiceSaleswsDetBean=new InvoiceSalesDetBean();
        
        //创建一个空的销售明细form集合接收
        List<InvoiceSalesDetBean> invoiceSalesBeanDetList=new ArrayList<InvoiceSalesDetBean>();
        if(form.getHno()!=null && form.getMonth_id()!=null ) {
        	//查询用户基本数据(根据户号和当前年月查询)普通
        //	List<UserMsgResultBean> userMsgResultBeanList=new ArrayList<UserMsgResultBean>();
        	List<UserMsgResultBean> userMsgResultBeanList=invoiceService.getUserInfobyno(form);
        	
        	int userFlag=0;//0普通1大中
        	if(userMsgResultBeanList.size()<=0) {
        		//查询大中客户的基本信息(大中)
        		userMsgResultBeanList=invoiceService.getDZUserInfobyno(form);
        		if(userMsgResultBeanList.size()>0) {
        			userFlag=1;
        		}
        	}      	
        	
        	if(userMsgResultBeanList.size()>0) {
        		//封装数据(封装成json的格式)
	        		//添加销售单主表
	    			invoiceSalesBean.setBillBatchNo(billBatchNo);
	    			invoiceSalesBean.setBillCode(fpbillCode);
	    			invoiceSalesBean.setBillDate(getNowDate());
	    			invoiceSalesBean.setCreditType("3");
	    			//如果是开票
	    			if(form.getInvoiceNature().equals("0")) {
	    				invoiceSalesBean.setInvoiceNature("0");
	    			}else {
	    				//红冲
	    				invoiceSalesBean.setInvoiceNature("1");
	    				invoiceSalesBean.setOldBillBatch(oldBillBatch);
	    				invoiceSalesBean.setOldBillCode(oldBillCode);
	    			}	    			
	    			invoiceSalesBean.setInvoiceAuto("0");
	    			invoiceSalesBean.setInvoiceType("51");
	    			invoiceSalesBean.setCustName(userMsgResultBeanList.get(0).getName());
	    		    invoiceSalesBean.setCustCreditCode(userMsgResultBeanList.get(0).getNsid());	    			
	    			invoiceSalesBean.setCustAddress(userMsgResultBeanList.get(0).getNewaddr());
	    			invoiceSalesBean.setCustPhone(userMsgResultBeanList.get(0).getPhone());
	    			invoiceSalesBean.setDiskCode(dzfp.getDiskCode());
	    			invoiceSalesBean.setUkeyCode(dzfp.getUkeyCode());
	    			invoiceSalesBean.setReviewer(dzfp.getReviewer());
	    			invoiceSalesBean.setPayee(dzfp.getSkr());
	    			invoiceSalesBean.setOpenPeople(dzfp.getOpenPeople());
	    			invoiceSalesBean.setRemark(dzfp.getRemark());
	    			
	    			double dblSl=0;
	    			double dbYjSe =0;
	    			double dbEjSe=0;
	    			double  dbSjSe=0;
	    			
        		for(int i=0;i<userMsgResultBeanList.size();i++) {      		        			
        			//添加销售单明细
        			 //计算一二三级税额
        			 dblSl = 0.0D;
        			 
        			 dblSl = Double.parseDouble(dzfp.getSL());
        			 dbYjSe = userMsgResultBeanList.get(i).getPrice() - userMsgResultBeanList.get(i).getPrice() / (1.0D + dblSl);
        		     dbYjSe = getSe(dbYjSe);

        		     dbEjSe = userMsgResultBeanList.get(i).getPriceo() - userMsgResultBeanList.get(i).getPriceo() / (1.0D + dblSl);
        		     dbEjSe = getSe(dbEjSe);
        		     dbSjSe = userMsgResultBeanList.get(i).getPriceoo() - userMsgResultBeanList.get(i).getPriceoo() / (1.0D + dblSl);
        		     dbSjSe = getSe(dbSjSe);
        		     
        		     if (userMsgResultBeanList.get(i).getPrice() != 0.0D) {
        		    	 invoiceSalesDetBean.setMatName("一档水费");       		    	 
        		    	//如果是开票
     	    			 if(form.getInvoiceNature().equals("0")) {
     	    				 invoiceSalesDetBean.setTaxAmount(dbYjSe);
     	    				 invoiceSalesDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuanty()));
     	    				 
        		    	 }else {    
        		    		 //红冲
        		    		 invoiceSalesDetBean.setTaxAmount(dbYjSe*iFlag);
        		    		 invoiceSalesDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuanty()*iFlag));
        		    	 }      		    	
        		     }
        		     else if (userMsgResultBeanList.get(i).getPriceo() != 0.0D) {
        		    	 invoiceSalesDetBean.setMatName("二档水费");        		    	 
        		    	//如果是开票
     	    			 if(form.getInvoiceNature().equals("0")) {
     	    				invoiceSalesDetBean.setTaxAmount(dbEjSe);
     	    				invoiceSalesDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyo()));
     	    			 }else {
     	    				invoiceSalesDetBean.setTaxAmount(dbEjSe*iFlag);
     	    				invoiceSalesDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyo()*iFlag));
     	    			 }
        		    	 
        		     }
        		     else if(userMsgResultBeanList.get(i).getPriceoo()  != 0.0D) {
        		    	 invoiceSalesDetBean.setMatName("三档水费");
        		    	 
        		    	//如果是开票
     	    			 if(form.getInvoiceNature().equals("0")) {
     	    			 invoiceSalesDetBean.setTaxAmount(dbSjSe);
        		    	 invoiceSalesDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyoo()));
     	    			 }else {
     	    		      //红冲
     	    			 invoiceSalesDetBean.setTaxAmount(dbSjSe*iFlag);
     	    			 invoiceSalesDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyoo()*iFlag)); 
     	    			 }
        		     }else{
        		    	 invoiceSalesDetBean.setPrivilegeType("0");
        		     }
        		     
        		    invoiceSalesDetBean.setUnit("吨");
        			invoiceSalesDetBean.setIsTax("1");
        			if(userFlag==0) {
        		 	invoiceSalesDetBean.setPriceMoney(userMsgResultBeanList.get(i).getUprice());
        			}
        			if(userFlag==1) {
        		     invoiceSalesDetBean.setPriceMoney(userMsgResultBeanList.get(i).getGyuprice());	
        			}
        			//如果是开票
	    			if(form.getInvoiceNature().equals("0")) {
        			 invoiceSalesDetBean.setMoney(userMsgResultBeanList.get(i).getPrice());
	    			}else {
	    			 invoiceSalesDetBean.setMoney(userMsgResultBeanList.get(i).getPrice()*iFlag);
	    			}
        			invoiceSalesDetBean.setTaxRate(Double.valueOf(dzfp.getSL()));      			
        			invoiceSalesDetBean.setRevenueCode("1100399000000000000");
        			invoiceSalesDetBean.setIsPrivilege("0");
        			invoiceSalesDetBean.setRevenueVersion("17.0");
        			invoiceSalesDetBean.setLineNature("0");
        			
        			invoiceSalesBeanDetList.add(invoiceSalesDetBean);
        		}
        		//普通用户的话
        		if(userFlag==0) {
        			userMsgResultBeanList.get(0).setLjsdate("2019");
        			userMsgResultBeanList.get(0).setPwsdate("2019");
        		}
        			//并且垃圾费缴费日期不为空的话，垃圾费作为商品  //大中和普通用户都要
        			if(userMsgResultBeanList.get(0).getLjsdate()!=null && !"".equals(userMsgResultBeanList.get(0).getLjsdate())) {
        				invoiceSalesljDetBean.setMatName("垃圾处理费");   
        				
        				 //计算一二三级税额
           			 dblSl = 0.0D;
           			 
           			 dblSl = Double.parseDouble(dzfp.getSL());
           			 dbYjSe = userMsgResultBeanList.get(0).getLjprice() - userMsgResultBeanList.get(0).getLjprice() / (1.0D + dblSl);
           		     dbYjSe = getSe(dbYjSe);
        				 
        				 if (userMsgResultBeanList.get(0).getPrice() != 0.0D) {            		    	   		    	 
            		    	//如果是开票
         	    			 if(form.getInvoiceNature().equals("0")) {
         	    				invoiceSalesljDetBean.setTaxAmount(dbYjSe);         	    				
         	    				invoiceSalesljDetBean.setQuantity(String.valueOf(1));     	    				
         	    				 
            		    	 }else {    
            		    		 //红冲
            		    		 invoiceSalesljDetBean.setTaxAmount(dbYjSe*iFlag);
            		    		 //如果是大中          		    		
            		    			 invoiceSalesljDetBean.setQuantity(String.valueOf(1*iFlag));           		    		           		    		
            		    	 }      		    	
            		     }
            		     else{
            		    	 invoiceSalesljDetBean.setPrivilegeType("0");
            		     }
        				
        				   invoiceSalesljDetBean.setPriceMoney(userMsgResultBeanList.get(0).getLjprice());
        				 
        				//如果是开票
    	    			if(form.getInvoiceNature().equals("0")) {
    	    				invoiceSalesljDetBean.setMoney(userMsgResultBeanList.get(0).getLjprice());
    	    			}else {
    	    				invoiceSalesljDetBean.setMoney(userMsgResultBeanList.get(0).getLjprice()*iFlag);
    	    			}
    	    			invoiceSalesljDetBean.setIsTax("1");
    	    			invoiceSalesljDetBean.setTaxRate(Double.valueOf(dzfp.getSL()));      			
    	    			invoiceSalesljDetBean.setRevenueCode("1100399000000000000");
    	    			invoiceSalesljDetBean.setIsPrivilege("0");
    	    			invoiceSalesljDetBean.setRevenueVersion("17.0");
    	    			invoiceSalesljDetBean.setLineNature("0");
        			}
        			invoiceSalesBeanDetList.add(invoiceSalesljDetBean);
        			//并且污水费缴费日期不为空的话，污水费作为商品
        			if(userMsgResultBeanList.get(0).getPwsdate()!=null && !"".equals(userMsgResultBeanList.get(0).getPwsdate())) {
        				invoiceSaleswsDetBean.setMatName("污水处理费");
        				 //计算一二三级税额
              			 dblSl = 0.0D;
              			 
              			 dblSl = Double.parseDouble(dzfp.getSL());
              			 dbYjSe = userMsgResultBeanList.get(0).getPwf() - userMsgResultBeanList.get(0).getPwf() / (1.0D + dblSl);
              		     dbYjSe = getSe(dbYjSe);
       				 
       				 if (userMsgResultBeanList.get(0).getPrice() != 0.0D) {            		    	   		    	 
           		    	//如果是开票
        	    			 if(form.getInvoiceNature().equals("0")) {
        	    				invoiceSaleswsDetBean.setTaxAmount(dbYjSe);
        	    				if(userFlag==1) {
        	    					invoiceSaleswsDetBean.setQuantity(String.valueOf(1));
        	    				}else {
        	    					invoiceSaleswsDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(0).getQuanty()));
        	    				}
        	    				
        	    				 
           		    	 }else {    
           		    		 //红冲
           		    		invoiceSaleswsDetBean.setTaxAmount(dbYjSe*iFlag);
           		    	   //如果是大中
	       		    		 if(userFlag==1) {
	       		    			invoiceSaleswsDetBean.setQuantity(String.valueOf(1*iFlag));
	       		    		 }else {
	       		    			invoiceSaleswsDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(0).getQuanty()*iFlag)); 
	       		    		 }
           		    		
           		    	 }      		    	
           		     } 
           		     else{
           		        	invoiceSaleswsDetBean.setPrivilegeType("0");
           		      }
       				 if(userFlag==1) { 
       				  invoiceSaleswsDetBean.setPriceMoney(userMsgResultBeanList.get(0).getPwf());
       				 }else {
       				   invoiceSaleswsDetBean.setPriceMoney(userMsgResultBeanList.get(0).getPwuprice()); 
       				 }
       				//如果是开票
       			    	 if(form.getInvoiceNature().equals("0")) {
       			    		invoiceSaleswsDetBean.setMoney(userMsgResultBeanList.get(0).getPwf());
	   	    			}else {
	   	    				invoiceSaleswsDetBean.setMoney(userMsgResultBeanList.get(0).getPwf()*iFlag);
	   	    			}
       			    	invoiceSaleswsDetBean.setIsTax("1");
       			    	invoiceSaleswsDetBean.setTaxRate(Double.valueOf(dzfp.getSL()));      			
       			    	invoiceSaleswsDetBean.setRevenueCode("1100399000000000000");
       			    	invoiceSaleswsDetBean.setIsPrivilege("0");
       			    	invoiceSaleswsDetBean.setRevenueVersion("17.0");
       			    	invoiceSaleswsDetBean.setLineNature("0");
       			    	invoiceSalesBeanDetList.add(invoiceSaleswsDetBean);
        			}
        		
        		
        		invoiceSalesBean.setBillDetailApiList(invoiceSalesBeanDetList);
        		//创建一个空的传送的bean(这个传给接口的参数)
        		InvoiceFormBean invoiceFormBean=new InvoiceFormBean();
        		invoiceFormBean.setData(invoiceSalesBean);
        		invoiceFormBean.setGroup("14");
        		invoiceFormBean.setChannel("电子发票系统");
        		//ArrayList转json
        		String data=JSONObject.toJSONString(invoiceFormBean);  
        		data=data.replaceAll("  ","");
        		System.out.print("传入data的值："+data);
        		//String data=JSON.toJSONString(invoiceFormBean);
        		//调用接口地址
        		Map<String, String> headers = new HashMap<>();
        		long timestamp = System.currentTimeMillis();
        		headers.put("timestamp", Long.toString(timestamp));
        		headers.put("Content-Type", "application/json");
        		String result=HttpRequest.doPost(openInvoiceUrl, data, headers);
        		ResultData resultData = JSONObject.parseObject(result, ResultData.class); 
        		InvoiceResultDataBean invoiceResultDataBean=null;
        		 double TaxAmount=0;
        		ExtendInfoBean extendInfoBean=null;
        	
        		if(!("").equals(resultData.getData())) {
        			 invoiceResultDataBean=JSONObject.parseObject(resultData.getData(), InvoiceResultDataBean.class);
            		//pdf地址
            		 extendInfoBean=JSONObject.parseObject(invoiceResultDataBean.getExtendInfo(), ExtendInfoBean.class);          		 
            		 //获取税额
            		 for(int t=0;t<invoiceResultDataBean.getInvoiceDetailList().size();t++) {
            			TaxAmount+=invoiceResultDataBean.getInvoiceDetailList().get(t).getTaxAmount(); 
            		 }
            		 
        		}
        		
        		//判断是否成功
        		if(resultData.getMsgCode()==0 && resultData.getMessage().equals("电子票开票成功") && form.getInvoiceNature().equals("0")) {           		         		 
            		  //根据类型判断插入到开票记录表中,还是红冲记录表中
            		  //创建一个空的开票记录表
            		  InvoiceRecordBean invoiceRecordBean=new InvoiceRecordBean();
                      invoiceRecordBean.setCtm_num(form.getHno());
                      invoiceRecordBean.setCtm_name(invoiceResultDataBean.getCustName());
                      invoiceRecordBean.setMonth_id(form.getMonth_id());
                      invoiceRecordBean.setInvoice_num(invoiceResultDataBean.getInvoiceNumber());
                      invoiceRecordBean.setInvoice_code(invoiceResultDataBean.getInvoiceCode());
                      invoiceRecordBean.setInvoice_url(extendInfoBean.getPdfUrl());
                      invoiceRecordBean.setCode_num(fpbillCode);
                      invoiceRecordBean.setBatch_num(billBatchNo);
                      invoiceRecordBean.setKp_man(dzfp.getOpenPeople());
                      invoiceRecordBean.setKp_time(getNowDate());
                      invoiceRecordBean.setDzfp_state(1);
                     
                      double finalAmount=0;
                      //遍历获取开票金额
                      for(int q=0;q<userMsgResultBeanList.size();q++) {
                    	  if("".equals(userMsgResultBeanList.get(q).getLjprice())) {
                    		  userMsgResultBeanList.get(q).setLjprice(0);
                    	  }
                    	  if("".equals(userMsgResultBeanList.get(q).getPwf())) {
                    		  userMsgResultBeanList.get(q).setPwf(0);
                    	  }
                    	  finalAmount+=userMsgResultBeanList.get(q).getPrice()+userMsgResultBeanList.get(q).getPriceo()+userMsgResultBeanList.get(q).getPriceoo()+userMsgResultBeanList.get(q).getPwf()+userMsgResultBeanList.get(q).getLjprice();
                      }
                     //开票金额
                      invoiceRecordBean.setKp_price(finalAmount);
                      //开票税额
                      invoiceRecordBean.setKp_taxAmount(TaxAmount);
                    //  invoiceRecordBean.setZp_state(0);
            		  int i=invoiceService.addIntoInvoice(invoiceRecordBean);
            		  
            		  if(i>0) {
            			//  String finalResultData=resultData.toString();
            			  return ResponseData.success(resultData, "开票成功");   
            		  }else {
            			  return ResponseData.faill("开票失败");
            		  }
            		             		  
        		}
        		//红冲
        		else if(resultData.getMsgCode()==0 && resultData.getMsgCode()==0 && resultData.getMessage().equals("电子票开票成功") && form.getInvoiceNature().equals("1")) {
        	      //先删除发票表的记录(如果是红冲记录的话,根据户号和日期)
          		  int i=invoiceService.delInvoiceByno(form);
          		  //插入到红冲记录表中
          		  if(i>0) {
          			  InvoiceRedBean invoiceRedBean=new InvoiceRedBean();
             		  invoiceRedBean.setCtm_num(form.getHno());
             		  invoiceRedBean.setCtm_name(invoiceResultDataBean.getCustName());
             		  invoiceRedBean.setMonth_id(form.getMonth_id());
             		  invoiceRedBean.setInvoice_num(invoiceResultDataBean.getInvoiceNumber());
             		  invoiceRedBean.setInvoice_code(invoiceResultDataBean.getInvoiceCode());
             		  invoiceRedBean.setInvoice_url(extendInfoBean.getPdfUrl());
             		  invoiceRedBean.setZf_man(loginName); 
             		  invoiceRedBean.setZf_time(getNowDate());
             		  invoiceRedBean.setRed_type("1");
             		  //发票红冲金额
             		 double finalAmount=0;
                     //遍历获取开票金额
                     for(int q=0;q<userMsgResultBeanList.size();q++) {
                   	  if("".equals(userMsgResultBeanList.get(q).getLjprice())) {
                   		  userMsgResultBeanList.get(q).setLjprice(0);
                   	  }
                   	  if("".equals(userMsgResultBeanList.get(q).getPwf())) {
                   		  userMsgResultBeanList.get(q).setPwf(0);
                   	  }
                   	  finalAmount+=userMsgResultBeanList.get(q).getPrice()+userMsgResultBeanList.get(q).getPriceo()+userMsgResultBeanList.get(q).getPriceoo()+userMsgResultBeanList.get(q).getPwf()+userMsgResultBeanList.get(q).getLjprice();
                     }
                    //开票金额
                     invoiceRedBean.setKpred_price(finalAmount);
                     //开票税额
                     invoiceRedBean.setKp_taxAmount(TaxAmount);
             		  int j=invoiceService.addIntoRedInvoice(invoiceRedBean);
             		  if(j>0){
             			//  String finalResultData=resultData.toString();
             			  return ResponseData.success(resultData, "红冲成功");  
             		  }else {
             			 return ResponseData.faill( "红冲失败");
             		  }
             				  
          		  }else {
          			 return ResponseData.success(resultData, "删除之前开票记录失败");
          		  }
          		         		  
        		}
        		else {
        			return ResponseData.faill("开票或红冲失败");
        		}     	
        		
        	}else {
        		return ResponseData.faill("没有查询到该户号");
        	}
        }
        else {
        	return ResponseData.faill("参数不对");
        }
      }
   }
    
   /*
    * 
     * date:2019-09-17
     * funtion:专票推送作废,主要封装数据给营收系统,未加密前的数据，开票和作废过程不在本机操作
     * author:xiaozhan
     * param:ctm_num 户号 month_id 开始年月  dzfp_type 标识
    */
    @LoginToken
    @MyLog("专票推送作废")
    @ResponseBody
    @RequestMapping(value = "/openSpecialInvoice.api", method = RequestMethod.POST)
    public ResponseData openSpecialInvoice(@RequestBody JbaseBean form,HttpSession session,HttpServletRequest request,@StaffAttribute(Contans.USER) LoginBean user) throws Exception{
    	 request.setAttribute(Contans.CACHE_NAME, user.getLoginName());	
    	 Object loginNameObj= session.getAttribute("loginName");
    	 String loginName="";
    	 if(loginNameObj!=null) {
    		 loginName=loginNameObj.toString();
    	 }
    	 if( loginNameObj==null ) {
    		return ResponseData.faill(1001, "会话已过期,请重新登录！");
    	 }
         Object userObj= session.getAttribute("user");
    	if(form.getHno()==null ||form.getHno()=="") {
        	return ResponseData.faill("户号不能为空");
        }
        if(form.getMonth_id()==null || form.getMonth_id()=="") {
        	return ResponseData.faill("年月不能为空");
        }
        if(form.getInvoiceType()==null || form.getInvoiceType()=="") {
        	return ResponseData.faill("发票种类不能为空");
        }
        if(form.getInvoiceNature()==null ||form.getInvoiceNature()=="") {
        	return ResponseData.faill("开票性质不能为空");
        } 
        //String转date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date monthDate = df.parse(form.getMonth_id());
        form.setMonthDate(monthDate);
        
        //检查专票表中是否存在该用户的发票信息
        List<UserMsgfpResultBean> UserMsgzpResultBeanList =invoiceService.getUserzpInfobyno(form);
        //说明已经开过专票
        if(UserMsgzpResultBeanList.size()>0 && form.getInvoiceType().equals("0") && form.getInvoiceNature().equals("0")) {
        	 return ResponseData.faill("这个账单已经开过专票，请先撤回后，再次开专票");
        }
        
        //作废的时候检查有没有开过票
        if(UserMsgzpResultBeanList.size()<=0 && form.getInvoiceNature().equals("1")) {
       	  return ResponseData.faill("还未推送专票，开票才可以作废");
        }
        //作废的时候检查是不是已经开过票了
        if(UserMsgzpResultBeanList.size()>0 && UserMsgzpResultBeanList.get(0).getZp_state()==1) {
          return ResponseData.faill("推送状态下不能作废哦");
        }
        else {
        //开专票和作废过程	
        
        //配置文件信息
        dzfpConfigDto dzfp = new dzfpConfigDto();
		Properties prop = new Properties();
		prop.load(new InputStreamReader(TaskScheduler.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8"));
		
		 dzfp.setSL(prop.getProperty("dzfp.SL").trim());	
		 dzfp.setGroup(prop.getProperty("dzfp.GROUP").trim());
		 dzfp.setGSBM(prop.getProperty("dzfp.GSBM").trim());
		 dzfp.setChannel(prop.getProperty("dzfp.Channel").trim());
		 dzfp.setDiskCode(prop.getProperty("dzfp.DiskCode").trim());
		 dzfp.setOrgCode(prop.getProperty("dzfp.OrgCode").trim());
		 dzfp.setUkeyCode(prop.getProperty("dzfp.UkeyCode").trim());
		 dzfp.setSkr(prop.getProperty("dzfp.skr").trim());
		 dzfp.setReviewer(prop.getProperty("dzfp.Reviewer").trim());
		 dzfp.setOpenPeople(prop.getProperty("dzfp.openPeople").trim());
		 
		//开专票发票单的单据批次号
		 String billBatchNo=new String();
		 //开发票单的销售单号
		 String zpbillCode=new String();
		 
		 //如果是专票,生成销售单号billCode,规则是每天从0000001开始		 
			 String todaynow=getNow();
			 String todayymd=getYMD();
			 DateYMDBean dateYMDBean=new DateYMDBean();
			 dateYMDBean.setTodayymd(todayymd);
			 //拿出当天中最大的单号
			  zpbillCode=invoiceService.getMaxBillCode(dateYMDBean);
			
			 //如果单号不存在的话
			 if("".equals(zpbillCode) || zpbillCode==null) {
				//则设置每天的第一个单号
				 zpbillCode="ZP"+todaynow+"0000001";
				 billBatchNo=todayymd+"0000001";
			 }else {
				 String billCode=getDigitalToStr(zpbillCode);
				 zpbillCode="ZP"+todaynow+billCode;
				 billBatchNo=todayymd+billCode;
		 }
			 //原销售单批次
			 String oldBillBatch=new String();
	          //原销售单号
			 String oldBillCode=new String();
			 
	    //作废的话，获取到 原销售单批次oldBillBatch和 旧的销售单号oldBillCode	 
		  if(form.getInvoiceNature().equals("1")) {			 			
				 oldBillBatch=UserMsgzpResultBeanList.get(0).getBatch_num();
				 oldBillCode =UserMsgzpResultBeanList.get(0).getCode_num();					
		  }		
			 
	       int iFlag=1;
	    //如果是推送的话，就传1，作废的话就传0
		      //推送
			if(form.getInvoiceNature().equals("0")) {
				iFlag=1;
			}
			//作废
			else {
				iFlag=0;
			}
			
		  //负数，作废的时候要用到
			int isFlag=-1;
		       
        //创建一个空的销售主单
        SpecialInvoiceBean specialInvoiceBean=new SpecialInvoiceBean();        
        SpecialInvoiceDetBean specialInvoiceDetBean=new SpecialInvoiceDetBean();
        //生活费
        SpecialInvoiceDetBean invoiceSalesljDetBean=new SpecialInvoiceDetBean();
        //污水费bean 
        SpecialInvoiceDetBean invoiceSaleswsDetBean=new SpecialInvoiceDetBean();
        //创建一个空的销售明细form集合接收
        List<SpecialInvoiceDetBean> specialInvoiceDetBeanList=new ArrayList<SpecialInvoiceDetBean>();
        if(form.getHno()!=null && form.getMonth_id()!=null && form.getInvoiceNature()!=null) {
        	//查询用户基本数据(根据户号和当前年月查询)
        //	List<UserMsgResultBean> userMsgResultBeanList=new ArrayList<UserMsgResultBean>();
        	List<UserMsgResultBean> userMsgResultBeanList=invoiceService.getUserInfobyno(form);
        	
        	int userFlag=0;//0普通1大中
        	if(userMsgResultBeanList.size()<=0) {
        		//查询大中客户的基本信息(大中)
        		userMsgResultBeanList=invoiceService.getDZUserInfobyno(form);
        		if(userMsgResultBeanList.size()>0) {
        			userFlag=1;
        		}
        	}      	
        	
        	
        	if(userMsgResultBeanList.size()>0) {
        		//封装数据(封装成json的格式)
	        		//添加销售单主表
        		    specialInvoiceBean.setBillBatchNo(billBatchNo);
        		    specialInvoiceBean.setBillCode(zpbillCode);
	    			specialInvoiceBean.setBillDate(getNowDate());
        		    specialInvoiceBean.setInvoiceType("0");
        		    specialInvoiceBean.setInvoiceNature(form.getInvoiceNature());
        		//    specialInvoiceBean.setIndustryCode("000");
        		    specialInvoiceBean.setCustName(userMsgResultBeanList.get(0).getName());
        		//    specialInvoiceBean.setCustCreditCode(userMsgResultBeanList.get(0).getNsid());
        		    specialInvoiceBean.setCustCreditCode("123456789opqwer");
        		    specialInvoiceBean.setCustAddress(userMsgResultBeanList.get(0).getNewaddr());
        		    specialInvoiceBean.setCustPhone(userMsgResultBeanList.get(0).getPhone());
        		    specialInvoiceBean.setIsTax("1");
        		    specialInvoiceBean.setBusSysId("A0001");
        		    specialInvoiceBean.setBusSysName("sds");
        		    specialInvoiceBean.setDiskCode(dzfp.getDiskCode());
        		    specialInvoiceBean.setUkeyCode(dzfp.getUkeyCode());
        		    specialInvoiceBean.setBusSysId("ssss");
        		    specialInvoiceBean.setBusSysName("ddddd");
        		    specialInvoiceBean.setBusUserName("sdddddd");
        		    specialInvoiceBean.setBusUserPwd("aaaaa");
        		    specialInvoiceBean.setInvoiceOrgCode("org-wsy");
        		    specialInvoiceBean.setInvoiceAuto("0");
        		    specialInvoiceBean.setInvoiceType("0");
        		    specialInvoiceBean.setReviewer(dzfp.getReviewer());
        		    specialInvoiceBean.setPayee(dzfp.getSkr());        		    
        		    specialInvoiceBean.setRemark(dzfp.getRemark());
        		    specialInvoiceBean.setCustBank("中国银行");
        		    specialInvoiceBean.setCustAddress("中国广州");
        		    //作废
        		    if(form.getInvoiceNature().equals("1") && form.getInvoiceType().equals("0")) {
        		    	specialInvoiceBean.setRushRedReason("红冲原因");
        		    	specialInvoiceBean.setOldBillBatch(oldBillBatch);
        		    	specialInvoiceBean.setOldBillCode(oldBillCode);
        		    }
        		    
        		    double dblSl=0;
	    			double dbYjSe =0;
	    			double dbEjSe=0;
	    			double  dbSjSe=0;
	    			
        		for(int i=0;i<userMsgResultBeanList.size();i++) {      		        			
        			//添加销售单明细
        			 //计算一二三级税额
        			  dblSl = 0.0D;
        			 
        			 dblSl = Double.parseDouble(dzfp.getSL());
        			  dbYjSe = userMsgResultBeanList.get(i).getPrice() - userMsgResultBeanList.get(i).getPrice() / (1.0D + dblSl);
        		     dbYjSe = getSe(dbYjSe);

        		     dbEjSe = userMsgResultBeanList.get(i).getPriceo() - userMsgResultBeanList.get(i).getPriceo() / (1.0D + dblSl);
        		     dbEjSe = getSe(dbEjSe);
        		      dbSjSe = userMsgResultBeanList.get(i).getPriceoo() - userMsgResultBeanList.get(i).getPriceoo() / (1.0D + dblSl);
        		     dbSjSe = getSe(dbSjSe);
        		     
        		     if (userMsgResultBeanList.get(i).getPrice() != 0.0D) {
                         specialInvoiceDetBean.setMatName("一档水费");
                         //如果是推送
                         if(form.getInvoiceNature().equals("0") && form.getInvoiceType().equals("0")) {
	        		    	 specialInvoiceDetBean.setTaxAmount(dbYjSe);
	        		    	 specialInvoiceDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuanty()));
                         }else {
                        	 specialInvoiceDetBean.setTaxAmount(dbYjSe);
	        		    	 specialInvoiceDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuanty()*isFlag));
                         }
        		     }
        		     else if (userMsgResultBeanList.get(i).getPriceo() != 0.0D) {
        		    	 specialInvoiceDetBean.setMatName("二档水费");
        		    	 if(form.getInvoiceNature().equals("0") && form.getInvoiceType().equals("0")) {
	        		    	 specialInvoiceDetBean.setTaxAmount(dbEjSe);
	        		    	 specialInvoiceDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyo()));
        		    	 }else {
        		    		 specialInvoiceDetBean.setTaxAmount(dbEjSe);
	        		    	 specialInvoiceDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyo()*isFlag));
        		    	 }
        		     }
        		     else if(userMsgResultBeanList.get(i).getPriceoo()  != 0.0D) {
        		    	 specialInvoiceDetBean.setMatName("三档水费");
        		    	 if(form.getInvoiceNature().equals("0") && form.getInvoiceType().equals("0")) {
	        		    	 specialInvoiceDetBean.setTaxAmount(dbSjSe);
	        		    	 specialInvoiceDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyoo()));
        		    	 }else {
        		    		 specialInvoiceDetBean.setTaxAmount(dbSjSe);
	        		    	 specialInvoiceDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(i).getQuantyoo()*isFlag));
        		    	 }
        		     }else {
        		    	 specialInvoiceDetBean.setPrivilegeType("0");
        		     }
        		     
        		     specialInvoiceDetBean.setUnit("吨");
        		     specialInvoiceDetBean.setIsTax("1");
        		    
        		     if(userFlag==0) {
        		    	 specialInvoiceDetBean.setPriceMoney(userMsgResultBeanList.get(i).getUprice());
             			}
             			if(userFlag==1) {
             				specialInvoiceDetBean.setPriceMoney(userMsgResultBeanList.get(i).getGyuprice());	
             			}
        		     
        		     if(form.getInvoiceNature().equals("0") && form.getInvoiceType().equals("0")) {
        		       specialInvoiceDetBean.setMoney(userMsgResultBeanList.get(i).getPrice()); 
        		     }else {
        		       specialInvoiceDetBean.setMoney(userMsgResultBeanList.get(i).getPrice()*isFlag);  
        		     }  
        		     specialInvoiceDetBean.setTaxRate(Double.valueOf(dzfp.getSL()));      			
        		     specialInvoiceDetBean.setRevenueCode("1100399000000000000");
        		     specialInvoiceDetBean.setIsPrivilege("0");       			
        		     specialInvoiceDetBean.setLineNature("0");
        		     specialInvoiceDetBean.setRevenueVersion("17.0");
        			
        		     specialInvoiceDetBeanList.add(specialInvoiceDetBean);
        		}
        		//如果是大众用户
        		//普通用户的话
        		if(userFlag==0) {
        			userMsgResultBeanList.get(0).setLjsdate("2019");
        			userMsgResultBeanList.get(0).setPwsdate("2019");
        		}
        			//并且垃圾费缴费日期不为空的话，垃圾费作为商品
        			if(userMsgResultBeanList.get(0).getLjsdate()!=null && !"".equals(userMsgResultBeanList.get(0).getLjsdate())) {
        				invoiceSalesljDetBean.setMatName("垃圾处理费");   
        				
        				 //计算一二三级税额
           			 dblSl = 0.0D;
           			 
           			 dblSl = Double.parseDouble(dzfp.getSL());
           			 dbYjSe = userMsgResultBeanList.get(0).getLjprice() - userMsgResultBeanList.get(0).getLjprice() / (1.0D + dblSl);
           		     dbYjSe = getSe(dbYjSe);
        				 
        				 if (userMsgResultBeanList.get(0).getPrice() != 0.0D) {            		    	   		    	 
            		    	//如果是开票
         	    			 if(form.getInvoiceNature().equals("0")) {
         	    				invoiceSalesljDetBean.setTaxAmount(dbYjSe);         	    				      	    			
         	    				 invoiceSalesljDetBean.setQuantity(String.valueOf(1));         	    			
         	    				 
            		    	 }else {    
            		    		 //红冲
            		    		 invoiceSalesljDetBean.setTaxAmount(dbYjSe*iFlag);            		    		
            		    		 invoiceSalesljDetBean.setQuantity(String.valueOf(1*iFlag));            		    	
            		    	 }      		    	
            		     }
            		     else{
            		    	 invoiceSalesljDetBean.setPrivilegeType("0");
            		     }
        				 
        				 invoiceSalesljDetBean.setPriceMoney(userMsgResultBeanList.get(0).getLjprice());
        				//如果是开票
    	    			if(form.getInvoiceNature().equals("0")) {
    	    				invoiceSalesljDetBean.setMoney(userMsgResultBeanList.get(0).getLjprice());
    	    			}else {
    	    				invoiceSalesljDetBean.setMoney(userMsgResultBeanList.get(0).getLjprice()*iFlag);
    	    			}
    	    			invoiceSalesljDetBean.setIsTax("1");
    	    			invoiceSalesljDetBean.setTaxRate(Double.valueOf(dzfp.getSL()));      			
    	    			invoiceSalesljDetBean.setRevenueCode("1100399000000000000");
    	    			invoiceSalesljDetBean.setIsPrivilege("0");
    	    			invoiceSalesljDetBean.setRevenueVersion("17.0");
    	    			invoiceSalesljDetBean.setLineNature("0");
        			}
        			specialInvoiceDetBeanList.add(invoiceSalesljDetBean);
        			//并且污水费缴费日期不为空的话，污水费作为商品
        			if(userMsgResultBeanList.get(0).getPwsdate()!=null && !"".equals(userMsgResultBeanList.get(0).getPwsdate())) {
        				invoiceSaleswsDetBean.setMatName("污水处理费");
        				 //计算一二三级税额
              			 dblSl = 0.0D;
              			 
              			 dblSl = Double.parseDouble(dzfp.getSL());
              			 dbYjSe = userMsgResultBeanList.get(0).getPwf() - userMsgResultBeanList.get(0).getPwf() / (1.0D + dblSl);
              		     dbYjSe = getSe(dbYjSe);
       				 
       				 if (userMsgResultBeanList.get(0).getPrice() != 0.0D) {            		    	   		    	 
           		    	//如果是开票
        	    			 if(form.getInvoiceNature().equals("0")) {
        	    				invoiceSaleswsDetBean.setTaxAmount(dbYjSe);
        	    				invoiceSaleswsDetBean.setQuantity(String.valueOf(1));
        	    				//如果是大中
         	    				if(userFlag==1) {
         	    					invoiceSaleswsDetBean.setQuantity(String.valueOf(1));
         	    				}else {
         	    					invoiceSaleswsDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(0).getQuanty()));
         	    				}
        	    				 
           		    	 }else {    
           		    		 //红冲
           		    		invoiceSaleswsDetBean.setTaxAmount(dbYjSe*iFlag);
           		    		
           		    	 //如果是大中
	       		    		 if(userFlag==1) {
	       		    			invoiceSaleswsDetBean.setQuantity(String.valueOf(1*iFlag));
	       		    		 }else {
	       		    			invoiceSaleswsDetBean.setQuantity(String.valueOf(userMsgResultBeanList.get(0).getQuanty()*iFlag)); 
	       		    		 }
           		    	 }      		    	
           		     } 
           		     else{
           		        	invoiceSaleswsDetBean.setPrivilegeType("0");
           		      }
       				 if(userFlag==1) {
       				  invoiceSaleswsDetBean.setPriceMoney(userMsgResultBeanList.get(0).getPwf());
       				 }else {
       					invoiceSaleswsDetBean.setPriceMoney(userMsgResultBeanList.get(0).getPwuprice()); 
       				 }
       				//如果是开票
       			    	 if(form.getInvoiceNature().equals("0")) {
       			    		invoiceSaleswsDetBean.setMoney(userMsgResultBeanList.get(0).getPwf());
	   	    			}else {
	   	    				invoiceSaleswsDetBean.setMoney(userMsgResultBeanList.get(0).getPwf()*iFlag);
	   	    			}
       			    	invoiceSaleswsDetBean.setIsTax("1");
       			    	invoiceSaleswsDetBean.setTaxRate(Double.valueOf(dzfp.getSL()));      			
       			    	invoiceSaleswsDetBean.setRevenueCode("1100399000000000000");
       			    	invoiceSaleswsDetBean.setIsPrivilege("0");
       			    	invoiceSaleswsDetBean.setRevenueVersion("17.0");
       			    	invoiceSaleswsDetBean.setLineNature("0");
       			    	specialInvoiceDetBeanList.add(invoiceSaleswsDetBean);
        			}
        		
        		
        		specialInvoiceBean.setBillDetailApiList(specialInvoiceDetBeanList);
        		//创建一个空的传送的bean(这个传给接口的参数)
        		SpecialFormBean specialFormBean=new SpecialFormBean();
        		specialFormBean.setData(specialInvoiceBean);
        		specialFormBean.setGroup("14");
        		specialFormBean.setChannel("aa");
        		//ArrayList转json
        		String data=JSONObject.toJSONString(specialFormBean);  
        		data=data.replaceAll("  ","");
        		System.out.print("传入data的值："+data);
        		//String data=JSON.toJSONString(invoiceFormBean);
        		//调用接口地址
        		Map<String, String> headers = new HashMap<>();
        		long timestamp = System.currentTimeMillis();
        		headers.put("timestamp", Long.toString(timestamp));
        		headers.put("Content-Type", "application/json");
        		if(iFlag==1) {
        		   headers.put("billFlag","1");
        		}else {
        	       headers.put("billFlag","0");
        		}
        		String result=HttpRequest.doPost(openSpecialInvoiceUrl, data, headers);
        		ResultData resultData = JSONObject.parseObject(result, ResultData.class);
        		InvoiceResultDataBean invoiceResultDataBean=new InvoiceResultDataBean();
        		double TaxAmount=0;
        		if(!("").equals(resultData.getData())) {
        		  invoiceResultDataBean=JSONObject.parseObject(resultData.getData(), InvoiceResultDataBean.class);        		 
        		  //获取税额
         		 for(int t=0;t<invoiceResultDataBean.getBillDetailList().size();t++) {
         			TaxAmount+=invoiceResultDataBean.getBillDetailList().get(t).getTaxAmount(); 
         		 }
        		}
        		//判断是否成功
        		if(resultData.getMsgCode()==0  && form.getInvoiceNature().equals("0") && form.getInvoiceType().equals("0")) {           		          		 
            	     //根据类型判断插入到开票记录表中,还是红冲记录表中  (专票)   
        			 //创建一个空的开票记录表
          		    InvoiceRecordBean invoiceRecordBean=new InvoiceRecordBean();
                    invoiceRecordBean.setCtm_num(form.getHno());
                    invoiceRecordBean.setCtm_name(invoiceResultDataBean.getCustName());
                    invoiceRecordBean.setMonth_id(form.getMonth_id());
                //  invoiceRecordBean.setInvoice_num(invoiceResultDataBean.getInvoiceNumber());
                //  invoiceRecordBean.setInvoice_code(invoiceResultDataBean.getInvoiceCode());               
                    invoiceRecordBean.setCode_num(zpbillCode);
                    invoiceRecordBean.setBatch_num(billBatchNo);
               //   invoiceRecordBean.setKp_man(dzfp.getOpenPeople());
               //   invoiceRecordBean.setKp_time(invoiceResultDataBean.getInvoiceDate());
               //   invoiceRecordBean.setDzfp_state(0);
                    invoiceRecordBean.setZp_state(1);
                    
                    double finalAmount=0;
                    //遍历获取开票金额
                    for(int q=0;q<userMsgResultBeanList.size();q++) {
                  	  if("".equals(userMsgResultBeanList.get(q).getLjprice())) {
                  		  userMsgResultBeanList.get(q).setLjprice(0);
                  	  }
                  	  if("".equals(userMsgResultBeanList.get(q).getPwf())) {
                  		  userMsgResultBeanList.get(q).setPwf(0);
                  	  }
                  	  finalAmount+=userMsgResultBeanList.get(q).getPrice()+userMsgResultBeanList.get(q).getPriceo()+userMsgResultBeanList.get(q).getPriceoo()+userMsgResultBeanList.get(q).getPwf()+userMsgResultBeanList.get(q).getLjprice();
                    }
                    
                    //开票金额
                    invoiceRecordBean.setKp_price(finalAmount);
                    //开票税额
                    invoiceRecordBean.setKp_taxAmount(TaxAmount);
                    
              //      invoiceRecordBean.setKp_tax(kp_tax);
          		    int i=invoiceService.addIntoInvoice(invoiceRecordBean);
	          		 if(i>0) {
	          			 //推送的时候，跳到第三方url
	        			  String goUrl="http://192.168.4.202"+URLEncoder.encode("/#!/outputManage/ovat/invoice/list", "UTF-8");
	        			 //封装参数
	        			  JSONObject employee=new JSONObject();
	        			  long timestampnew = System.currentTimeMillis();
	        			  employee.put("userName", openUrlPeople);
	        		      employee.put("timestamp", Long.valueOf(timestampnew));
	        		      String JsonContent=employee.toString();
	        		      String Content=AesUtil.encrypt(JsonContent);
	        		      Content = Content.replaceAll("\r|\n", "");
	        		      
	        		      Map map = new HashMap();
	        		      map.put("goUrl", goUrl);
	        		      map.put("busSysId", "999999");
	        		      map.put("data", Content);
	        		      String Jsoninfo = map.toString();
	        		      Jsoninfo = Jsoninfo.substring(1, Jsoninfo.length() - 1);
	        		      String Jsoninfo2 = Jsoninfo.replaceAll(", ", "&");
	        		      
	        		      String m_strMsg = Jsoninfo2;        		      
	        		      resultData.setGoUrl(m_strMsg);
	        		      
	        			  return ResponseData.success(resultData, "电子专票推送成功"); 	        			 
	        		  }else {
	        			  return ResponseData.faill("电子专票推送失败");
	        		  }
            		    		  
        		}
        		//作废（跳转到第三方，在第三方操作）
        		else if(resultData.getMsgCode()==0  && form.getInvoiceNature().equals("1") && form.getInvoiceType().equals("0")) {        			
               			 //作废的时候，跳到第三方url
	        			  String goUrl="http://192.168.4.202" + URLEncoder.encode("/#!/outputManage/ovat/openedInvoice/list", "UTF-8");
	        			 //封装参数
	        			  JSONObject employee=new JSONObject();
	        			  long timestampnew = System.currentTimeMillis();
	        			  employee.put("userName", openUrlPeople);
	        		      employee.put("timestamp", Long.valueOf(timestampnew));
	        		      String JsonContent=employee.toString();
	        		      String Content=AesUtil.encrypt(JsonContent);
	        		      Content = Content.replaceAll("\r|\n", "");
	        		      
	        		      Map map = new HashMap();
	        		      map.put("goUrl", goUrl);
	        		      map.put("busSysId", "999999");
	        		      map.put("data", Content);
	        		      String Jsoninfo = map.toString();
	        		      Jsoninfo = Jsoninfo.substring(1, Jsoninfo.length() - 1);
	        		      String Jsoninfo2 = Jsoninfo.replaceAll(", ", "&");
	        		      
	        		      String m_strMsg = Jsoninfo2;	        		      
	        		      resultData.setGoUrl(m_strMsg);
	        		      
               			  return ResponseData.success(resultData, "电子专票作废成功");                 		                				  
            		  
        		}
        		else {
        			return ResponseData.faill(resultData.getMessage());
        		} 		
        	}else {
        		return ResponseData.faill("没有查询到该用户");
        	}
        	
         }else {
        	 return ResponseData.faill("参数错误"); 
         }
       }
		
    }
    
    /*
     * 
      * date:2019-09-25
      * funtion:专票撤回(这个接口调用了第三方的撤回接口)(暂时废弃)
      * author:xiaozhan
      * param:ctm_num 户号 month_id 开始年月  dzfp_type 标识
     */
     @LoginToken
     @MyLog("专票撤回")
     @ResponseBody
     @RequestMapping(value = "/specialInvoiceWithdraw.api", method = RequestMethod.POST)
     public ResponseData specialInvoiceWithdraw(@RequestBody JbaseBean form,HttpSession session,HttpServletRequest request,@StaffAttribute(Contans.USER) LoginBean user) throws Exception {
    	 request.setAttribute(Contans.CACHE_NAME, user.getLoginName());	    	
	      Object loginNameObj= session.getAttribute("loginName");
	    	String loginName="";
	    	
	    	if(loginNameObj==null) {
	    	 return ResponseData.faill(1001, "会话已过期,请重新登录！");
	    	}
    	 if(form.getHno()==null ||form.getHno()=="") {
         	return ResponseData.faill("户号不能为空");
         }
         if(form.getMonth_id()==null || form.getMonth_id()=="") {
         	return ResponseData.faill("年月不能为空");
         }
         if(form.getInvoiceType()==null || form.getInvoiceType()=="") {
         	return ResponseData.faill("发票种类不能为空");
         }
         if(form.getHno()!=null && form.getMonth_id()!=null && form.getInvoiceType()!=null &&form.getInvoiceType().equals("0")) {
        	 //查询是否有待开的在invoice_record的表中
        	 List<UserMsgfpResultBean> UserMsgfpResultBeanList=invoiceService.getUserDKFPInfo(form);
        	 if(UserMsgfpResultBeanList.size()>0) {
        		 //说明存在待开的
        		 
        		 //封装请求数据(请求头)
        		 long timestamp = System.currentTimeMillis();
        		 Map<String, String> headers = new HashMap<>();
        		 headers.put("timestamp", Long.toString(timestamp));
        	     headers.put("Content-Type", "application/json");
        	     //请求数据
        	     JSONObject employee=new JSONObject();
        	     employee.put("billBatchNo", UserMsgfpResultBeanList.get(0).getBatch_num());
        	     employee.put("billCode", UserMsgfpResultBeanList.get(0).getCode_num());
        	     String billData=employee.toString();
        	     JSONObject invoicelist = new JSONObject();
        	      invoicelist.put("data", billData);
        	      invoicelist.put("group", "14");
        	      invoicelist.put("channel", "电子专票撤回");
        	      String invoiceInfo=invoicelist.toString();
        	      System.out.println("撤回请求参数："+invoiceInfo);
        		  //调用第三方接口，主要是他那边的状态也要跟着改变，我们这边才可以删除
        		  String info=HttpRequest.doPost(cancelSpecialInvoiceUrl, invoiceInfo, headers);
        		  JSONObject Returnjson=JSONObject.parseObject(info);
        		  String code=Returnjson.getString("code");
        		  String message = Returnjson.getString("message");
        	      String data = Returnjson.getString("data");
        	     if("0".equals(code)) {
        	    	 //说明撤回成功（另外一个平台）
        	    	 //根据户号，年月，待开票专状态，删除该条待开开票记录
                	 int i=invoiceService.delSpecialInvoiceByzpstate(form);
                	 if(i>0) {
                		return  ResponseData.success(data,"撤回成功");
                	 }else {
                		return  ResponseData.faill("撤回失败");
                	 }
        	     }else {
        	    	 return  ResponseData.faill(message);
        	     }
        		
        	 }else {
        		 return  ResponseData.faill("这条记录还没有存在待开状态的发票");
        	 }
        	        	 
         }else {
        	 return  ResponseData.faill("参数不对"); 
         }
     }   
     /*
      * 
       * date:2019-10-10
       * funtion:专票撤回(这个只是删除mysql中的专票推送状态的那条数据)
       * author:xiaozhan
       * param:ctm_num 户号 month_id 开始年月  dzfp_type 标识
      */
      @LoginToken
      @MyLog("电子专票撤回")
      @ResponseBody
      @RequestMapping(value = "/specialInvoiceWithdrawNew.api", method = RequestMethod.POST)
      public ResponseData specialInvoiceWithdrawNew(@RequestBody JbaseBean form,HttpSession session,HttpServletRequest request,@StaffAttribute(Contans.USER) LoginBean user) throws Exception {
    	  request.setAttribute(Contans.CACHE_NAME, user.getLoginName());		
	      Object loginNameObj= session.getAttribute("loginName");
	    	String loginName="";
	    	
	    	if(loginNameObj==null) {
	    	 return ResponseData.faill(1001, "会话已过期,请重新登录！");
	    	}
    	  if(form.getHno()==null ||form.getHno()=="") {
          	return ResponseData.faill("户号不能为空");
          }
          if(form.getMonth_id()==null || form.getMonth_id()=="") {
          	return ResponseData.faill("年月不能为空");
          }
          if(form.getInvoiceType()==null || form.getInvoiceType()=="") {
          	return ResponseData.faill("发票种类不能为空");
          }
          if(form.getHno()!=null && form.getMonth_id()!=null && form.getInvoiceType()!=null &&form.getInvoiceType().equals("0")) {
         	 //查询是否有待开的在invoice_record的表中
         	 List<UserMsgfpResultBean> UserMsgfpResultBeanList=invoiceService.getUserDKFPInfo(form);
         	 if(UserMsgfpResultBeanList.size()>0) {
         		 //说明存在待开的        		          		
         	     //根据户号，年月，待开票专状态，删除该条待开开票记录
                 	 int i=invoiceService.delSpecialInvoiceByzpstate(form);
                 	 if(i>0) {                		
                 		return  ResponseData.success("撤回成功");
                 	 }else {
                 		return  ResponseData.faill("撤回失败");
                 	 }         	   
         		
         	 }else {
         		 return  ResponseData.faill("这条记录还没有存在待开状态的发票");
         	 }
         	        	 
          }else {
         	 return  ResponseData.faill("参数不对"); 
          }
      }
      
      /*
       * 
        * date:2019-10-16
        * funtion:账单查询接口
        * author:xiaozhan
        * param:hno
       */
      
      @MyLog("账单查询接口")
      @ResponseBody
      @RequestMapping(value = "/historyBillQuery.api", method = RequestMethod.POST)
      public ResponseData historyBillQuery(@RequestBody JbaseBean jbaseBean,HttpSession session){
          if(jbaseBean.getHno()==null || "".equals(jbaseBean.getHno())) {
        	  return ResponseData.faill("户号不能为空"); 
          }else {
        	  //查询普通用户的账单信息
        	  List<HistoryBillBean> historyBillBeanList=invoiceService.queryHistoryBill(jbaseBean);
        	  //说明是普通用户
        	  if(historyBillBeanList.size()>0) {
        		  for(int i=0;i<historyBillBeanList.size();i++) {
        			  //遍历中把每个账单信息的总额和缴费状态给改了
        			  historyBillBeanList.get(i).setTotal_money(historyBillBeanList.get(i).getPrice()+historyBillBeanList.get(i).getPriceo()+historyBillBeanList.get(i).getPriceoo()+historyBillBeanList.get(i).getCnj()+historyBillBeanList.get(i).getPwcnj()+historyBillBeanList.get(i).getPwf()+historyBillBeanList.get(i).getLjprice());
        			  historyBillBeanList.get(i).setMoney_state("1");
        		  }
        		  return ResponseData.success(historyBillBeanList, "账单查询成功");
        	  }else {
        	  //查询大中用户的账单信息
        		  List<HistoryBillBean> historyBigBillBeanList=invoiceService.queryHistoryBigBill(jbaseBean); 
        		  //说明是大中用户
        		  if(historyBigBillBeanList.size()>0) {
        			 for(int i=0;i<historyBigBillBeanList.size();i++) {
            			  //遍历中把每个账单信息的总额和缴费状态给改了
        				  historyBigBillBeanList.get(i).setTotal_money(historyBigBillBeanList.get(i).getPrice()+historyBigBillBeanList.get(i).getCnj()+historyBigBillBeanList.get(i).getPwcnj()+historyBigBillBeanList.get(i).getPwf()+historyBillBeanList.get(i).getLjprice());
        				  historyBigBillBeanList.get(i).setMoney_state("1");
            		  }
        			  return ResponseData.success(historyBigBillBeanList, "账单查询成功");
        		  }else {
        			  return ResponseData.faill("没有查询到该用户的账单信息"); 
        		  }
        	  }
          }
      } 	  
      
     /*
      * 
       * date:2019-09-25
       * funtion:专票开票接口(更新数据，打印什么的)
       * author:xiaozhan
       * param:invoice_num  invoice_code invoice_money
      */
     @MyLog("专票回调")
     @ResponseBody
     @RequestMapping(value = "/specialInvoiceOpen.api", method = RequestMethod.POST)
     public ResponseData specialInvoiceOpen(@RequestBody InvoiceStatusBean form,HttpSession session) throws Exception {
    	 //拿到第三方返回给的数据
    	if(form.getBillType()==null || form.getBillType().equals("")) {
    		 ResponseData.faill("失败,参数billType不能为空"); 
    	}
    	//解密需要的数据
    	String data=AesUtil.decrypt(form.getValue());
    	JSONObject jsonObj2=JSONObject.parseObject(data);
    	String billNum = jsonObj2.getString("invoiceNumber");
        String gmpc = jsonObj2.getString("invoiceCode");
        String billCode = jsonObj2.getString("billCode");
        String billMoney = jsonObj2.getString("billMoney");
        String kpman = jsonObj2.getString("kpMan");
        String billPrintState = jsonObj2.getString("billPrintState");
        if(billNum==null || ("").equals(billNum)) {
        	ResponseData.faill("失败,参数billNum不能为空"); 
        }
        if(gmpc==null || ("").equals(gmpc)) {
        	ResponseData.faill("失败,参数gmpc不能为空"); 
        }
        if(billCode==null || ("").equals(billCode)) {
        	ResponseData.faill("失败,参数billCode不能为空"); 
        }
        if(billMoney==null || ("").equals(billMoney)) {
        	ResponseData.faill("失败,参数billMoney不能为空"); 
        }
        if(kpman==null || ("").equals(kpman)) {
        	ResponseData.faill("失败,参数kpman不能为空"); 
        }
        if(billPrintState==null || ("").equals(billPrintState)) {
        	ResponseData.faill("失败,参数billPrintState不能为空"); 
        }
    	//开票
        if("1".equals(form.getBillType())) {
        	//更新Invoice_record记录表的数据 根据订单号code_num
        	//创建一个空的开票记录表
  		    InvoiceRecordBean invoiceRecordBean=new InvoiceRecordBean();        
            invoiceRecordBean.setInvoice_num(billNum);
            invoiceRecordBean.setInvoice_code(gmpc);                 
            invoiceRecordBean.setKp_man(kpman);
            invoiceRecordBean.setKp_time(getNowDate());
            invoiceRecordBean.setCode_num(billCode);
            invoiceRecordBean.setDzfp_state(2);
            
        	int i=invoiceService.updateSpecialInvoicebynum(invoiceRecordBean);
        	if(i>0) {
        		return ResponseData.success("开票成功"); 
        	}else {
        		return ResponseData.faill("开票失败");
        	}
        }
        //作废红冲的话		
		  else if("2".equals(form.getBillType()) || "3".equals(form.getBillType())) {
		  		   
			 JbaseBean jbaseBean=new JbaseBean();
			 jbaseBean.setCode_num(billCode);
			 //先根据订单号code_num查询该条订单的的开票金额和开票税率	
			 List<UserMsgfpResultBean> priceandtaxList=invoiceService.searchPirceANDTax(jbaseBean);
			  //根据订单号code_num删除该条待开开票记录(//删除专票推送状态的)
         	 int i=invoiceService.delSpecialInvoiceByCodenum(jbaseBean);		   
         	 if(i>0) {
         		//红冲记录表增加一条 
         		InvoiceRedBean invoiceRedBean=new InvoiceRedBean();
         		invoiceRedBean.setCtm_num(priceandtaxList.get(0).getCtm_num());
         		invoiceRedBean.setCtm_name(priceandtaxList.get(0).getCtm_name());
         		invoiceRedBean.setMonth_id(priceandtaxList.get(0).getMonth_id());
         		invoiceRedBean.setInvoice_num(billNum);
         		invoiceRedBean.setInvoice_code(gmpc);
         		invoiceRedBean.setZf_man(kpman);       	          	     
         		invoiceRedBean.setZf_time(getNowDate());
         		invoiceRedBean.setRed_type("2");
       		    //专票作废金额
       		    invoiceRedBean.setKpred_price(priceandtaxList.get(0).getKp_price());               
         		//专票作废税额
       		    invoiceRedBean.setKp_taxAmount(priceandtaxList.get(0).getKp_taxAmount()*(-1));
       		  
         		invoiceService.addIntoRedInvoice(invoiceRedBean);
         		if(i>0) {
            		return ResponseData.success("作废成功"); 
            	}else {
            		return ResponseData.faill("作废失败");
            	}
         		
         	 }else {
         		return ResponseData.faill("作废失败");
         	 }
		  }
        else if("5".equals(form.getBillType())) {
        	 InvoiceRecordBean invoiceRecordBean=new InvoiceRecordBean();        
             invoiceRecordBean.setInvoice_num(billNum);
             invoiceRecordBean.setInvoice_code(gmpc);                 
             invoiceRecordBean.setKp_man(kpman);
             invoiceRecordBean.setKp_time(getNowDate());
             invoiceRecordBean.setCode_num(billCode);
             invoiceRecordBean.setDzfp_state(3);
         	 int i=invoiceService.updateSpecialInvoicebynum(invoiceRecordBean);
	         	if(i>0) {
	        		return ResponseData.success("开票和打印成功"); 
	        	}else {
	        		return ResponseData.faill("开票和打印失败");
	        	}
        }else {
        	return ResponseData.success("没进行任何操作"); 
        }
		
     }
    	/**
	              * 从网络Url中下载文件
	     * @param urlStr
	     * @param fileName
	     * @param savePath
	     * @throws IOException
	     * NAME:xiaozhan
	     */       
        @ResponseBody
        @RequestMapping(value = "/downLoadByUrl.api", method = RequestMethod.POST)
        public ResponseData downLoadByUrl(@RequestBody downURLBean form,HttpSession session) throws Exception {
	
	        if(form.getPdfurl()==null ||form.getPdfurl().equals("")) {
	        	return ResponseData.faill("下载路径不能为空");
	        }
	        if(form.getFileName()==null ||form.getFileName().equals("")) {
	        	return ResponseData.faill("文件名称不能为空");
	        }
	        form.setSavePath("c:/uploadpdf");
	        URL url = new URL(form.getPdfurl());
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        //设置超时间为3秒
	        conn.setConnectTimeout(5*1000);
	        //防止屏蔽程序抓取而返回403错误
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        //得到输入流
	        InputStream inputStream = conn.getInputStream();
	        //获取自己数组
	        byte[] getData = readInputStream(inputStream);
	        //文件保存位置
	        File saveDir = new File(form.getSavePath());
	        if(!saveDir.exists()){
	            saveDir.mkdir();
	        }
	        File file = new File(saveDir+File.separator+form.getFileName());
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write(getData);
	        if(fos!=null){
	            fos.close();
	        }
	        if(inputStream!=null){
	            inputStream.close();
	            
	        }	     
			return ResponseData.success("文件已经下载到c:/uploadpdf文件夹中");

	    }
      //首页电子发票报表接口(发票种类，开票数量，开票金额，开票税额，)
      @MyLog("首页电子发票接口")
	  @LoginToken
	  @ResponseBody	  
	  @RequestMapping(value = "/reportIndex.api", method = RequestMethod.POST)
	   public ResponseData reportIndex(HttpSession session,HttpServletRequest request,@StaffAttribute(Contans.USER) LoginBean user) {
		  
		  request.setAttribute(Contans.CACHE_NAME, user.getLoginName());	
	      Object loginNameObj= session.getAttribute("loginName");
	    	String loginName="";
	    	
	    	if(loginNameObj==null) {
	    	 return ResponseData.faill(1001, "会话已过期,请重新登录！");
	    	}else {
	    		loginName=loginNameObj.toString();	
	    	    Object userObj= session.getAttribute("user");
	    		   //查询普票的sql
	    			  List<IndexReportBean> indexPTReportBeanList= invoiceService.getCountPTFPMsg();
	    			//查询普票红冲的sql
	    			  List<IndexReportBean> indexPTRedReportBeanList= invoiceService.getCountPTFPRedMsg();
	    		    //查询专票的sql
	    			  List<IndexReportBean> indexZPReportBeanList= invoiceService.getCountZPMsg();
	    			//查询专票红冲的sql
	    			  List<IndexReportBean> indexZPRedReportBeanList= invoiceService.getCountZPRedMsg();
	    			//获取当前登陆者的信息
	    			  IndexReportBean indexReportBean=new IndexReportBean();
	    			/*
	    			 * String username=session.getAttribute("user").toString(); String
	    			 * username1=session.getAttribute("loginName").toString();
	    			 */
	    			  indexReportBean.setLoginName(loginName);
	    		    //查询用户登陆信息
	    			  List<IndexReportBean> indexUserReportBeanList= invoiceService.getCountUserMsg(indexReportBean);
	    			  
	    			
	    			  //创建一个空的List接收
	    			  List<IndexReportBean> finalIndexReportBeanList=new ArrayList<IndexReportBean>();
	    			 
	    			  for(int i=1;i<=2;i++) {
	    				   //创建一个全新空的bean接收
	    				     IndexReportBean finalIndexReportBean=new IndexReportBean();
	    				     //没找到该用户信息
	    					  if(indexUserReportBeanList.size()<=0) {
	    							 finalIndexReportBean.setLastIP("还未登陆过");
	    							 finalIndexReportBean.setLastLoginTime("还未登陆过");
	    							 finalIndexReportBean.setLoginNum(1);
	    						 }else {
	    							finalIndexReportBean.setLastIP(indexUserReportBeanList.get(0).getLastIP());
	    							finalIndexReportBean.setLastLoginTime(indexUserReportBeanList.get(0).getLastLoginTime());
	    							finalIndexReportBean.setLoginNum(indexUserReportBeanList.get(0).getLoginNum());
	    							
	    					   }
	    				     
	    					 finalIndexReportBean.setInvoiceType(i+"");						 
	    					 if(i==1) {
	    					  //存在普票信息
	    					  if(indexPTReportBeanList.get(0).getKpNum() !=null && indexPTReportBeanList.get(0).getKpNum()>0) {
	    						  finalIndexReportBean.setKpNum(indexPTReportBeanList.get(0).getKpNum());
	    						  finalIndexReportBean.setKpPrice(indexPTReportBeanList.get(0).getKpPrice()); 
	    						  finalIndexReportBean.setKpShuiER(indexPTReportBeanList.get(0).getKpShuiER());
	    					  }else {
	    						  finalIndexReportBean.setKpNum(0);
	    						  finalIndexReportBean.setKpPrice(0);
	    						  finalIndexReportBean.setKpShuiER(0);
	    					  }
	    					  if(indexPTRedReportBeanList.get(0).getRedNum()!=null && indexPTRedReportBeanList.get(0).getRedNum()>0) {
	    					   finalIndexReportBean.setKpRedPrice(indexPTRedReportBeanList.get(0).getKpRedPrice());
	    				       finalIndexReportBean.setRedNum(indexPTRedReportBeanList.get(0).getRedNum());
	    				       finalIndexReportBean.setKpRedShuiER(indexPTRedReportBeanList.get(0).getKpRedShuiER());
	    					  }else {
	    						  finalIndexReportBean.setKpRedPrice(0);
	    					       finalIndexReportBean.setRedNum(0); 
	    					  }
	    					  finalIndexReportBeanList.add(finalIndexReportBean);
	    				  }
	    					 				 
	    					 else {
	    					   if(indexZPReportBeanList.get(0).getKpNum() !=null && indexZPReportBeanList.get(0).getKpNum()>0) {
	    				       finalIndexReportBean.setKpNum(indexZPReportBeanList.get(0).getKpNum());
	    				       finalIndexReportBean.setKpPrice(indexZPReportBeanList.get(0).getKpPrice());
	    					   }else {
	    						finalIndexReportBean.setKpNum(0);
	    					    finalIndexReportBean.setKpPrice(0); 
	    					   }
	    					   if(indexZPReportBeanList.get(0).getRedNum()!=null && indexZPReportBeanList.get(0).getRedNum()>0) { 
	    					    finalIndexReportBean.setKpRedPrice(indexZPRedReportBeanList.get(0).getKpRedPrice());
	    				        finalIndexReportBean.setRedNum(indexZPRedReportBeanList.get(0).getRedNum());
	    					   }else {
	    						   finalIndexReportBean.setKpRedPrice(0);
	    					       finalIndexReportBean.setRedNum(0);
	    					   }
	    					   finalIndexReportBeanList.add(finalIndexReportBean);
	    					 }
	    					
	    					
	    				  }
	    			 
	    			return  ResponseData.success(finalIndexReportBeanList,"成功请求数据");	
	    	}
	    	 			  
	  }
	  
	//专票自定义按钮点击跳转到第三方url接口
	  @LoginToken
	  @MyLog("专票自定义按钮点击跳转到第三方url接口")
	  @ResponseBody	  
	  @RequestMapping(value = "/getLogin.api", method = RequestMethod.POST)
	  public ResponseData getLogin(HttpSession session,HttpServletRequest request,@StaffAttribute(Contans.USER) LoginBean user) {
		    String m_strMsg = "";
		    String goUrl = "";
		    try {		    
		      long timestamp = System.currentTimeMillis();		     
		      goUrl = "http://192.168.4.202" + URLEncoder.encode("/#!/statistics/outputStatistics/statisticsOpenedInv/list", "UTF-8");
		      JSONObject employee = new JSONObject();
		      employee.put("userName", openUrlPeople);
		      employee.put("timestamp", Long.valueOf(timestamp));
		      String JsonContent = employee.toString();
		      System.out.println("加密前" + JsonContent);
		      String Content = AesUtil.encrypt(JsonContent);
		      Content = Content.replaceAll("\r|\n", "");

		      Map map = new HashMap();
		      map.put("goUrl", goUrl);
		      map.put("busSysId", "999999");
		      map.put("data", Content);
		      String Jsoninfo = map.toString();
		      Jsoninfo = Jsoninfo.substring(1, Jsoninfo.length() - 1);
		      String Jsoninfo2 = Jsoninfo.replaceAll(", ", "&");

		      m_strMsg =  Jsoninfo2;
		      System.out.println("返回信息" + m_strMsg);	
		      return ResponseData.success(m_strMsg, "单点登录成功");
		    }
		    catch (Exception ex)
		    {
		      m_strMsg = "单点登录失败" + ex.getMessage();
		      ex.printStackTrace();
		      return ResponseData.faill("单点登录失败");
		    }
				   
	  }
	 
    //插入到日志表中
    public void insertLog(InvoiceLogBean logBean) {
    	  //插入到日志表中
		  InvoiceLogBean invoiceLogBean=new InvoiceLogBean();
		  invoiceLogBean.setBegin_time(logBean.getBegin_time());
		  invoiceLogBean.setEnd_time(logBean.getEnd_time());
		  invoiceLogBean.setRequestData(logBean.getRequestData());
		  invoiceLogBean.setResponseData(logBean.getResponseData());
		  invoiceLogBean.setRemoteAddr(logBean.getRemoteAddr());
		  invoiceLogBean.setType(logBean.getType());
		  invoiceLogBean.setUpdate_time(logBean.getUpdate_time());
		  invoiceLogBean.setCreate_time(logBean.getCreate_time());
		  invoiceLogBean.setCreate_name(logBean.getCreate_name());
		  invoiceLogBean.setUpdate_name(logBean.getUpdate_name());		  		  
    }
    //获取现在日期(String类,yyyyMMddHHmmssSSS)
    public static String getNow(){
    	Date now = new Date();    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");//可以方便地修改日期格式
    	String nowDate = dateFormat.format(now); 
    	return nowDate;
    }
  //获取现在日期(String类,yyyyMMdd)
    public static String getYMD(){
    	Date now = new Date();    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//可以方便地修改日期格式
    	String nowDate = dateFormat.format(now); 
    	return nowDate;
    }
    //将例如0000001字符串增加
    public static String getDigitalToStr(String fpbillCode) {
    	//转化为数字
    	int finalfpbillCode=Integer.parseInt(fpbillCode)+1;
    	String finalfpbillCodeStr=finalfpbillCode+"";
        String billCode=new String();
         if(1<=finalfpbillCode && finalfpbillCode<10) {
        	 billCode="000000"+finalfpbillCodeStr;
         }else if(10<=finalfpbillCode && finalfpbillCode<100) {
        	 billCode="00000"+finalfpbillCodeStr;
         }else if(100<=finalfpbillCode && finalfpbillCode<1000) {
        	 billCode="0000"+finalfpbillCodeStr;
         }
         else if(1000<=finalfpbillCode && finalfpbillCode<10000) {
        	 billCode="000"+finalfpbillCodeStr;
         } else if(10000<=finalfpbillCode && finalfpbillCode<100000) {
        	 billCode="00"+finalfpbillCodeStr;
         }else if(100000<=finalfpbillCode && finalfpbillCode<1000000) {
        	 billCode="0"+finalfpbillCodeStr;
         }else {
        	 billCode=finalfpbillCodeStr;
         }
   
		return billCode;
    	
    }
    
    
    //获取现在日期(String类)
    public static String getNowDate(){
    	Date now = new Date();    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
    	String nowDate = dateFormat.format(now); 
    	return nowDate;
    }
    
    
    //获取税额
    public static double getSe(double money) {
    BigDecimal ne = new BigDecimal("1");
    BigDecimal df = new BigDecimal(money);
    money = df.divide(ne, 2, 4).doubleValue();
    return money;
  }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    
    //排序(泛型)
    public static <E> void Sort(List<E> list, final String method, final String sort) {
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object a, Object b) {
				int ret = 0;
				try {
					Method m1 = ((E) a).getClass().getMethod(method, null);
					Method m2 = ((E) b).getClass().getMethod(method, null);
					if (sort != null && "desc".equals(sort))// 倒序
						ret = m2.invoke(((E) b), null).toString()
								.compareTo(m1.invoke(((E) a), null).toString());
					else
						// 正序
						ret = m1.invoke(((E) a), null).toString()
								.compareTo(m2.invoke(((E) b), null).toString());
				} catch (NoSuchMethodException ne) {
					System.out.println(ne);
				} catch (IllegalAccessException ie) {
					System.out.println(ie);
				} catch (InvocationTargetException it) {
					System.out.println(it);
				}
				return  ret;
			}
		});
    }
    
}