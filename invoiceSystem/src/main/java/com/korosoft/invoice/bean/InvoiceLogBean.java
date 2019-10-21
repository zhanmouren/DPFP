package com.korosoft.invoice.bean;

import java.io.Serializable;
import java.util.Date;

public class InvoiceLogBean implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	//请求开始时间
	private String begin_time;
	//请求结束时间
	private String end_time;
	//请求参数
	private String requestData;
	//ip
	private String remoteAddr;
	//返回参数
	private String responseData;
	
	private Integer type;
	//请求到返回数据所需要的时间
	private String update_time;
	
	private String create_name;
	//请求时间
	private String create_time;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	//请求的类名
	private String className;
	//操作人的token
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCreate_name() {
		return create_name;
	}
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	
	public String getUpdate_name() {
		return update_name;
	}
	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}
	@Override
	public String toString() {
		return "InvoiceLogBean [id=" + id + ", begin_time=" + begin_time + ", end_time=" + end_time + ", requestData="
				+ requestData + ", remoteAddr=" + remoteAddr + ", responseData=" + responseData + ", type=" + type
				+ ", update_time=" + update_time + ", create_name=" + create_name + ", create_time=" + create_time
				+ ", update_name=" + update_name + "]";
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String update_name;
	
}
