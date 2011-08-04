package com.skynet.spms.persistence.entity.customerService.RepairService.RepairConfirmation;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseConfirmationOfEnrollment.BaseConfirmationOfEnrollment;

/**
 * 客户送修确认函
 * 
 * @author tqc
 */
@Entity
@Table(name = "SPMS_REPAIRCONFIRMATION")
public class RepairConfirmation extends BaseConfirmationOfEnrollment {

	/** 客户识别代码 **/
	private CustomerIdentificationCode m_CustomerIdentificationCode;

	@ManyToOne
	@JoinColumn(name = "CIC_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

}