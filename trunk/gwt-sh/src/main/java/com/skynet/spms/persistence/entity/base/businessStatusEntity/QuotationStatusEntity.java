package com.skynet.spms.persistence.entity.base.businessStatusEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseStatusEntity;

/**
 * 报价状态实体
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 03-四月-2011 11:33:14
 */
@Entity
@Table(name = "SPMS_QUOTATION_STATUS")
public class QuotationStatusEntity extends BaseStatusEntity {

	public QuotationStatus m_QuotationStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "QUOTATION_STATUS")
	public QuotationStatus getM_QuotationStatus() {
		return m_QuotationStatus;
	}

	public void setM_QuotationStatus(QuotationStatus m_QuotationStatus) {
		this.m_QuotationStatus = m_QuotationStatus;
	}

}