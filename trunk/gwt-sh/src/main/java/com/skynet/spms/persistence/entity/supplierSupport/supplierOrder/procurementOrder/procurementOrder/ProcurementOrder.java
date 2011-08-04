package com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.baseProcurementOrder.BaseProcurementOrder;

/**
 * 采购指令继承于基础采购指令，计划采购指令依赖于采购计划的下达。之所以称之为计划采购指令，原因是依赖于采购计划的采购指令，简称计划采购指令。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:36
 */
@Entity
@Table(name = "SPMS_PROCUREMENTORDER")
public class ProcurementOrder extends BaseProcurementOrder {
	private static final long serialVersionUID = 1L;

	private String procurementPlanId;

	/** 采购计划编号 **/
	private String procurementPlanNumber;
	/** 明细项总计 **/
	private float itemCount;
	/** 金额总计 **/
	private float totalAmount;
	/** 计划开始时间 **/
	private Date startDate;
	/** 计划结束时间 **/
	private Date endDate;

	/** 合同是否存在 **/
	private Boolean isTemptlate;

	@Column(name = "PROCUREMENTPLANID")
	public String getProcurementPlanId() {

		return procurementPlanId;
	}

	public void setProcurementPlanId(String procurementPlanId) {
		this.procurementPlanId = procurementPlanId;
	}

	@Column(name = "STARTDATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "ISTEMPTLATE")
	public Boolean getIsTemptlate() {
		return isTemptlate;
	}

	public void setIsTemptlate(Boolean isTemptlate) {
		this.isTemptlate = isTemptlate;
	}

	@Column(name = "PROUREMLANNUER")
	public String getProcurementPlanNumber() {
		return procurementPlanNumber;
	}

	public void setProcurementPlanNumber(String procurementPlanNumber) {
		this.procurementPlanNumber = procurementPlanNumber;
	}

	@Column(name = "ITEMCOUNT")
	public float getItemCount() {
		return itemCount;
	}

	public void setItemCount(float itemCount) {
		this.itemCount = itemCount;
	}

	@Column(name = "TOTALAMOUNT")
	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

}