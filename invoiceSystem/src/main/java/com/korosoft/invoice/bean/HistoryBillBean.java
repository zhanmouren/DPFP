package com.korosoft.invoice.bean;

//这个bean作为账单查询接口返回的resultBean
public class HistoryBillBean {
    //户号
	private String hno;
	//垃圾费
	private double ljprice;
	public double getLjprice() {
		return ljprice;
	}
	public void setLjprice(double ljprice) {
		this.ljprice = ljprice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNsid() {
		return nsid;
	}
	public void setNsid(String nsid) {
		this.nsid = nsid;
	}
	public String getMoney_state() {
		return money_state;
	}
	public void setMoney_state(String money_state) {
		this.money_state = money_state;
	}
	public double getTotal_money() {
		return total_money;
	}
	public void setTotal_money(double total_money) {
		this.total_money = total_money;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	//户名
	private String name;
	//地址
	private String addr;
	//电话
	private String phone;
	//纳税人识别号
	private String nsid;
	//缴费状态
	private String money_state;
	//总金额
	private double total_money;
	//账单日期
	private String cdate;
	//水费
	private double price;
	//二阶水费
	private double priceo;
	//三阶水费
	private double priceoo;
	//污水处理费
	private double pwf;
	//水费违约金
	private double cnj;
	//污水滞纳金
	private double pwcnj;
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPriceo() {
		return priceo;
	}
	public void setPriceo(double priceo) {
		this.priceo = priceo;
	}
	public double getPriceoo() {
		return priceoo;
	}
	public void setPriceoo(double priceoo) {
		this.priceoo = priceoo;
	}
	public double getPwf() {
		return pwf;
	}
	public void setPwf(double pwf) {
		this.pwf = pwf;
	}
	public double getCnj() {
		return cnj;
	}
	public void setCnj(double cnj) {
		this.cnj = cnj;
	}
	public double getPwcnj() {
		return pwcnj;
	}
	public void setPwcnj(double pwcnj) {
		this.pwcnj = pwcnj;
	}
	
}
