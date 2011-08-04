package com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheetItem.BuybackRequisitionSheetItem;

/**
 * 客户回购申请单
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_BUYBACKSHEET")
public class BuybackRequisitionSheet extends BaseApplicationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1003987282308479075L;
	/**
	 * 合同依据描述
	 */
	private String contractBasisDesc;
	/**
	 * 附件
	 */
	public List<Attachment> m_Attachment;
	/**
	 * 回购申请单明细项
	 */
	public List<BuybackRequisitionSheetItem> m_BuybackRequisitionSheetItem;
	/**
	 * 客户身份识别码
	 */
	private CustomerIdentificationCode m_CustomerIdentificationCode;
	/** 合同是否存在 **/
	private Boolean isTemptlate;
	/**
	 * 总项数
	 */
	private Integer totalCount;
	
	/**
	 * 总项数
	 * @return
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Column(name = "ISTEMPTLATE")
	public Boolean getIsTemptlate() {
		return isTemptlate;
	}

	public void setIsTemptlate(Boolean isTemptlate) {
		this.isTemptlate = isTemptlate;
	}

	@Column(name = "CONTRACTBASISDESC")
	public String getContractBasisDesc() {
		return contractBasisDesc;
	}

	public void setContractBasisDesc(String contractBasisDesc) {
		this.contractBasisDesc = contractBasisDesc;
	}

	@Transient
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}

	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}

	@Transient
	public List<BuybackRequisitionSheetItem> getM_BuybackRequisitionSheetItem() {
		return m_BuybackRequisitionSheetItem;
	}

	public void setM_BuybackRequisitionSheetItem(
			List<BuybackRequisitionSheetItem> m_BuybackRequisitionSheetItem) {
		this.m_BuybackRequisitionSheetItem = m_BuybackRequisitionSheetItem;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "m_CustomerIdentificationCode")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

}