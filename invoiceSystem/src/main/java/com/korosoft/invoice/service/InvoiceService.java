package com.korosoft.invoice.service;

import java.util.List;

import com.korosoft.invoice.bean.DateYMDBean;
import com.korosoft.invoice.bean.FinalUserMsgBean;
import com.korosoft.invoice.bean.HistoryBillBean;
import com.korosoft.invoice.bean.IndexReportBean;
import com.korosoft.invoice.bean.InvoiceLogBean;
import com.korosoft.invoice.bean.InvoiceRecordBean;
import com.korosoft.invoice.bean.InvoiceRedBean;
import com.korosoft.invoice.bean.JbaseBean;
import com.korosoft.invoice.bean.UserMsgResultBean;
import com.korosoft.invoice.bean.UserMsgfpResultBean;
import com.korosoft.invoice.vo.ResponseData;

public interface InvoiceService {

	//2019-09-16 //根据开始的年月和结束的年月户号查询用户的基本信息(普通用户)
	List<UserMsgResultBean> getusermsgbyno(JbaseBean jbaseBean);
	
	//2019-10-16 //根据开始的年月和结束的年月户号查询用户的基本信息(普通用户)
	List<UserMsgResultBean> getDZusermsgbyno(JbaseBean jbaseBean);
	
	//2019-09-16       根据开始的年月和结束的年月户号查询用户的基本信息(mysql)
	List<UserMsgfpResultBean> getuserfpmsgbyno(JbaseBean jbaseBean);
	
	//2019-09-18    根据户号和年月查询用户的基本信息和账单信息(普通)
	List<UserMsgResultBean> getUserInfobyno(JbaseBean jbaseBean);
	
	//2019-09-18    根据户号和年月查询用户的基本信息和账单信息(大中)
    List<UserMsgResultBean> getDZUserInfobyno(JbaseBean jbaseBean);
	
	
	//2019-09-19  开票成功插入到开票记录表中
	 int addIntoInvoice(InvoiceRecordBean invoiceRecordBean);
	 
	 //插入日志
	 int addLog(InvoiceLogBean invoiceLog);
	 
	 //红冲的时候先删除发票记录
	 int delInvoiceByno(JbaseBean jBaseBean);
	 
	//获取最大的订单号()
	 String getMaxBillCode(DateYMDBean dateYMDBean);
	 
	 //根据年月和户号查询发票记录表中是否存在该用户的发票信息
	 List<UserMsgfpResultBean> getUserFPInfobyno(JbaseBean jbaseBean); 
	 
	 //红冲的时候插入到红冲表中
	 int addIntoRedInvoice(InvoiceRedBean invoiceRedBean);
	 
	 //作废的时候先删除专票记录
	 int delInvoiceBySpecialno(JbaseBean jBaseBean);
	 
	//根据年月和户号查询发票记录表中是否存在该用户的专票信息
	 List<UserMsgfpResultBean> getUserzpInfobyno(JbaseBean jbaseBean); 
	 
	 //根据户号，年月，待开票专状态，删除该条待开开票记录
	 int delSpecialInvoiceByzpstate(JbaseBean jBaseBean);
	 
	 //根据户号，年月，待开票专状态，查询是否存在该条待开开票记录
	 List<UserMsgfpResultBean>  getUserDKFPInfo(JbaseBean jbaseBean);
	 
	 //根据订单号code_num删除该条待开开票记录
	 int delSpecialInvoiceByCodenum(JbaseBean jbaseBean);
	 
	 //根据发票号码更新字段
	 int updateSpecialInvoicebynum(InvoiceRecordBean invoiceRecordBean);
	 
	 //根据开始的年月和结束的年月户号查询用户的专票基本信息(mysql)
	 List<UserMsgfpResultBean> getuserzpmsgbyno(JbaseBean jbaseBean); 
	 
	 //普通发票的统计信息
	 List<IndexReportBean> getCountPTFPMsg();
	 
	//普通发票的红冲统计信息
	 List<IndexReportBean> getCountPTFPRedMsg();
	
	//查询专票的sql
	 List<IndexReportBean> getCountZPMsg();
	 
	//专票红冲统计信息
	 List<IndexReportBean> getCountZPRedMsg();
	 
	 
	 //查询用户登陆信息
	 List<IndexReportBean> getCountUserMsg(IndexReportBean indexReportBean);
	 
	 //根据户号查询该用户的级别(普通)
	 List<JbaseBean> getuserkpfg(String hno);
	 
	 //根据户号查询该用户的级别(大中)
	 List<JbaseBean> getDZuserkpfg(String hno);
	 
	 
	 //根据订单号code_num查询该条待开开票记录的开票金额和开票税额
	 List<UserMsgfpResultBean> searchPirceANDTax(JbaseBean jbaseBean);
	 
	 //查询最近一次的登录的token
	 List<IndexReportBean> searchLastToken(IndexReportBean indexReportBean);
	 
	 //根据户号查询最近6条账单信息(普通)
	 List<HistoryBillBean> queryHistoryBill(JbaseBean jbaseBean);
	 
	 //根据户号查询最近6条账单信息(大中)
	 List<HistoryBillBean> queryHistoryBigBill(JbaseBean jbaseBean);
	 
	 
	 
}
