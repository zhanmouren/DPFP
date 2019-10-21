package com.korosoft.invoice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korosoft.invoice.bean.LoginBean;
import com.korosoft.invoice.mapper.master.*;
import com.korosoft.invoice.mapper.second.ProcessMapper;
import com.korosoft.invoice.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    
    @Autowired
    private ProcessMapper processMapper;

	@Override
	public LoginBean login(LoginBean bean) {
		// TODO 自动生成的方法存根
		return loginMapper.login(bean);
	}

	@Override
	public int processCount(LoginBean bean) {       
		return processMapper.processCount(bean);
	}
	
	@Override
	public int PermissionType(LoginBean bean) {       
		return processMapper.PermissionType(bean);
	}

}