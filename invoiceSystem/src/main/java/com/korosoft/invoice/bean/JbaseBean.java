package com.korosoft.invoice.bean;

import java.util.Date;

//用户信息表(可以作为参数bean使用)
public class JbaseBean {
	//用户号
   private String hno;
   public String getHno() {
	return hno;
}
public void setHno(String hno) {
	this.hno = hno;
}
//手机
   private String phone;
   //地址
   private String newaddr;
   //电话
   private String moblie;
   //开始日期(查询)
   private String startTime;
   //结束日期(查询)
   private String endTime;
   //年月
   private String month_id;
   //电子开票状态
   private Integer dzfp_state;
   //证件类型
   private String zjlx;
   //证件号码
   private String zjno;
   //证件号码
   private String email;
   //纳税人识别号;
   private String nsid;
   //发票性质(0正数代表开票，1代表红冲)
   private String invoiceNature;
   //发票种类(0(专票);2(普票);51(电子票))
   private String invoiceType;
   
   //发票销售单号
   private String code_num;
   
   //用户类型
   private String kpfg;
   
   //当前页数
   private Integer page;
   //每页条数
   private Integer pageCount;
  

public Integer getPage() {
	return page;
}
public void setPage(Integer page) {
	this.page = page;
}
public Integer getPageCount() {
	return pageCount;
}
public void setPageCount(Integer pageCount) {
	this.pageCount = pageCount;
}
public String getKpfg() {
	return kpfg;
}
public void setKpfg(String kpfg) {
	this.kpfg = kpfg;
}
public String getCode_num() {
	return code_num;
}
public void setCode_num(String code_num) {
	this.code_num = code_num;
}
public String getInvoiceType() {
	return invoiceType;
}
public void setInvoiceType(String invoiceType) {
	this.invoiceType = invoiceType;
}
public String getInvoiceNature() {
	return invoiceNature;
}
public void setInvoiceNature(String invoiceNature) {
	this.invoiceNature = invoiceNature;
}
public String getZjlx() {
	return zjlx;
}
public void setZjlx(String zjlx) {
	this.zjlx = zjlx;
}
public String getZjno() {
	return zjno;
}
public void setZjno(String zjno) {
	this.zjno = zjno;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getNsid() {
	return nsid;
}
public void setNsid(String nsid) {
	this.nsid = nsid;
}
public String getBankname() {
	return bankname;
}
public void setBankname(String bankname) {
	this.bankname = bankname;
}
public String getBankcount() {
	return bankcount;
}
public void setBankcount(String bankcount) {
	this.bankcount = bankcount;
}
private String bankname;
   //银行名称
   private String bankcount; 
   //日期类型的年月
   private Date monthDate;
   
   



public Date getMonthDate() {
	return monthDate;
}
public void setMonthDate(Date monthDate) {
	this.monthDate = monthDate;
}
public Integer getDzfp_state() {
	return dzfp_state;
}
public void setDzfp_state(Integer dzfp_state) {
	this.dzfp_state = dzfp_state;
}



public String getMonth_id() {
	return month_id;
}
public void setMonth_id(String month_id) {
	this.month_id = month_id;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}

public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getNewaddr() {
	return newaddr;
}
public void setNewaddr(String newaddr) {
	this.newaddr = newaddr;
}
public String getMoblie() {
	return moblie;
}
public void setMoblie(String moblie) {
	this.moblie = moblie;
}
}
