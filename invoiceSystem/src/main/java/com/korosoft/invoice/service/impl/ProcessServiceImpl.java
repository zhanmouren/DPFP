package com.korosoft.invoice.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korosoft.invoice.bean.ProcessBean;
import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;
import com.korosoft.invoice.mapper.master.*;
import com.korosoft.invoice.mapper.second.*;
import com.korosoft.invoice.service.ProcessService;
import com.korosoft.invoice.service.UnitService;
import com.korosoft.invoice.vo.ResponseData;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessMapper ProcessMapper;
    


	@Override
	public List<ProcessBean> findAll(String state) {
		// TODO Auto-generated method stub
		return ProcessMapper.findAll(state);
	}

	@Override
	public List<ProcessBean> findAllByCtmNum(String state, String ctmnum) {
		// TODO Auto-generated method stub
		return ProcessMapper.findAllbyCtmNum(state,ctmnum);
	}



	



}