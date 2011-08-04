package com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate.LeaseContractTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * @author 曹宏炜
 * @category 客户租赁申请单
 * @version 1.0
 * @created 03-四月-2011 11:32:57
 */
@Entity
@Table(name = "SPMS_LEASEREQUSITIONSHEET")
public class LeaseRequisitionSheet extends BaseApplicationForm {

	private List<LeaseRequisitionSheetItem> m_LeaseRequisitionSheetItem;
	private String contractNumber;
	private CustomerIdentificationCode m_CustomerIdentificationCode;
	// 优先级
	private Priority m_Priority;

	@Column(name = "CONTRACTNUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}

	@OneToMany(mappedBy = "leaseRequisitionSheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<LeaseRequisitionSheetItem> getM_LeaseRequisitionSheetItem() {
		return m_LeaseRequisitionSheetItem;
	}

	public void setM_LeaseRequisitionSheetItem(
			List<LeaseRequisitionSheetItem> m_LeaseRequisitionSheetItem) {
		this.m_LeaseRequisitionSheetItem = m_LeaseRequisitionSheetItem;
	}

	@ManyToOne
	@JoinColumn(name = "CIFC_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

}