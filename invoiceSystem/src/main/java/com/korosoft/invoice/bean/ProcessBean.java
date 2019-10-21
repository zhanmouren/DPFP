package com.korosoft.invoice.bean;

import java.sql.Date;

public class ProcessBean {
	
	private Integer id;
	private String sub_name;
	private Date sub_time;
	private String process_name;
	private Date process_time;
	private String process_content;
	private String ctm_num;
	private Integer state_id;
	private String create_name;
	private Date create_time;
	private String update_name;
	private Date update_time;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public Date getSub_time() {
		return sub_time;
	}
	public void setSub_time(Date sub_time) {
		this.sub_time = sub_time;
	}
	public String getProcess_name() {
		return process_name;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public Date getProcess_time() {
		return process_time;
	}
	public void setProcess_time(Date process_time) {
		this.process_time = process_time;
	}
	public String getCtm_num() {
		return ctm_num;
	}
	public void setCtm_num(String ctm_num) {
		this.ctm_num = ctm_num;
	}
	public Integer getState_id() {
		return state_id;
	}
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
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
	public String getUpdate_name() {
		return update_name;
	}
	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getProcess_content() {
		return process_content;
	}
	public void setProcess_content(String process_content) {
		this.process_content = process_content;
	}
	

	
	
	

	
}
