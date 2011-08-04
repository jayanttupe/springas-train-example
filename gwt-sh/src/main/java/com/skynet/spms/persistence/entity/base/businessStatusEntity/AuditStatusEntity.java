package com.skynet.spms.persistence.entity.base.businessStatusEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseStatusEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:32
 */
@Entity
@Table(name = "SPMS_AUDIT_STATUS")
public class AuditStatusEntity extends BaseStatusEntity {

	public AuditStatus m_AuditStatus;

	@Enumerated(EnumType.STRING)
	@Column(name="AUDIT_STATUS")
	public AuditStatus getM_AuditStatus() {
		return m_AuditStatus;
	}

	public void setM_AuditStatus(AuditStatus m_AuditStatus) {
		this.m_AuditStatus = m_AuditStatus;
	}
}