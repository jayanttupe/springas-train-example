package com.skynet.spms.persistence.entity.financeService.purposeVoucher;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.Attachment;
import    com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import    com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.SaleInvoice;
import com.skynet.spms.persistence.entity.spmsdd.PurposeVoucherType;

/**
 * @author 黄赟
 * @version 1.0
 * @created 21-四月-2011 19:57:55
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_PURPOSE_VOUCHER")
public class PurposeVoucher extends BaseEntity {

	private Date certificateDate;
	private String createUser;
	private Double totalAmount;
	private int orderNunber;
	private String certificateNumber;
	private String certificateCode;
	private int typeNumber;
	private String createCertificateUser;
	private String checkUser;
	private String accountUser;
	private String cashier;
	private String accountManager;
	private int checkFlag;
	private int accountFlag;
	private int completeFlag;
	private int invalidFlag;
	private int attachmentNumber;
	public List<ClassificationItem> classificationItem;
	public List<Attachment> m_Attachment;
	public PurposeVoucherType purposeVoucherType;
	public VoucherWord m_VoucherWord;
	
	@Reference(itemCls=Attachment.class)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "RELATED_BUSSINESS_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}
	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}
	@Column(name="CERTIFICATE_DATE")
	public Date getCertificateDate() {
		return certificateDate;
	}
	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}
	@Column(name="CREATE_USER")
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@Column(name="TOTAL_AMOUNT")
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="ORDER_NUMBER")
	public int getOrderNunber() {
		return orderNunber;
	}
	public void setOrderNunber(int orderNunber) {
		this.orderNunber = orderNunber;
	}

	@Column(name="CERTIFICATE_CODE")
	public String getCertificateCode() {
		return certificateCode;
	}
	@Column(name="CERTIFICATE_NUMBER")
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
	@Column(name="TYPE_NUMBER")
	public int getTypeNumber() {
		return typeNumber;
	}
	public void setTypeNumber(int typeNumber) {
		this.typeNumber = typeNumber;
	}
	@Column(name="CREATE_CERTIFICATE_USER")
	public String getCreateCertificateUser() {
		return createCertificateUser;
	}
	public void setCreateCertificateUser(String createCertificateUser) {
		this.createCertificateUser = createCertificateUser;
	}
	@Column(name="CHECK_USER")
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	@Column(name="ACCOUNT_USER")
	public String getAccountUser() {
		return accountUser;
	}
	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}
	@Column(name="CASHIER")
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	@Column(name="ACCOUNT_MANAGER")
	public String getAccountManager() {
		return accountManager;
	}
	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}
	@Column(name="CHECK_FLAG")
	public int getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}
	@Column(name="ACCOUNT_FLAG")
	public int getAccountFlag() {
		return accountFlag;
	}
	public void setAccountFlag(int accountFlag) {
		this.accountFlag = accountFlag;
	}
	@Column(name="COMPLETE_FLAG")
	public int getCompleteFlag() {
		return completeFlag;
	}
	public void setCompleteFlag(int completeFlag) {
		this.completeFlag = completeFlag;
	}
	@Column(name="INVALID_FLAG")
	public int getInvalidFlag() {
		return invalidFlag;
	}
	public void setInvalidFlag(int invalidFlag) {
		this.invalidFlag = invalidFlag;
	}
	@Reference(itemCls=ClassificationItem.class)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "CLASSIFICATION_ITEM_ID")
	public List<ClassificationItem> getClassificationItem() {
		return classificationItem;
	}
	public void setClassificationItem(List<ClassificationItem> classificationItem) {
		this.classificationItem = classificationItem;
	}
	
	private List<Map> listMap=new ArrayList<Map>();
	
	@Transient
	public List<Map> getClassificMapItems() {
		return listMap;
	}
	public void setClassificMapItems(List<Map> listMap) {
		this.listMap = listMap;
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="VOUCHER_WORD")
	public VoucherWord getM_VoucherWord() {
		return m_VoucherWord;
	}
	public void setM_VoucherWord(VoucherWord m_VoucherWord) {
		this.m_VoucherWord = m_VoucherWord;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="PURPOSE_VOUCHER_TYPE")
	public PurposeVoucherType getPurposeVoucherType() {
		return purposeVoucherType;
	}
	public void setPurposeVoucherType(PurposeVoucherType purposeVoucherType) {
		this.purposeVoucherType = purposeVoucherType;
	}
	@Column(name="ATTACHMENT_NUMBER")
	public int getAttachmentNumber() {
		return attachmentNumber;
	}
	public void setAttachmentNumber(int attachmentNumber) {
		this.attachmentNumber = attachmentNumber;
	}
	
	
	
	

}