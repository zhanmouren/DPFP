package com.korosoft.invoice.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.korosoft.invoice.config.MyLog;
import com.korosoft.invoice.service.InvoiceService;
import com.korosoft.invoice.util.LoginInterceptor;
import com.korosoft.invoice.vo.ResponseData;
import com.korosoft.invoice.bean.InvoiceLogBean;

import com.korosoft.invoice.config.HttpContextUtils;
import com.korosoft.invoice.config.IPUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.fastjson.JSONObject;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private InvoiceService invoiceService;
    
    String startTime=new String();
    String jsonString=new String();
    String description=new String();

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.korosoft.invoice.config.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        InvoiceLogBean sysLog = new InvoiceLogBean();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
		
        //注解上的名称
        String opevalue =new String();
		  if (myLog != null) { 
			  //注解上的描述
			  opevalue = myLog.value();
		  }
		  sysLog.setUpdate_name(opevalue);//保存获取的操作 }
		 

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名		
		String methodName = method.getName();
		
		sysLog.setClassName(className + "." + methodName);
		 
		 

        //请求的参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        
        //将参数所在的数组转换成json
        String params = "";
        if (arguments != null) {
        	try {
        		params = JSONObject.toJSONString(arguments);
            } catch (Exception e) {
            	params = arguments.toString();
            }
        }
        sysLog.setRequestData(params);

        sysLog.setBegin_time(startTime);
        
        sysLog.setEnd_time(getNowDate());
        
        sysLog.setCreate_time(getNowDate());
        
        sysLog.setResponseData("返回结果："+description+" 返回值："+jsonString);
        //插入token
        String token="";
        if(!"".equals(jsonString) && jsonString!=null && !"null".equals(jsonString)  && "登陆".equals(opevalue)) {
        	JSONObject jsonObj2=JSONObject.parseObject(jsonString);
        	 token=jsonObj2.getString("token");      	
        }
        sysLog.setToken(token);
        
        String updateTime=gapTime(startTime,getNowDate());
        sysLog.setUpdate_time(updateTime);
        //获取用户名
       // sysLog.setUsername(ShiroUtils.getUserEntity().getUsername());
       // getUserName(new HttpSession());
       // (User)WebUtils.getSessionAttribute(request, "user");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String loginName="";
        if(session.getAttribute("loginName")!=null) {
        	 loginName=session.getAttribute("loginName").toString();
        }
        sysLog.setCreate_name(loginName);
        //获取用户ip地址
        HttpServletRequest request1 = HttpContextUtils.getHttpServletRequest();
        sysLog.setRemoteAddr(IPUtils.getIpAddr(request1));

        //调用service保存SysLog实体类到数据库
        invoiceService.addLog(sysLog);
    }
    
    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPoinCut()")
    public void doAfterReturning(ResponseData ret) {
    	description=ret.getDescription();
        System.out.println("方法的返回值 : " + ret.getDescription());
        System.out.println("方法的返回值 : " + ret.getData());  
       // Object jsonObject=JSONObject.toJSON(ret.getData());
         jsonString=JSONObject.toJSONString(ret.getData());
      //  System.out.println("方法的返回值 : " + jsonObject);  
        System.out.println("方法的返回值 : " + jsonString); 
    }
    /**
	  * 处理前
	 * 
	 */
    @Before("logPoinCut()")
    public void dobefore() {
      System.out.println("处理前");    
      startTime=getNowDate();
    }
    
   //获取现在日期(String类)
    public static String getNowDate(){
    	Date now = new Date();    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
    	String nowDate = dateFormat.format(now); 
    	return nowDate;
    }
    
    //获取两个时间差
    public static String gapTime(String startTime,String endTime)  {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1=null;
		try {
			date1 = sdf.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2=null;
		try {
			date2 = sdf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long l = date2.getTime() - date1.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String finalTime=day + "天" + hour + "小时" + min +"分" + s + "秒";
		return finalTime;
    }
    public  static String getUserName(HttpSession session) {
    	String loginName=session.getAttribute("loginName").toString();
		return loginName;
      }
    

}
