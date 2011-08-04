package com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.baseProcurementOrder;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;
import com.skynet.spms.persistence.entity.csdd.a.AircraftIdentificationNumber;
import com.skynet.spms.persistence.entity.csdd.a.AircraftModelIdentifier;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:02
 */
@MappedSuperclass
public class BaseProcurementOrder extends BaseOrderEntity {
	private static final long serialVersionUID = 1L;
	/** 客户合同编号 **/
	private String customerContractNumber;
	/** 采购期限 **/
	private Date deadline;
	/** 备注 **/
	private String remarkText;
	public Priority m_Priority;
	public AircraftModelIdentifier m_AircraftModelIdentifier;
	public CustomerIdentificationCode m_CustomerIdentificationCode;
	public String m_AircraftIdentificationNumber;
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	private UnitOfMeasureCode m_UnitOfMeasureCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "M_INTERNCYCODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_UNITOFMEASURECODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	// public ProcurementContractTemplate m_ProcurementContractTemplate;
	@Column(name = "CUSTOMRACTNER")
	public String getCustomerContractNumber() {
		return customerContractNumber;
	}

	public void setCustomerContractNumber(String customerContractNumber) {
		this.customerContractNumber = customerContractNumber;
	}

	@Column(name = "DEADLINE")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "REMARKTEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_AIRCRALIDENR")
	public AircraftModelIdentifier getM_AircraftModelIdentifier() {
		return m_AircraftModelIdentifier;
	}

	public void setM_AircraftModelIdentifier(
			AircraftModelIdentifier m_AircraftModelIdentifier) {
		this.m_AircraftModelIdentifier = m_AircraftModelIdentifier;
	}

	@ManyToOne
	@JoinColumn(name = "M_CURIDICADE_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

	@Column(name = "M_ACRAENTINR")
	public String getM_AircraftIdentificationNumber() {
		return m_AircraftIdentificationNumber;
	}

	public void setM_AircraftIdentificationNumber(
			String mAircraftIdentificationNumber) {
		m_AircraftIdentificationNumber = mAircraftIdentificationNumber;
	}

}