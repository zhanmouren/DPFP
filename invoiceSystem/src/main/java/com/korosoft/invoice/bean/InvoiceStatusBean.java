package com.korosoft.invoice.bean;

//发票状态接口参数
public class InvoiceStatusBean {
	private String billType;
	private String value;
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
