package com.korosoft.invoice.mapper.second;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.korosoft.invoice.bean.LoginBean;
import com.korosoft.invoice.bean.ProcessBean;
import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;


@Repository
public interface ProcessMapper {

	public List<ProcessBean> findAll(String state_id);
	public List<ProcessBean> findAllbyCtmNum(@Param("state") String state,@Param("ctmnum") String ctmnum);	
	public int processCount(LoginBean bean);
	public int PermissionType(LoginBean bean);
}
