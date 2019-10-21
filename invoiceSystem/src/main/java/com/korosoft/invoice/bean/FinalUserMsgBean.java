package com.korosoft.invoice.bean;

import java.util.Date;

public class FinalUserMsgBean {
	
	//用户基本信息表
		//用户号
		   private String hno;
		   
		   private String name;
		   private String cdate;
		   
		   public String getCdate() {
			return cdate;
		}
		public void setCdate(String cdate) {
			this.cdate = cdate;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		//手机
		   private String phone;
		   //地址
		   private String newaddr;
		   //电话
		   private String moblie;
		  
	      //账单历史表
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
		    //三阶水费
		    private double priceoo;
		    //水量
		    private double quanty;
		    //二阶水量
		    private double quantyo;
		    //三阶水量
		    private double quantyoo;
		    
		    public double getPriceoo() {
				return priceoo;
			}
			public void setPriceoo(double priceoo) {
				this.priceoo = priceoo;
			}
			//户名
			private String ctm_name;
			//年月
			private String month_id;
			//发票号码
			private String invoice_num;
			//发票代码
			private String Invoice_code;
			//发票地址
			private String invoice_url;
			//发票流水号
			private String code_num;
			//发票订单号
			private String batch_num;
			//开票人
			private String kp_man;
			//开票时间
			private Date kp_time;
			
			//开票金额
			private double kp_price;
			public double getKp_price() {
				return kp_price;
			}
			public void setKp_price(double kp_price) {
				this.kp_price = kp_price;
			}
			public Date getKp_time() {
				return kp_time;
			}
			public void setKp_time(Date kp_time) {
				this.kp_time = kp_time;
			}
			//电子发票开票
			private Integer dzfp_state;
			//专票打印状态
			private Integer zp_state;
		    
	public String getHno() {
				return hno;
			}
			public void setHno(String hno) {
				this.hno = hno;
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
			public String getCtm_name() {
				return ctm_name;
			}
			public void setCtm_name(String ctm_name) {
				this.ctm_name = ctm_name;
			}
			public String getMonth_id() {
				return month_id;
			}
			public void setMonth_id(String month_id) {
				this.month_id = month_id;
			}
			public String getInvoice_num() {
				return invoice_num;
			}
			public void setInvoice_num(String invoice_num) {
				this.invoice_num = invoice_num;
			}
			public String getInvoice_code() {
				return Invoice_code;
			}
			public void setInvoice_code(String invoice_code) {
				Invoice_code = invoice_code;
			}
			public String getInvoice_url() {
				return invoice_url;
			}
			public void setInvoice_url(String invoice_url) {
				this.invoice_url = invoice_url;
			}
			public String getCode_num() {
				return code_num;
			}
			public void setCode_num(String code_num) {
				this.code_num = code_num;
			}
			public String getBatch_num() {
				return batch_num;
			}
			public void setBatch_num(String batch_num) {
				this.batch_num = batch_num;
			}
			public String getKp_man() {
				return kp_man;
			}
			public void setKp_man(String kp_man) {
				this.kp_man = kp_man;
			}
			
			public Integer getDzfp_state() {
				return dzfp_state;
			}
			public void setDzfp_state(Integer dzfp_state) {
				this.dzfp_state = dzfp_state;
			}
			public Integer getZp_state() {
				return zp_state;
			}
			public void setZp_state(Integer zp_state) {
				this.zp_state = zp_state;
			}
	
	
}
