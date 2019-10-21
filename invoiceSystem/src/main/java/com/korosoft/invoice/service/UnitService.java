package com.korosoft.invoice.service;

import java.util.List;

import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;
import com.korosoft.invoice.vo.ResponseData;


public interface UnitService {
		
	public List<UnitDataBean> findAllUnit();
	public List<UnitDataBean> findUnitByName(String Name);
	public UnitDataBean findById(int id);
	public ResponseData  ImportUnit(String filePath);
	public ResponseData  UnitAdd(UnitDataBean data);
	public ResponseData  deleteUnit(int[] id);
	public ResponseData  updateUnit(UnitDataBean data);
	public List<UnitUserBean> findUnitUserById(int id);
	public List<UnitUserBean> findUnitUserByCtmNum(String ctmnum);
	public ResponseData  addUser(int id,String ctmnum);
	public ResponseData  updateUser(int[] ctmnum);
}
