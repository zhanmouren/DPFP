package com.korosoft.invoice.authority;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.korosoft.invoice.authority.annotation.LoginToken;
import com.korosoft.invoice.bean.IndexReportBean;
import com.korosoft.invoice.service.Contans;
import com.korosoft.invoice.service.InvoiceService;
import com.korosoft.invoice.util.JwtUtils;
import com.korosoft.invoice.vo.ResponseData;

/**
 * 登录权限验证
 * @author 59532
 *
 */
@Aspect
@Component
public class LoginAuthorizeAspect {

    @Autowired
    private InvoiceService invoiceService;
	
	@Pointcut("@annotation(com.korosoft.invoice.authority.annotation.LoginToken)")
	public void cut() {
		
	}

	@Around("cut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature sig = (MethodSignature) point.getSignature();
		Method method = sig.getMethod();
		LoginToken methodAnnotation = method.getAnnotation(LoginToken.class);
		if(methodAnnotation != null) {
			ResponseData data = ResponseData.faill(1001, "会话已过期,请重新登录！");
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String token = request.getHeader(Contans.TOKEN);
			if(token == null || "null".equals(token) || "".equals(token) || token=="") {
				return data;
			}
			boolean isExpired = JwtUtils.isExpired(token);
			if(isExpired) {
				return data;
			}		
			//获取用户名
		      HttpSession session = request.getSession();
		        String loginName="";
		        if(session.getAttribute("loginName")!=null) {
		        	 loginName=session.getAttribute("loginName").toString();
		        }
		        IndexReportBean indexReportBean=new IndexReportBean();
		        indexReportBean.setLoginName(loginName);
			//查询前端带过来的token是否和最近登录一次的是否一致(根据用户名)
		        List<IndexReportBean> indexReportBeanList=invoiceService.searchLastToken(indexReportBean);
		        if(indexReportBeanList.size()>0) {
		        	 if(!token.equals(indexReportBeanList.get(0).getToken())) {
		        		 return data;
		        	 }
		        }
		      
			
		}
		return point.proceed();
	}
}
