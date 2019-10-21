package com.korosoft.invoice.bean;

import java.sql.Date;

public class UnitDataBean {
	
	private Integer b_id;
	private String ctm_name;
	private String ctm_addr;
	private String tax_num;
	private String link_tel;
	private String bank_name;
	private String bank_num;
	private Integer total; 
	private String create_name;
	private Date create_time;
	
	
	public Integer getB_id() {
		return b_id;
	}
	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}
	public String getCtm_name() {
		return ctm_name;
	}
	public void setCtm_name(String ctm_name) {
		this.ctm_name = ctm_name;
	}
	public String getCtm_addr() {
		return ctm_addr;
	}
	public void setCtm_addr(String ctm_addr) {
		this.ctm_addr = ctm_addr;
	}
	public String getTax_num() {
		return tax_num;
	}
	public void setTax_num(String tax_num) {
		this.tax_num = tax_num;
	}
	public String getLink_tel() {
		return link_tel;
	}
	public void setLink_tel(String link_tel) {
		this.link_tel = link_tel;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_num() {
		return bank_num;
	}
	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}
	public String getCreate_name() {
		return create_name;
	}
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
