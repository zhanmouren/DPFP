package com.korosoft.invoice.bean;

import java.util.Date;

//这个bean作为返回结果接收sql的字段（这个是sql server的用户数据信息）
public class UserMsgResultBean {
	//用户基本信息表
	//用户号
	   private String hno;
	   //户名
	   private String name;
	   private String cdate;
	   
	   //大中用户的水单价
	   private double gyuprice;
	   
	   //污水缴费日期
	   private String pwsdate;
	   
	   //污水单价
	   private double pwuprice;
	   
	   public double getPwuprice() {
		return pwuprice;
	}
	public void setPwuprice(double pwuprice) {
		this.pwuprice = pwuprice;
	}
	public String getPwsdate() {
		return pwsdate;
	}
	public void setPwsdate(String pwsdate) {
		this.pwsdate = pwsdate;
	}
	public String getLjsdate() {
		return ljsdate;
	}
	public void setLjsdate(String ljsdate) {
		this.ljsdate = ljsdate;
	}
	//垃圾水缴费日期
	   private String ljsdate;
	   
	   
	   public double getGyuprice() {
		return gyuprice;
	}
	public void setGyuprice(double gyuprice) {
		this.gyuprice = gyuprice;
	}
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
	   
	   //证件类型
	   private String zjlx;
	   //证件号码
	   private String zjno;
	   //证件号码
	   private String email;
	   //纳税人识别号;
	   private String nsid;
	 //银行名称
	   private String bankname;
	   //号码
	   private String bankcount; 
	  
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
	    //水量
	    private double quanty;
	    //二阶水量
	    private double quantyo;
	    public double getPriceoo() {
			return priceoo;
		}
		public void setPriceoo(double priceoo) {
			this.priceoo = priceoo;
		}
		//三阶水量
	    private double quantyoo;
	    //
	    private double priceoo;
	    //
	    
	  
		
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
	
	    
}
