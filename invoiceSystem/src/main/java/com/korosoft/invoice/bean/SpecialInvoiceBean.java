package com.korosoft.invoice.bean;

import java.util.Date;
import java.util.List;

//专票销售单的bean
public class SpecialInvoiceBean {
	private String billBatchNo;
	
	//销售单号
	private String billCode;
	//单据日期：yyyy-MM-dd HH:mm:ss 
	private String billDate;
	//发票种类：0(专票);2(普票);51(电子票)
	private String invoiceType;
	//发票性质：0(正数)；1(负数)
	private String invoiceNature;
	//申请人
	private String applicant;
	//申请日期
	private Date applyDate;
	//开票机构编码：E税云维护
	private String invoiceOrgCode;
	//购方名称
	private String custName;
	//购方纳税人信用代码
	private String custCreditCode;
	//购方地址
	private String custAddress;
	//购方省份
	private String custProvince;
	//购方电话
	private String custPhone;
	//购方手机
	private String custMobile;
	//购方银行账号
	private String custBankAccount;
	//购方开户银行
	private String custBank;
	//购方邮箱
	private String custEmail;
	//购方企业类型
	private String creditType;
	
	//行业名称
	private String industryName;
	//是否含税,0(否);1(是)
	private String isTax;
	//收款人
	private String payee;
	//复核人
	private String reviewer;
	//开票服务器（金税盘）编号：E税云维护
	private String diskCode;
	//开票点编号：E税云维护
	private String ukeyCode;
	//待开标识，是否自动生成待开发票
	private String invoiceAuto;
	//自动处理规则方案编码：E税云维护
	private String ruleSchemaCode;
	//业务系统编号
	private String busSysId;
	//业务系统名称
	private String busSysName;
	//项目ID
	private String projectId;
	//项目名称
	private String projectName;
	public String getBillBatchNo() {
		return billBatchNo;
	}
	public void setBillBatchNo(String billBatchNo) {
		this.billBatchNo = billBatchNo;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
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
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getInvoiceOrgCode() {
		return invoiceOrgCode;
	}
	public void setInvoiceOrgCode(String invoiceOrgCode) {
		this.invoiceOrgCode = invoiceOrgCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustCreditCode() {
		return custCreditCode;
	}
	public void setCustCreditCode(String custCreditCode) {
		this.custCreditCode = custCreditCode;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustProvince() {
		return custProvince;
	}
	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public String getCustBankAccount() {
		return custBankAccount;
	}
	public void setCustBankAccount(String custBankAccount) {
		this.custBankAccount = custBankAccount;
	}
	public String getCustBank() {
		return custBank;
	}
	public void setCustBank(String custBank) {
		this.custBank = custBank;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getIsTax() {
		return isTax;
	}
	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getDiskCode() {
		return diskCode;
	}
	public void setDiskCode(String diskCode) {
		this.diskCode = diskCode;
	}
	public String getUkeyCode() {
		return ukeyCode;
	}
	public void setUkeyCode(String ukeyCode) {
		this.ukeyCode = ukeyCode;
	}
	public String getInvoiceAuto() {
		return invoiceAuto;
	}
	public void setInvoiceAuto(String invoiceAuto) {
		this.invoiceAuto = invoiceAuto;
	}
	public String getRuleSchemaCode() {
		return ruleSchemaCode;
	}
	public void setRuleSchemaCode(String ruleSchemaCode) {
		this.ruleSchemaCode = ruleSchemaCode;
	}
	public String getBusSysId() {
		return busSysId;
	}
	public void setBusSysId(String busSysId) {
		this.busSysId = busSysId;
	}
	public String getBusSysName() {
		return busSysName;
	}
	public void setBusSysName(String busSysName) {
		this.busSysName = busSysName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSpecialRedFlag() {
		return specialRedFlag;
	}
	public void setSpecialRedFlag(String specialRedFlag) {
		this.specialRedFlag = specialRedFlag;
	}
	public String getReplacementSign() {
		return replacementSign;
	}
	public void setReplacementSign(String replacementSign) {
		this.replacementSign = replacementSign;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getRushRedReason() {
		return rushRedReason;
	}
	public void setRushRedReason(String rushRedReason) {
		this.rushRedReason = rushRedReason;
	}
	public String getRedInfoNumber() {
		return redInfoNumber;
	}
	public void setRedInfoNumber(String redInfoNumber) {
		this.redInfoNumber = redInfoNumber;
	}
	public String getOldBillCode() {
		return oldBillCode;
	}
	public void setOldBillCode(String oldBillCode) {
		this.oldBillCode = oldBillCode;
	}
	public String getOldBillBatch() {
		return oldBillBatch;
	}
	public void setOldBillBatch(String oldBillBatch) {
		this.oldBillBatch = oldBillBatch;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApplyOrgName() {
		return applyOrgName;
	}
	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getBusUserName() {
		return busUserName;
	}
	public void setBusUserName(String busUserName) {
		this.busUserName = busUserName;
	}
	public String getBusUserPwd() {
		return busUserPwd;
	}
	public void setBusUserPwd(String busUserPwd) {
		this.busUserPwd = busUserPwd;
	}
	//特殊冲红标志
	private String specialRedFlag;
	//是否代开(电子发票使用):0(自开);1(代开)
	private String replacementSign;
	//操作代码
	private String operationCode;
	//冲红原因
	private String rushRedReason;
	//红字信息表编号
	private String redInfoNumber;
	//原销售单号:负销售单要填
	private String oldBillCode;
	//原销售单批次：负销售单要填 	
	private String oldBillBatch;
	//备注
	private String remark;
	//申请机构名称
	private String applyOrgName;
	//主商品名称 
	private String matName;
	//描述
	private String description;
	
	
	
	//特殊行业类型发票定制的内容
	private String extendInfo;
	//业务系统用户名;
	private String busUserName;
	//系统用户密码
	private String busUserPwd;
	//销售单明细
	private List<SpecialInvoiceDetBean> billDetailApiList;
	public List<SpecialInvoiceDetBean> getBillDetailApiList() {
		return billDetailApiList;
	}
	public void setBillDetailApiList(List<SpecialInvoiceDetBean> billDetailApiList) {
		this.billDetailApiList = billDetailApiList;
	}

}
