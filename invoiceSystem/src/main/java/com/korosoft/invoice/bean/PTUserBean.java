package com.korosoft.invoice.bean;

import java.sql.Date;

public class PTUserBean {
	
	public static final int USER_TYPE = 1;
	
	private Integer user_id;
	private String ctm_num;
	private String ctm_name;
	private String ctm_addr;
	private Integer unit_id;
	private String create_name;
	private Date create_time;
	
	private Integer total; 
	
	private UnitDataBean unitbean;
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
	public String getCtm_addr() {
		return ctm_addr;
	}
	public void setCtm_addr(String ctm_addr) {
		this.ctm_addr = ctm_addr;
	}
	public Integer getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Integer unit_id) {
		this.unit_id = unit_id;
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
	public UnitDataBean getUnitbean() {
		return unitbean;
	}
	public void setUnitbean(UnitDataBean unitbean) {
		this.unitbean = unitbean;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	

	
}
