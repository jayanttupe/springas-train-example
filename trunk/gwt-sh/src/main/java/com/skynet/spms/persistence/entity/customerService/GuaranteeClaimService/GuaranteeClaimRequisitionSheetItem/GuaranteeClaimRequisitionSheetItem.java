package com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheetItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;
import com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.GuaranteeClaimRequisitionSheet;

/**
 * 担保索赔明细
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_GUARANTEECLAIMRQSHEETITEM")
public class GuaranteeClaimRequisitionSheetItem extends BaseApplicationFormItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2665369902201637682L;
	
	/** 序号/批号 */
	private String orderNum;
	
	/** 申请单实体 */
	private GuaranteeClaimRequisitionSheet sheet;

	/** 序号/批号 */
	@Column(name = "BATCHNUMBER")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "GUARANTEEID")
	public GuaranteeClaimRequisitionSheet getSheet() {
		return sheet;
	}

	public void setSheet(GuaranteeClaimRequisitionSheet sheet) {
		this.sheet = sheet;
	}
}