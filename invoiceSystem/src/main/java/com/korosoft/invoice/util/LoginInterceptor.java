package com.korosoft.invoice.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
          
        Object user = request.getSession().getAttribute("user");
        //用户名为空
        if (user == null || user.equals(""))  {
            response.sendRedirect("login.jsp");  
            return false;  
        }
        //
        return true;
    }  
}