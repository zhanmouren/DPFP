package com.korosoft.invoice.bean;

//这个bean是为了解析extendInfo的值
public class ExtendInfoBean {
	//存放pdf
	private String pdfUrl;

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
}
