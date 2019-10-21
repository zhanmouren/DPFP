package com.korosoft.invoice.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SpecialFormBean {
	//前端传过来的销售单列表(开票)
		private SpecialInvoiceBean data;
		//水司id(自己定义)
		
		@NotNull(message = "字段【group】不能为空")
		private String group;
		//水司使用发票的系统名称
		
		public SpecialInvoiceBean getData() {
			return data;
		}

		public void setData(SpecialInvoiceBean data) {
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

		@NotBlank(message = "字段【channel】不能为空")
		private String channel;
}
