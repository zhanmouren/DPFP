package com.korosoft.invoice.authority;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.korosoft.invoice.authority.annotation.Validation;
import com.korosoft.invoice.service.Contans;
import com.korosoft.invoice.vo.ResponseData;

@Aspect
@Component
public class TimestampValidationAspect {
	
	@Value("${app.minute}")
	protected int minute; 
	
	@Pointcut("@annotation(com.korosoft.invoice.authority.annotation.Validation)")
	public void cut() {
		
	}
	
	@Around("cut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature sig = (MethodSignature) point.getSignature();
		Method method = sig.getMethod();
		Validation methodAnnotation = method.getAnnotation(Validation.class);
		if(methodAnnotation != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String timestamp = request.getHeader(Contans.TIMESRAMP);
			if(StringUtils.isBlank(timestamp)) {
				return ResponseData.faill("请在请求头中设置【" + Contans.TIMESRAMP + "】");
			}
			try {
				Long t1 = System.currentTimeMillis();
				Long t2 = Long.parseLong(timestamp);
				if((t1 - t2) / 60000 > minute) {
					return ResponseData.faill("无效的请求");
				};
			}catch(NumberFormatException ex) {
				return ResponseData.faill("请设置正确的时间戳格式");
			}
		}
		return point.proceed();
	}
}
