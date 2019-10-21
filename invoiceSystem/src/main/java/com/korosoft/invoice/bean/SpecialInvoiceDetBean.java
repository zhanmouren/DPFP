package com.korosoft.invoice.bean;

//这个bean是发票销售订单明细的bean
public class SpecialInvoiceDetBean {
	//业务系统明细ID
	private String serialId;
	//商品编码
	private String matCode;
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
	//税率
	private double taxRate;
	//规格型号
	private String spec;
	//单位
	private String unit;
	//数量
	private String quantity;
	//税收编码
	private String revenueCode;
	//企业自编码
	private String ownCoding;
	//税收名称
	private String revenueName;
	//税收版本号
	private String revenueVersion;
	//是否享受优惠政策,0(否);1(是)
	private String isPrivilege;
	//优惠政策类型
	private String privilegeType;
	//零税率标志
	private String zeroTax;
	
	
	
	//折扣行数
	private String discountNo;
	//行性质,0(商品行);1(折扣行)
	private String lineNature;
	//扩展内容
	private String extendInfo;
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
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
	public String getOwnCoding() {
		return ownCoding;
	}
	public void setOwnCoding(String ownCoding) {
		this.ownCoding = ownCoding;
	}
	public String getRevenueName() {
		return revenueName;
	}
	public void setRevenueName(String revenueName) {
		this.revenueName = revenueName;
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

	
	
	public String getDiscountNo() {
		return discountNo;
	}
	public void setDiscountNo(String discountNo) {
		this.discountNo = discountNo;
	}
	public String getLineNature() {
		return lineNature;
	}
	public void setLineNature(String lineNature) {
		this.lineNature = lineNature;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
}
