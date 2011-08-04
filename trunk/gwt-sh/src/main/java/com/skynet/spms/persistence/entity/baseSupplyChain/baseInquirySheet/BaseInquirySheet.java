package com.skynet.spms.persistence.entity.baseSupplyChain.baseInquirySheet;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;

/**
 * 基础询价单
 * 
 * @author tqc
 * @version 1.0
 * @created 10-三月-2011 11:10:34
 */
@MappedSuperclass
public class BaseInquirySheet extends BaseEntity {

	/** 询价单编号 * */
	private String inquirySheetNumber;

	/** 询价单备注 * */
	public String remark;

	/** 如果是非系统登录用户，可以填写联系人姓名，做为联系方式 * */
	private String linkman;

	/** 客户的联系人员的联系方式 * */
	private String contactInformation;

	/** 业务状态实体 * */
	private BussinessStatusEntity m_BussinessStatusEntity;

	/** 报价状态实体 * */
	private QuotationStatusEntity m_QuotationStatusEntity;
	
	/**发布状态**/
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;
										
	/**发布时间**/
	private Date publishDate;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BPSTATUSENTITY_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}

	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity bussinessPublishStatusEntity) {
		m_BussinessPublishStatusEntity = bussinessPublishStatusEntity;
	}

	@Column(name = "PUBLISH_DATE")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Transient
	public String getBussinessStatusStr() {
		if (m_BussinessStatusEntity != null) {
			return "已新建";
		} else {
			String value = m_BussinessStatusEntity.getStatus() + "";
			if (value.equals("created")) {
				return "已新建";
			} else if (value.equals("submited")) {
				return "已提交";
			} else if (value.equals("refused")) {
				return "已打回";
			} else if (value.equals("accepted")) {
				return "已确认";
			} else if (value.equals("dispatched")) {
				return "已分派";
			} else if (value.equals("processed")) {
				return "已处理";
			} else if (value.equals("closed")) {
				return "已关闭";
			}
			return "已新建";
		}

	}

	@Column(name = "INQUIRY_SHEET_NUMBER")
	public String getInquirySheetNumber() {
		return inquirySheetNumber;
	}

	public void setInquirySheetNumber(String inquirySheetNumber) {
		this.inquirySheetNumber = inquirySheetNumber;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
			BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "QSTATUSENTITY_ID")
	public QuotationStatusEntity getM_QuotationStatusEntity() {
		return m_QuotationStatusEntity;
	}

	public void setM_QuotationStatusEntity(
			QuotationStatusEntity m_QuotationStatusEntity) {
		this.m_QuotationStatusEntity = m_QuotationStatusEntity;
	}

}