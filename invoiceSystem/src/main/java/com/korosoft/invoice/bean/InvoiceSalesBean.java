package com.korosoft.invoice.bean;

import java.util.Date;
import java.util.List;


public class InvoiceSalesBean {
	//单据批次号
	private String billBatchNo;
	//销售单号
	private String billCode;
	//单据日期：yyyy-MM-dd HH:mm:ss 
	private String billDate;
	//发票性质：0(正数)；1(负数)
	private String invoiceNature;
	//购方企业类型
	private String creditType;
	//待开标识，是否自动生成待开发票
	private String invoiceAuto;	
	//发票种类
	private String invoiceType;
	//购方名称
	private String custName;
	//购方纳税人信用代码
	private String custCreditCode;
	//购方地址
	private String custAddress;
	//购方手机
	private String custPhone;
	//购方电话
	private String custMoblie;
	//购方银行账号
	private String custBankAccount;
	//购方开户银行
	private String custBank;
	//购方邮箱
	private String custEmali;
	//收款人
	private String payee;
	//复核人
	private String reviewer;
	//金税盘开票机号
	private String diskCode;
	//开票点编号：
	private String ukeyCode;
	//开票人，必填
	private String openPeople;
	//原销售单号:
	private String oldBillCode;
	//原销售单批次
	private String oldBillBatch;
	//备注
	private String remark;
	
	/**销售单明细*/
	private List<InvoiceSalesDetBean> billDetailApiList;
	
	
	public List<InvoiceSalesDetBean> getBillDetailApiList() {
		return billDetailApiList;
	}
	public void setBillDetailApiList(List<InvoiceSalesDetBean> billDetailApiList) {
		this.billDetailApiList = billDetailApiList;
	}
	public String getBillBatchNo() {
		return billBatchNo;
	}
	public void setBillBatchNo(String billBatchNo) {
		this.billBatchNo = billBatchNo;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getInvoiceNature() {
		return invoiceNature;
	}
	public void setInvoiceNature(String invoiceNature) {
		this.invoiceNature = invoiceNature;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustCreditCode() {
		return custCreditCode;
	}
	public void setCustCreditCode(String custCreditCode) {
		this.custCreditCode = custCreditCode;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMoblie() {
		return custMoblie;
	}
	public void setCustMoblie(String custMoblie) {
		this.custMoblie = custMoblie;
	}
	public String getCustBankAccount() {
		return custBankAccount;
	}
	public void setCustBankAccount(String custBankAccount) {
		this.custBankAccount = custBankAccount;
	}
	public String getCustBank() {
		return custBank;
	}
	public void setCustBank(String custBank) {
		this.custBank = custBank;
	}
	public String getCustEmali() {
		return custEmali;
	}
	public void setCustEmali(String custEmali) {
		this.custEmali = custEmali;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getUkeyCode() {
		return ukeyCode;
	}
	public void setUkeyCode(String ukeyCode) {
		this.ukeyCode = ukeyCode;
	}
	public String getOpenPeople() {
		return openPeople;
	}
	public void setOpenPeople(String openPeople) {
		this.openPeople = openPeople;
	}
	public String getOldBillCode() {
		return oldBillCode;
	}
	public void setOldBillCode(String oldBillCode) {
		this.oldBillCode = oldBillCode;
	}
	
	public String getOldBillBatch() {
		return oldBillBatch;
	}
	public void setOldBillBatch(String oldBillBatch) {
		this.oldBillBatch = oldBillBatch;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	public String getInvoiceAuto() {
		return invoiceAuto;
	}
	public void setInvoiceAuto(String invoiceAuto) {
		this.invoiceAuto = invoiceAuto;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getDiskCode() {
		return diskCode;
	}
	public void setDiskCode(String diskCode) {
		this.diskCode = diskCode;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	
}
