package com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContractPostponeRequsition;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;

/**
 * @author 曹宏炜
 * @category 客户租赁合同延期申请单
 * @version 1.0
 * @created 03-四月-2011 11:32:56
 */
@Entity
@Table(name = "SPMS_LEASESHEET")
public class LeaseContractPostponeRequsitionSheet extends BaseApplicationForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 客户合同编号
	private String contractNumber;

	public List<LeaseContractPostponeRequsitionSheetItem> m_LeaseContractPostponeRequsitionSheetItem;

	@Column(name = "CONTRACTNUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@OneToMany
	@JoinColumn(name = "LCPRSI_ID")
	public List<LeaseContractPostponeRequsitionSheetItem> getM_LeaseContractPostponeRequsitionSheetItem() {
		return m_LeaseContractPostponeRequsitionSheetItem;
	}

	public void setM_LeaseContractPostponeRequsitionSheetItem(
			List<LeaseContractPostponeRequsitionSheetItem> m_LeaseContractPostponeRequsitionSheetItem) {
		this.m_LeaseContractPostponeRequsitionSheetItem = m_LeaseContractPostponeRequsitionSheetItem;
	}

}