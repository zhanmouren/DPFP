package com.korosoft.invoice.bean;

import java.util.Date;
import java.util.List;

//这个bean 作为返回接收json转化为bean的一个
public class InvoiceResultDataBean {
	//户名
	private String custName;
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	//发票号码
	private String invoiceNumber;
	
	//发票代码
	private String  invoiceCode;
	//发票地址
	private String extendInfo;
    //开票时间	
	private Date invoiceDate;
	//

	public List<InvoiceDetailListBean> getInvoiceDetailList() {
		return invoiceDetailList;
	}
	public void setInvoiceDetailList(List<InvoiceDetailListBean> invoiceDetailList) {
		this.invoiceDetailList = invoiceDetailList;
	}
	//
	private List<InvoiceDetailListBean> invoiceDetailList;
	
	private List<InvoiceDetailListBean> billDetailList;
	public List<InvoiceDetailListBean> getBillDetailList() {
		return billDetailList;
	}
	public void setBillDetailList(List<InvoiceDetailListBean> billDetailList) {
		this.billDetailList = billDetailList;
	}
	
}
