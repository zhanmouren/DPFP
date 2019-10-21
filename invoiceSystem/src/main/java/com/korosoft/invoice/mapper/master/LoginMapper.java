package com.korosoft.invoice.mapper.master;

import org.springframework.stereotype.Repository;

import com.korosoft.invoice.bean.LoginBean;


public interface LoginMapper {

	public void save(LoginBean login);
	
	public LoginBean login(LoginBean bean);

}
