package com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.RepairRequisitionSheetItem;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 客户送修申请单
 * 
 * @author tqc
 * 
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_REPAIRREQSHEET")
public class RepairRequisitionSheet extends BaseApplicationForm {
	
	private String repairContractTemplateId;

	/** 客户送修申请单明细项 **/
	private RepairRequisitionSheetItem repairRequisitionSheetItem;

	/** 客户识别代码 **/
	private CustomerIdentificationCode m_CustomerIdentificationCode;

	/** 工作任务的优先级* */
	private Priority m_Priority;
	
	/**联系信息**/
	private String contactInformation;
	
	/**备注**/
	private String remarkText;
	
	@Column(name="REMARKTEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Column(name="CONTACTINFORMATION")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Column(name="REPAIRCONTRACTTEMPLATE_ID")
	public String getRepairContractTemplateId() {
		return repairContractTemplateId;
	}

	public void setRepairContractTemplateId(String repairContractTemplateId) {
		this.repairContractTemplateId = repairContractTemplateId;
	}



	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "REPAIRREQUISITIONSHEET_ITEM")
	public RepairRequisitionSheetItem getRepairRequisitionSheetItem() {
		return repairRequisitionSheetItem;
	}

	public void setRepairRequisitionSheetItem(
			RepairRequisitionSheetItem repairRequisitionSheetItem) {
		this.repairRequisitionSheetItem = repairRequisitionSheetItem;
	}

	@ManyToOne
	@JoinColumn(name = "CIC_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY_CODE")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}

}