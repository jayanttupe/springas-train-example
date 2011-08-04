package com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.ProcurementPlanType;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementPlanItem.ProcurementPlanItem;

/**
 * @author 乔海龙
 * @category 采购计划
 * @version 1.0
 * @created 07-五月-2011 10:33:36
 */
@Entity
@Table(name = "SPMS_PROCUREMENTPLAN")
public class ProcurementPlan extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 采购计划编号 * */
	private String procurementPlanNumber;
	/** 描述 * */
	private String description;
	/** 计划开始时间 * */
	private Date startDate;
	/** 计划结束时间 * */
	private Date endDate;
	/** 计划用途 * */
	private String purposes;
	/**
	 * 默认为当前用户，但用户可以进行自定义修改为他人人员的姓名
	 */
	private String makeBy;
	/**
	 * 默认为创建记录的日期时间，用户可以自定义进行修改。
	 */
	private Date makeDate;
	/** 明细项总计 * */
	private float itemCount;
	/** 金额总计 * */
	private float totalAmount;
	/** 备注 * */
	private String remarkText;
	private BussinessStatusEntity m_BussinessStatusEntity;
	private List<Attachment> m_Attachment;
	private List<ProcurementPlanItem> m_ProcurementPlanItem;
	private ProcurementPlanType m_ProcurementPlanType;
	/** 业务发布状态实体 * */
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;
	/** 审批结果 * */
	private AuditStatus auditStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "AUDITSTATUS")
	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name = "REMARKTEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "M_BSSIEUHSTSNTY_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}

	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}

	@Column(name = "PROCUREMENTPLANNUMBER")
	public String getProcurementPlanNumber() {
		return procurementPlanNumber;
	}

	public void setProcurementPlanNumber(String procurementPlanNumber) {
		this.procurementPlanNumber = procurementPlanNumber;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STARTDATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "PURPOSES")
	public String getPurposes() {
		return purposes;
	}

	public void setPurposes(String purposes) {
		this.purposes = purposes;
	}

	@Column(name = "MAKEBY")
	public String getMakeBy() {
		return makeBy;
	}

	public void setMakeBy(String makeBy) {
		this.makeBy = makeBy;
	}

	@Column(name = "MAKEDATE")
	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	@Column(name = "ITEMCOUNT")
	public float getItemCount() {
		return itemCount;
	}

	public void setItemCount(float itemCount) {
		this.itemCount = itemCount;
	}

	@Column(name = "TOTALAMOUNT")
	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "M_BUSSINESSSTATUSENTITY_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}

	public void setM_BussinessStatusEntity(
			BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}

	@OneToMany
	@JoinColumn(name = "M_ATTACHMENT_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}

	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}

	@OneToMany(mappedBy = "procurementPlan", fetch = FetchType.LAZY)
	public List<ProcurementPlanItem> getM_ProcurementPlanItem() {
		return m_ProcurementPlanItem;
	}

	public void setM_ProcurementPlanItem(
			List<ProcurementPlanItem> m_ProcurementPlanItem) {
		this.m_ProcurementPlanItem = m_ProcurementPlanItem;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PROCUREMENTPLANTYPE")
	public ProcurementPlanType getM_ProcurementPlanType() {
		return m_ProcurementPlanType;
	}

	public void setM_ProcurementPlanType(
			ProcurementPlanType m_ProcurementPlanType) {
		this.m_ProcurementPlanType = m_ProcurementPlanType;
	}

	// public ApprovalAvailableEntity getM_ApprovalAvailableEntity() {
	// return m_ApprovalAvailableEntity;
	// }
	//
	// public void setM_ApprovalAvailableEntity(
	// ApprovalAvailableEntity m_ApprovalAvailableEntity) {
	// this.m_ApprovalAvailableEntity = m_ApprovalAvailableEntity;
	// }

}