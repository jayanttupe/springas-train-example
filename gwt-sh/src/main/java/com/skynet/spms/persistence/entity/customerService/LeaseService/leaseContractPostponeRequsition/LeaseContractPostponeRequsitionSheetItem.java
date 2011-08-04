package com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContractPostponeRequsition;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;

/**
 * @author 曹宏炜
 * @category 客户租赁合同延期申请单明细项
 * @version 1.0
 * @created 03-四月-2011 11:32:56
 */
@Entity
@Table(name = "SPMS_LEASESHEETITEM")
public class LeaseContractPostponeRequsitionSheetItem extends
		BaseApplicationFormItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 手续费
	private float factorage;
	// 延期日期
	private Date postponeDate;
	@Column(name="FACTORAGE")
	public float getFactorage() {
		return factorage;
	}

	public void setFactorage(float factorage) {
		this.factorage = factorage;
	}

	@Column(name = "POSTPONEDATE")
	public Date getPostponeDate() {
		return postponeDate;
	}

	public void setPostponeDate(Date postponeDate) {
		this.postponeDate = postponeDate;
	}

}