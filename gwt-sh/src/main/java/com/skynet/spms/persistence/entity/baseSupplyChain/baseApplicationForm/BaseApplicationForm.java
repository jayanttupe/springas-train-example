package com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;

/**
 * 基础申请单
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 16:05:31
 */
@MappedSuperclass
public class BaseApplicationForm extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 申请单编号 **/
	private String requisitionSheetNumber;
	
	/** 申请单的提交申请的日期，默认为用户提交申请单的日期，用户也可以自行对申请日期进行修改 **/
	private Date requisitionDate;
	
	/** 如果是非系统登录用户，可以填写联系人姓名，做为联系方式 **/
	private String linkman;
	
	/**联系方式**/
	private String contactWay;
	
	/** 备注* */
	private String remark;

	/** 业务发布状态实体 **/
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;

	/** 业务状态实体 **/
	private BussinessStatusEntity m_BussinessStatusEntity;

    
    /**飞机机尾号**/
	private String airIdentificationNumber;

	
	/**总金额**/
	private  Float totalAmount;
	
	
	@Column(name = "AIRIDENTIFICATIONNUMBER")
	public String getAirIdentificationNumber() {
		return airIdentificationNumber;
	}

	public void setAirIdentificationNumber(
			String airIdentificationNumber) {
		this.airIdentificationNumber = airIdentificationNumber;
	}



	@Column(name = "REQUISITION_DATE")
	public Date getRequisitionDate() {
		return requisitionDate;
	}

	public void setRequisitionDate(Date requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	@Column(name = "REQSHEET_NUM")
	public String getRequisitionSheetNumber() {
		return requisitionSheetNumber;
	}

	public void setRequisitionSheetNumber(String requisitionSheetNumber) {
		this.requisitionSheetNumber = requisitionSheetNumber;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BPSTATUSENTITY_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}

	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
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

	@Column(name = "LINKMAN")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	@Column(name = "CONTACTWAY")
	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "TOTAL_AMOUNT")
	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


}