package com.skynet.spms.persistence.entity.contractManagement.template.baseContractTemplate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.csdd.l.Language;
import com.skynet.spms.persistence.entity.spmsdd.ApprovalStage;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;

/**
 * 基础合同实体
 * 
 * @author tqc
 * 
 */
@MappedSuperclass
public class BaseContactEntity extends BaseEntity {
	/** 合同编号 **/
	private String contractNumber;

	/** 实体制定人员，默认为当前用户，可以进行手工修改人名 **/
	private String makeBy;

	/** 备注信息 **/
	private String remarkText;

	/** 语言 **/
	private Language m_Language;

	/** 业务发布状态 **/
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;

	/** 业务状态 **/
	private BussinessStatusEntity m_BussinessStatusEntity;

	/** 附件 **/
	private List<Attachment> contractAttachment;
	
	/**审批结果**/
	private AuditStatus auditStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="AUDITSTATUS")
	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name = "CONTRACTNUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name = "MAKEBY")
	public String getMakeBy() {
		return makeBy;
	}

	public void setMakeBy(String makeBy) {
		this.makeBy = makeBy;
	}

	@Column(name = "REMARKTEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "LANGUAGE_CODE")
	public Language getM_Language() {
		return m_Language;
	}

	public void setM_Language(Language m_Language) {
		this.m_Language = m_Language;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "PUBLISHSTATUSENTITY_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}

	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "STATUSENTITY_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}

	public void setM_BussinessStatusEntity(
			BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}

	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "ATTACHMENT_ID")
	public List<Attachment> getContractAttachment() {
		return contractAttachment;
	}

	public void setContractAttachment(List<Attachment> contractAttachment) {
		this.contractAttachment = contractAttachment;
	}

}