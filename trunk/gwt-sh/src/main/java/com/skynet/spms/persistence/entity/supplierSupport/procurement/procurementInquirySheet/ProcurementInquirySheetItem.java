package com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseInquirySheet.baseInquirySheetItem.BaseInquirySheetItem;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrderItem;
/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:37
 */

@Entity
@Table(name = "SPMS_PROINQUIRYSHEET_ITEM")
public class ProcurementInquirySheetItem extends BaseInquirySheetItem {
	

	private String procurementPlanItemNumber;
	
	//private ProcurementOrderItem procurementOrderItem;
	
	/**询价单**/
	private ProcurementInquirySheet procurementInquirySheet;
	
	/**计划单价**/
	private Float planUnitPrice;
	
	/**计划金额**/
	private Float planAmount;

	@Column(name="PLAN_UNIT_PRICE")
	public Float getPlanUnitPrice() {
		return planUnitPrice;
	}

	public void setPlanUnitPrice(Float planUnitPrice) {
		this.planUnitPrice = planUnitPrice;
	}

	@Column(name="PLAN_AMOUNT")
	public Float getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Float planAmount) {
		this.planAmount = planAmount;
	}

	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "PROCUREMENTORDER_ITEM_ID")
//	public ProcurementOrderItem getProcurementOrderItem() {
//		return procurementOrderItem;
//	}
//
//	public void setProcurementOrderItem(ProcurementOrderItem procurementOrderItem) {
//		this.procurementOrderItem = procurementOrderItem;
//	}

	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "PROCUREMENT_INQUIRY_ID")
	public ProcurementInquirySheet getProcurementInquirySheet() {
		return procurementInquirySheet;
	}

	public void setProcurementInquirySheet(
			ProcurementInquirySheet procurementInquirySheet) {
		this.procurementInquirySheet = procurementInquirySheet;
	}

	

}