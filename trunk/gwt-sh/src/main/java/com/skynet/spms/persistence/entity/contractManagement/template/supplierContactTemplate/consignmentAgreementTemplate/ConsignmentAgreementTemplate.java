package com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.consignmentAgreementTemplate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.baseSupplierContractTemplate.BaseSupplierContractTemplate;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;

/**
 * 供应商寄售协议是合同的一种变种，寄售协议通常先双方签订协议，然后进行相关的补库操作以及协议内的合同活动。
 * @author 曹宏炜
 * @version 1.1
 * @created 05-五月-2011 11:15:12
 */
@Entity
@Table(name = "SPMS_CONSIGAGREETEMPLATE")
public class ConsignmentAgreementTemplate extends BaseSupplierContractTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1510419734965810566L;
	/** 如果是非系统登录用户，可以填写联系人姓名，做为联系方式 **/
	private String linkman;
	/**联系方式**/
	private String contactWay;
	/** 特殊条款 */
	private String extraProvision;
	/** 支付说明 **/
	private String paymentExplain;
	/** 项数统计 **/
	private int totalCount;
	/**总金额**/
	private  Float totalAmount;
	
	/** 供应商 */
	private SupplierCode supplier;
	/** 供应商**/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUPPLIER")
	public SupplierCode getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierCode supplier) {
		this.supplier = supplier;
	}
	@Column(name="LINKMAN")
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	@Column(name="CONTACTWAY")
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	@Column(name="EXTRAPROVISION")
	public String getExtraProvision() {
		return extraProvision;
	}
	public void setExtraProvision(String extraProvision) {
		this.extraProvision = extraProvision;
	}
	@Column(name="PAYMENTEXPLAIN")
	public String getPaymentExplain() {
		return paymentExplain;
	}
	
	public void setPaymentExplain(String paymentExplain) {
		this.paymentExplain = paymentExplain;
	}
	@Column(name="TOTALCOUNT")
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Column(name="TOTALAMOUNT")
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}