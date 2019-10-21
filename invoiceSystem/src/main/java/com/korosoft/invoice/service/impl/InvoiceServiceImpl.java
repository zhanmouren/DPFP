package com.korosoft.invoice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.korosoft.invoice.mapper.master.UserMapper;
import com.korosoft.invoice.mapper.second.InvoiceMapper;
import com.korosoft.invoice.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceMapper  invoiceMapper;
	
	@Autowired
	private UserMapper  userMapper;
	
	@Override
	public List<UserMsgResultBean> getusermsgbyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return userMapper.getusermsgbyno(jbaseBean);
	}

	@Override
	public List<UserMsgfpResultBean> getuserfpmsgbyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.getuserfpmsgbyno(jbaseBean);
	}

	@Override
	public List<UserMsgResultBean> getUserInfobyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return userMapper.getUserInfobyno(jbaseBean);
	}

	@Override
	public int addIntoInvoice(InvoiceRecordBean invoiceRecordBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.addIntoInvoice(invoiceRecordBean);
	}

	@Override
	public int addLog(InvoiceLogBean invoiceLog) {
		// TODO Auto-generated method stub
		return invoiceMapper.addLog(invoiceLog);
	}

	@Override
	public int delInvoiceByno(JbaseBean jBaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.delInvoiceByno(jBaseBean);
	}

	@Override
	public String getMaxBillCode(DateYMDBean dateYMDBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.getMaxBillCode(dateYMDBean);
	}

	@Override
	public List<UserMsgfpResultBean> getUserFPInfobyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.getUserFPInfobyno(jbaseBean);
	}

	@Override
	public int addIntoRedInvoice(InvoiceRedBean invoiceRedBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.addIntoRedInvoice(invoiceRedBean);
	}

	@Override
	public int delInvoiceBySpecialno(JbaseBean jBaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.delInvoiceBySpecialno(jBaseBean);
	}

	@Override
	public List<UserMsgfpResultBean> getUserzpInfobyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.getUserzpInfobyno(jbaseBean);
	}

	@Override
	public int delSpecialInvoiceByzpstate(JbaseBean jBaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.delSpecialInvoiceByzpstate(jBaseBean);
	}

	@Override
	public List<UserMsgfpResultBean> getUserDKFPInfo(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.getUserDKFPInfo(jbaseBean);
	}

	@Override
	public int delSpecialInvoiceByCodenum(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.delSpecialInvoiceByCodenum(jbaseBean);
	}

	@Override
	public int updateSpecialInvoicebynum(InvoiceRecordBean invoiceRecordBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.updateSpecialInvoicebynum(invoiceRecordBean);
	}

	@Override
	public List<UserMsgfpResultBean> getuserzpmsgbyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.getuserzpmsgbyno(jbaseBean);
	}

	//普通发票的统计信息
	@Override
	public List<IndexReportBean> getCountPTFPMsg() {
		// TODO Auto-generated method stub
		return invoiceMapper.getCountPTFPMsg();
	}

	@Override
	public List<IndexReportBean> getCountZPMsg() {
		// TODO Auto-generated method stub
		return invoiceMapper.getCountZPMsg();
	}

	@Override
	public List<IndexReportBean> getCountUserMsg(IndexReportBean indexReportBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.getCountUserMsg(indexReportBean);
	}

	@Override
	public List<IndexReportBean> getCountPTFPRedMsg() {
		// TODO Auto-generated method stub
		return invoiceMapper.getCountPTFPRedMsg();
	}

	@Override
	public List<IndexReportBean> getCountZPRedMsg() {
		// TODO Auto-generated method stub
		return invoiceMapper.getCountZPRedMsg();
	}

	@Override
	public List<JbaseBean> getuserkpfg(String hno) {
		// TODO Auto-generated method stub
		return userMapper.getuserkpfg(hno);
	}

	@Override
	public List<UserMsgfpResultBean> searchPirceANDTax(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.searchPirceANDTax(jbaseBean);
	}

	@Override
	public List<IndexReportBean> searchLastToken(IndexReportBean indexReportBean) {
		// TODO Auto-generated method stub
		return invoiceMapper.searchLastToken(indexReportBean);
	}

	@Override
	public List<HistoryBillBean> queryHistoryBill(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return userMapper.queryHistoryBill(jbaseBean);
	}

	@Override
	public List<HistoryBillBean> queryHistoryBigBill(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return userMapper.queryHistoryBigBill(jbaseBean);
	}

	@Override
	public List<JbaseBean> getDZuserkpfg(String hno) {
		// TODO Auto-generated method stub
		return userMapper.getDZuserkpfg(hno);
	}

	@Override
	public List<UserMsgResultBean> getDZusermsgbyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return userMapper.getDZusermsgbyno(jbaseBean);
	}

	@Override
	public List<UserMsgResultBean> getDZUserInfobyno(JbaseBean jbaseBean) {
		// TODO Auto-generated method stub
		return userMapper.getDZUserInfobyno(jbaseBean);
	}

}
