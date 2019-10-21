package com.korosoft.invoice.bean;

//账单历史表
public class JhistoryBean {
    private String hno;
    //水费
    private double price;
    //滞纳金
    private double cnj;
    //污水费
    private double pwf;
    //垃圾费
    private double ljprice;
    //单价
    private double uprice;
    //二阶水费
    private double priceo;
    //水量
    private double quanty;
    //二阶水量
    private double quantyo;
    //三阶水量
    private double quantyoo;
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
	public double getCnj() {
		return cnj;
	}
	public void setCnj(double cnj) {
		this.cnj = cnj;
	}
	public double getPwf() {
		return pwf;
	}
	public void setPwf(double pwf) {
		this.pwf = pwf;
	}
	public double getLjprice() {
		return ljprice;
	}
	public void setLjprice(double ljprice) {
		this.ljprice = ljprice;
	}
	public double getUprice() {
		return uprice;
	}
	public void setUprice(double uprice) {
		this.uprice = uprice;
	}
	public double getPriceo() {
		return priceo;
	}
	public void setPriceo(double priceo) {
		this.priceo = priceo;
	}
	public double getQuanty() {
		return quanty;
	}
	public void setQuanty(double quanty) {
		this.quanty = quanty;
	}
	public double getQuantyo() {
		return quantyo;
	}
	public void setQuantyo(double quantyo) {
		this.quantyo = quantyo;
	}
	public double getQuantyoo() {
		return quantyoo;
	}
	public void setQuantyoo(double quantyoo) {
		this.quantyoo = quantyoo;
	}
	public double getPriceoo() {
		return priceoo;
	}
	public void setPriceoo(double priceoo) {
		this.priceoo = priceoo;
	}
	//三阶水费
    private double priceoo;
    
}
