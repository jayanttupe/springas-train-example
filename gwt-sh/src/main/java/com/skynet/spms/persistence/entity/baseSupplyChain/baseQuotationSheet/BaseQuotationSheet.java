package com.skynet.spms.persistence.entity.baseSupplyChain.baseQuotationSheet;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;

/**
 * @category 基础报价单
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:35
 */
@MappedSuperclass
public class BaseQuotationSheet extends BaseEntity {

	/** 报价单编号 * */
	private String quotationSheetNumber;

	/** 价格有效期 * */
	private Date priceEffectiveDate;

	/** 报价日期 * */
	private Date quotationDate;

	/** 报价单备注 * */
	private String quotationRemark;

	/** 如果是非系统登录用户，可以填写联系人姓名，做为联系方式 * */
	private String linkman;

	/** 客户的联系人员的联系方式 * */
	private String contactInformation;

	/** 报价状态实体* */
	private QuotationStatusEntity m_QuotationStatusEntity;

	/** 业务状态实体 * */
	private BussinessStatusEntity m_BussinessStatusEntity;

	/**发布状态**/
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BPSTATUSENTITY_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}

	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}
	
	@Column(name = "QUOTATION_SHEET_NUMBER")
	public String getQuotationSheetNumber() {
		return quotationSheetNumber;
	}

	public void setQuotationSheetNumber(String quotationSheetNumber) {
		this.quotationSheetNumber = quotationSheetNumber;
	}

	@Column(name = "PRICE_EFFECTIVE_DATE")
	public Date getPriceEffectiveDate() {
		return priceEffectiveDate;
	}

	public void setPriceEffectiveDate(Date priceEffectiveDate) {
		this.priceEffectiveDate = priceEffectiveDate;
	}

	@Column(name = "QUOTATION_DATE")
	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	@Column(name = "QUOTATION_REMARK")
	public String getQuotationRemark() {
		return quotationRemark;
	}

	public void setQuotationRemark(String quotationRemark) {
		this.quotationRemark = quotationRemark;
	}


	@Column(name = "LINKMAN")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "CONTACT_INFORMATION")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BSTATUSENTITY_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}

	public void setM_BussinessStatusEntity(
			BussinessStatusEntity bussinessStatusEntity) {
		m_BussinessStatusEntity = bussinessStatusEntity;
	}

	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "QSTATUSENTITY_ID")
	public QuotationStatusEntity getM_QuotationStatusEntity() {
		return m_QuotationStatusEntity;
	}

	public void setM_QuotationStatusEntity(
			QuotationStatusEntity quotationStatusEntity) {
		m_QuotationStatusEntity = quotationStatusEntity;
	}
}