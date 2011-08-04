package com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheetItem.GuaranteeClaimRequisitionSheetItem;

/**
 * 担保索赔
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_GUARANTEECLAIMRQSHEET")
public class GuaranteeClaimRequisitionSheet extends BaseApplicationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8375222190415855719L;

	private String contractBasisDesc;

	private CustomerIdentificationCode m_CustomerIdentificationCode;
	public List<Attachment> m_Attachment;

	/**
	 * 担保索赔申请单明细项
	 */
	public List<GuaranteeClaimRequisitionSheetItem> m_GuaranteeClaimRequisitionSheetItem;

	@Column(name = "CONTRACTBASISDESC")
	public String getContractBasisDesc() {
		return contractBasisDesc;
	}

	public void setContractBasisDesc(String contractBasisDesc) {
		this.contractBasisDesc = contractBasisDesc;
	}

	@Transient
	public List<GuaranteeClaimRequisitionSheetItem> getM_GuaranteeClaimRequisitionSheetItem() {
		return m_GuaranteeClaimRequisitionSheetItem;
	}

	public void setM_GuaranteeClaimRequisitionSheetItem(
			List<GuaranteeClaimRequisitionSheetItem> m_GuaranteeClaimRequisitionSheetItem) {
		this.m_GuaranteeClaimRequisitionSheetItem = m_GuaranteeClaimRequisitionSheetItem;
	}

	@ManyToOne
	@JoinColumn(name = "MCID_CODE")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

	@Transient
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}

	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}
}