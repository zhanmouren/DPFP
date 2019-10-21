package com.korosoft.invoice.service;

import java.util.List;

import com.korosoft.invoice.bean.ProcessBean;


public interface ProcessService {
		
	public List<ProcessBean> findAll(String state);
	public List<ProcessBean> findAllByCtmNum(String state,String ctmnum);
}
