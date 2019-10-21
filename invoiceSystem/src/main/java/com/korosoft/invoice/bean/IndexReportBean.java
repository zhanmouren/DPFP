package com.korosoft.invoice.bean;

//这个类作为电子发票的首页bean
public class IndexReportBean {
      //发票的的种类	 
	  private String invoiceType;
	  //开票的数量
	  private Integer kpNum;
	  //开票金额
	  private double  kpPrice;
	  //开票税额
	  private double kpShuiER;
	  //红冲数量
	  private Integer redNum;
	  //红冲金额
	  private double  kpRedPrice;
	  //红冲税额
	  private double  kpRedShuiER;
	  
	  //上次登陆ip
	  private String lastIP;
	  //上次登陆时间
	  private String lastLoginTime;
	  //登陆次数
	  private Integer loginNum;
	  
	  //token
	  private String token;
	  
	  public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	//登陆者
	  private String loginName;
	 public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public Integer getKpNum() {
		return kpNum;
	}
	public void setKpNum(Integer kpNum) {
		this.kpNum = kpNum;
	}
	public double getKpPrice() {
		return kpPrice;
	}
	public void setKpPrice(double kpPrice) {
		this.kpPrice = kpPrice;
	}
	public double getKpShuiER() {
		return kpShuiER;
	}
	public void setKpShuiER(double kpShuiER) {
		this.kpShuiER = kpShuiER;
	}
	public Integer getRedNum() {
		return redNum;
	}
	public void setRedNum(Integer redNum) {
		this.redNum = redNum;
	}
	public double getKpRedPrice() {
		return kpRedPrice;
	}
	public void setKpRedPrice(double kpRedPrice) {
		this.kpRedPrice = kpRedPrice;
	}
	public double getKpRedShuiER() {
		return kpRedShuiER;
	}
	public void setKpRedShuiER(double kpRedShuiER) {
		this.kpRedShuiER = kpRedShuiER;
	}
	  
}
