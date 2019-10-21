package com.korosoft.invoice.bean;

import java.util.Date;

public class InvoiceRecordBean {
	//id
		private Integer id;	
		//户号
		private String ctm_num;
		//户名
		private String ctm_name;
		//年月
		private String month_id;
		//发票号码
		private String invoice_num;
		//发票代码
		private String Invoice_code;
		//发票地址
		private String invoice_url;
		//发票流水号
		private String code_num;
		//发票订单号
		private String batch_num;
		//开票人
		private String kp_man;
		//开票时间
		private String kp_time;
		//电子发票开票
		private Integer dzfp_state;
		
		//开票金额
	    private double kp_price;
	    //开票税额
	    private double kp_taxAmount;
		
		public double getKp_taxAmount() {
			return kp_taxAmount;
		}
		public void setKp_taxAmount(double kp_taxAmount) {
			this.kp_taxAmount = kp_taxAmount;
		}
		public double getKp_price() {
			return kp_price;
		}
		public void setKp_price(double kp_price) {
			this.kp_price = kp_price;
		}
		//专票打印状态
		private Integer zp_state;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCtm_num() {
		return ctm_num;
	}
	public void setCtm_num(String ctm_num) {
		this.ctm_num = ctm_num;
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
		return invoice_num;
	}
	public void setInvoice_num(String invoice_num) {
		this.invoice_num = invoice_num;
	}
	public String getInvoice_code() {
		return Invoice_code;
	}
	public void setInvoice_code(String invoice_code) {
		Invoice_code = invoice_code;
	}
	public String getInvoice_url() {
		return invoice_url;
	}
	public void setInvoice_url(String invoice_url) {
		this.invoice_url = invoice_url;
	}
	public String getCode_num() {
		return code_num;
	}
	public void setCode_num(String code_num) {
		this.code_num = code_num;
	}
	public String getBatch_num() {
		return batch_num;
	}
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}
	public String getKp_man() {
		return kp_man;
	}
	public void setKp_man(String kp_man) {
		this.kp_man = kp_man;
	}

	public String getKp_time() {
		return kp_time;
	}
	public void setKp_time(String kp_time) {
		this.kp_time = kp_time;
	}
	public Integer getDzfp_state() {
		return dzfp_state;
	}
	public void setDzfp_state(Integer dzfp_state) {
		this.dzfp_state = dzfp_state;
	}
	public Integer getZp_state() {
		return zp_state;
	}
	public void setZp_state(Integer zp_state) {
		this.zp_state = zp_state;
	}

}
