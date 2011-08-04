package com.skynet.spms.persistence.entity.baseSupplyChain.baseConfirmationOfEnrollment;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;

/**
 * 基础确认函
 * 
 * @author tqc
 */
@MappedSuperclass
public class BaseConfirmationOfEnrollment extends BaseEntity {

	/** 确认函编号 **/
	private String confirmationNumber;

	/** 确认函描述 **/
	private String description;

	/** 附件 **/
	public List<Attachment> m_Attachment;

	/** 业务状态 **/
	public BussinessStatusEntity m_BussinessStatusEntity;

	/** 业务发布状态 **/
	public BussinessPublishStatusEntity m_BussinessPublishStatusEntity;

	@Column(name = "CONFIRMATION_NUM")
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	@Column(name = "DESCIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany
	@JoinColumn(name = "ATTACHMENT_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}

	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}

	@OneToOne
	@JoinColumn(name = "BSTATUSENTITY_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}

	public void setM_BussinessStatusEntity(
			BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}

	@OneToOne
	@JoinColumn(name = "BPSTATUSENTITY_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}

	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}

}