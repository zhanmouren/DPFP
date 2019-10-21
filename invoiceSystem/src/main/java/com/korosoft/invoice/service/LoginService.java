package com.korosoft.invoice.service;


import com.korosoft.invoice.bean.LoginBean;

public interface LoginService {
	
	/**
     * 判断用户登录
     * @param String loginname
     * @param String password
     * @return 找到返回User对象，没有找到返回null
     * */
	LoginBean login(LoginBean bean);
	int processCount(LoginBean bean);
	int PermissionType(LoginBean bean);
		
}
