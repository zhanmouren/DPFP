package com.korosoft.invoice.bean;

import java.util.Date;

public class InvoiceRedBean {
	private Integer id;
	//户号
	private String ctm_num;
	public String getCtm_num() {
		return ctm_num;
	}
	public void setCtm_num(String ctm_num) {
		this.ctm_num = ctm_num;
	}
	//户名
	private String ctm_name;
	//年月
	private String month_id;
	//发票号码
	private String Invoice_num;
	//发票代码
	private String Invoice_code;
	//发票地址
	private String Invoice_url;
	//作废人
	private String zf_man;
	//作废时间
	private String zf_time;
	//红冲金额
	private double kpred_price;
	//红冲类型(1普票2专票)
	private String red_type;
	//红冲税额
	private double kp_taxAmount;
	public double getKp_taxAmount() {
		return kp_taxAmount;
	}
	public void setKp_taxAmount(double kp_taxAmount) {
		this.kp_taxAmount = kp_taxAmount;
	}
	public String getRed_type() {
		return red_type;
	}
	public void setRed_type(String red_type) {
		this.red_type = red_type;
	}
	public double getKpred_price() {
		return kpred_price;
	}
	public void setKpred_price(double kpred_price) {
		this.kpred_price = kpred_price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCtm_name() {
		return ctm_name;
	}
	public void setCtm_name(String ctm_name) {
		this.ctm_name = ctm_name;
	}
	public String getMonth_id() {
		return month_id;
	}
	public void setMonth_id(String month_id) {
		this.month_id = month_id;
	}
	public String getInvoice_num() {
		return Invoice_num;
	}
	public void setInvoice_num(String invoice_num) {
		Invoice_num = invoice_num;
	}
	public String getInvoice_code() {
		return Invoice_code;
	}
	public void setInvoice_code(String invoice_code) {
		Invoice_code = invoice_code;
	}
	public String getInvoice_url() {
		return Invoice_url;
	}
	public void setInvoice_url(String invoice_url) {
		Invoice_url = invoice_url;
	}
	public String getZf_man() {
		return zf_man;
	}
	public void setZf_man(String zf_man) {
		this.zf_man = zf_man;
	}
	public String getZf_time() {
		return zf_time;
	}
	public void setZf_time(String zf_time) {
		this.zf_time = zf_time;
	}
	

}
