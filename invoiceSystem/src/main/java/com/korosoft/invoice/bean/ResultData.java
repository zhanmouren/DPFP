package com.korosoft.invoice.bean;

//这个作为是否成功的bean
public class ResultData {
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public int getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(int msgCode) {
		this.msgCode = msgCode;
	}


	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	private String message;
	
	
	private int msgCode;
	
	private String data;
	
	private Integer page;
	
	private Integer total;
	
	//封装跳转的url
	private String goUrl;
	
	

	public String getGoUrl() {
		return goUrl;
	}

	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}
}
