package com.korosoft.invoice.mapper.second;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;


@Repository
public interface UnitMapper {

	public List<UnitDataBean> findAllUnit();
	public List<UnitDataBean> findUnitByName(@Param("name") String name);
	public UnitDataBean findById(int id);
	public boolean importUnit(@Param("name") String name,@Param("taxnum") String taxnum,@Param("addr") String addr,@Param("bank") String bank);
	public boolean UnitAdd(UnitDataBean data);
	public boolean deleteUnit(int[] ids);	
	public boolean updateUnit(@Param("data") UnitDataBean data);
	public List<UnitUserBean> findUnitUserById(int id);
	public List<UnitUserBean> findUnitUserByCtmNum(String ctmnum);
	public boolean addUser(int id,String ctmnum);
	public int checkUser(String ctmnum);
	public boolean updateUser(int[] ctmnum);	
}
