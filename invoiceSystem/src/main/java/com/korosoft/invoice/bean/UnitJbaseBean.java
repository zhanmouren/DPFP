package com.korosoft.invoice.bean;

//单位信息的bean
public class UnitJbaseBean {
	//编号
    private String id;
    //单位地址
    private String unit_addr;
    //电话
    private String link_tel;
    //税号
    private String tax_num;
    //银行名称
    private String bank_name;
    //账号
    private String acc_num;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnit_addr() {
		return unit_addr;
	}
	public void setUnit_addr(String unit_addr) {
		this.unit_addr = unit_addr;
	}
	public String getLink_tel() {
		return link_tel;
	}
	public void setLink_tel(String link_tel) {
		this.link_tel = link_tel;
	}
	public String getTax_num() {
		return tax_num;
	}
	public void setTax_num(String tax_num) {
		this.tax_num = tax_num;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(String acc_num) {
		this.acc_num = acc_num;
	}
}
