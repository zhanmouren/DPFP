package com.korosoft.invoice.bean;

//销售单的明细表
public class InvoiceSalesDetBean {
	//商品名称
	private String matName;
	//是否含税,0(否);1(是)
	private String isTax;
	//单价
	private double priceMoney;
	//金额
	private double money;
	//税额
	private double taxAmount;
	//税率。如果是折扣行，允许为空；
	private double taxRate;
	//单位
	private String unit;
	//数量
	private String quantity;
	//税收编号
	private String revenueCode;
	//税收版本号。商品行必填
	private String revenueVersion;
	//是否享受优惠政策,0(否);1(是)。商品行必填
	private String isPrivilege;
	//优惠政策类型，享受税收优惠政策内容，也即优惠说明。享受优惠政策必填
	private String privilegeType;
	//零税率标志,0(出口退税);1(免税);2(征税);3(普通零税率）
	private String zeroTax;
	//行性质,0(商品行);1(折扣行)
	private String lineNature;
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getIsTax() {
		return isTax;
	}
	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}
	public double getPriceMoney() {
		return priceMoney;
	}
	public void setPriceMoney(double priceMoney) {
		this.priceMoney = priceMoney;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRevenueCode() {
		return revenueCode;
	}
	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}
	public String getRevenueVersion() {
		return revenueVersion;
	}
	public void setRevenueVersion(String revenueVersion) {
		this.revenueVersion = revenueVersion;
	}
	public String getIsPrivilege() {
		return isPrivilege;
	}
	public void setIsPrivilege(String isPrivilege) {
		this.isPrivilege = isPrivilege;
	}
	public String getPrivilegeType() {
		return privilegeType;
	}
	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}
	public String getZeroTax() {
		return zeroTax;
	}
	public void setZeroTax(String zeroTax) {
		this.zeroTax = zeroTax;
	}
	public String getLineNature() {
		return lineNature;
	}
	public void setLineNature(String lineNature) {
		this.lineNature = lineNature;
	}
}
