package com.korosoft.invoice.mapper.second;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.korosoft.invoice.bean.DateYMDBean;
import com.korosoft.invoice.bean.IndexReportBean;
import com.korosoft.invoice.bean.InvoiceLogBean;
import com.korosoft.invoice.bean.InvoiceRecordBean;
import com.korosoft.invoice.bean.InvoiceRedBean;
import com.korosoft.invoice.bean.JbaseBean;
import com.korosoft.invoice.bean.UserMsgResultBean;
import com.korosoft.invoice.bean.UserMsgfpResultBean;

@Repository
public interface InvoiceMapper {
		
	//2019-09-16       根据开始的年月和结束的年月户号查询用户的基本信息(mysql)
	public 	List<UserMsgfpResultBean> getuserfpmsgbyno(JbaseBean jbaseBean);
	
	
	//2019-09-19  开票成功插入到开票记录表中
	public int addIntoInvoice(InvoiceRecordBean invoiceRecordBean);
	
	//插入到日志表中
	public int addLog(InvoiceLogBean invoiceLog);
	 
	 //红冲的时候先删除发票记录
	public int delInvoiceByno(JbaseBean jBaseBean);
	
	//获取最大的订单号()
	public String getMaxBillCode(DateYMDBean todayymd);
	
	//根据年月和户号查询发票记录表中是否存在该用户的发票信息
	public List<UserMsgfpResultBean> getUserFPInfobyno(JbaseBean jbaseBean); 
	
	//插入到红冲表中
	public int addIntoRedInvoice(InvoiceRedBean invoiceRedBean);
	
	 //作废的时候先删除专票记录
	public int delInvoiceBySpecialno(JbaseBean jBaseBean);
	
	//根据年月和户号查询发票记录表中是否存在该用户的发票信息
	public List<UserMsgfpResultBean> getUserzpInfobyno(JbaseBean jbaseBean);
	
	//根据户号，年月，待开票专状态，删除该条待开开票记录
	public int delSpecialInvoiceByzpstate(JbaseBean jBaseBean);
	
	//根据户号，年月，待开票专状态，查询是否存在该条待开开票记录
	public List<UserMsgfpResultBean> getUserDKFPInfo(JbaseBean jbaseBean);
	
	//根据订单号code_num删除该条待开开票记录
	public int delSpecialInvoiceByCodenum(JbaseBean jbaseBean);
	
	//根据发票号码更新字段
	public int updateSpecialInvoicebynum(InvoiceRecordBean invoiceRecordBean);
	
	 //根据开始的年月和结束的年月户号查询用户的专票基本信息(mysql)
	public List<UserMsgfpResultBean> getuserzpmsgbyno(JbaseBean jbaseBean);
	
	//普通发票的统计信息
	public List<IndexReportBean> getCountPTFPMsg();
	//普通发票的红冲统计信息
	public List<IndexReportBean> getCountPTFPRedMsg();
	
	//专票的统计信息
	public List<IndexReportBean> getCountZPMsg();
	
	//专票红冲统计信息
	public List<IndexReportBean> getCountZPRedMsg();
	
	//用户登陆信息
	public List<IndexReportBean> getCountUserMsg(IndexReportBean indexReportBean);
	
	//根据订单号code_num查询该条待开开票记录的开票金额和开票税额
	public List<UserMsgfpResultBean> searchPirceANDTax(JbaseBean jbaseBean);
	
	 //查询最近一次的登录的token
	public List<IndexReportBean> searchLastToken(IndexReportBean indexReportBean);
}
