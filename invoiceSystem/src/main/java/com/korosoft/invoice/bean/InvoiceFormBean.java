package com.korosoft.invoice.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InvoiceFormBean {
	//前端传过来的销售单列表(开票)
	private InvoiceSalesBean data;
	//水司id(自己定义)
	
	@NotBlank(message = "字段【group】不能为空")
	private String group;
	//水司使用发票的系统名称
	
	@NotBlank(message = "字段【channel】不能为空")
	private String channel;
	public InvoiceSalesBean getData() {
		return data;
	}
	public void setData(InvoiceSalesBean data) {
		this.data = data;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
