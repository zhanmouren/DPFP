package com.korosoft.invoice.mapper.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.korosoft.invoice.bean.HistoryBillBean;
import com.korosoft.invoice.bean.JbaseBean;
import com.korosoft.invoice.bean.UnitDataBean;
import com.korosoft.invoice.bean.UnitUserBean;
import com.korosoft.invoice.bean.UserMsgResultBean;


@Repository
public interface UserMapper {

	public List<UnitUserBean> findUserByCtmNum(String ctmnum);
	
	//根据户号与开票期间查询用户的基本信息(普通用户)
	public List<UserMsgResultBean> getusermsgbyno(JbaseBean jbaseBean);
	//根据户号与开票期间查询用户的基本信息(大中用户)
	public List<UserMsgResultBean> getDZusermsgbyno(JbaseBean jbaseBean);
	
	//根据户号与开票日期查询用户的基本信息(普通用户)
	public List<UserMsgResultBean> getUserInfobyno(JbaseBean jbaseBean);
	//根据户号与开票日期查询用户的基本信息(大中用户)
	public List<UserMsgResultBean> getDZUserInfobyno(JbaseBean jbaseBean);
	
	//根据户号查询该用户的级别(普通)
	public List<JbaseBean> getuserkpfg(String hno);
	
	//根据户号查询该用户的级别(大中)
	public List<JbaseBean> getDZuserkpfg(String hno);
		
	//根据户号查询最近6条账单信息(普通)
	public List<HistoryBillBean> queryHistoryBill(JbaseBean jbaseBean);
	
	//根据户号查询最近6条账单信息(大中)
	public List<HistoryBillBean> queryHistoryBigBill(JbaseBean jbaseBean);
	
	
	
}
