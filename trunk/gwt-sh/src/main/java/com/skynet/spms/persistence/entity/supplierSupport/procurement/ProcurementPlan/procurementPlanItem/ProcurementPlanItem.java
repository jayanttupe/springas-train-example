package com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementPlanItem;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.ProcurementPlan;

/**
 * @author 乔海龙
 * @version 1.0
 * @category 采购计划项
 * @created 07-五月-2011 10:33:36
 */
@Entity
@Table(name = "SPMS_PROCUREMENTPLANITEM")
public class ProcurementPlanItem extends BaseEntity {
	/** 项号 **/
	private int itemNumber;
	/**
	 * 采购计划明细项编号，是一个人工可识别的明细项编号，通过这个编号可以跟踪采购计划的执行情况。
	 */
	private String procurementPlanItemNumber;
	/** 件号 **/
	private String partNumber;
	/** 关键字 **/
	private String itemKeyword;
	/** ATA章节号 **/
	private String ata;
	/** 计划采购金额 **/
	private float unitPriceAmount;
	/**
	 * 计划单价已本币方式进行计算 计划单价=采购系数*单价*汇率
	 */
	private float plannedUnitPrice;
	/** 计划采购数量 **/
	private float plannedQuantity;
	/**
	 * 采购系数做为采购价格中的一个可变动权重常量，其中计划单价的计算是由，单价*采购系数*汇率得到。采购系数即利润
	 */
	public float coefficient;
	/**
	 * 要求采购到库的日期
	 */
	private int leadTime;
	/** 交货日期 **/
	private Date deliveryDate;
	/** 备件需求编号 **/
	private String partRequirementNumber;
	/**
	 * 需求产生的具体日期
	 */
	private Date requiredDate;
	/** 总金额 **/
	private float amount;
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	private CertificateType m_CertificateType;
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	private ManufacturerCode m_ManufacturerCode;
	/** 备注 **/
	private String remarkText;
	private ProcurementPlan procurementPlan;

	@Column(name = "REMARKTEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Column(name = "ITEMNUMBER")
	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "PROCUREMENTPLANITEMNUMBER")
	public String getProcurementPlanItemNumber() {
		return procurementPlanItemNumber;
	}

	public void setProcurementPlanItemNumber(String procurementPlanItemNumber) {
		this.procurementPlanItemNumber = procurementPlanItemNumber;
	}

	@Column(name = "PARTNUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "ITEM_KEYWORD")
	public String getItemKeyword() {
		return itemKeyword;
	}

	public void setItemKeyword(String keyword) {
		this.itemKeyword = keyword;
	}

	@Column(name = "ATA")
	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	@Column(name = "UNITPRICEAMOUNT")
	public float getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}

	@Column(name = "PLANNEDUNITPRICE")
	public float getPlannedUnitPrice() {
		return plannedUnitPrice;
	}

	public void setPlannedUnitPrice(float plannedUnitPrice) {
		this.plannedUnitPrice = plannedUnitPrice;
	}

	@Column(name = "PLANNEDQUANTITY")
	public float getPlannedQuantity() {
		return plannedQuantity;
	}

	public void setPlannedQuantity(float plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}

	@Column(name = "COEFFICIENT")
	public float getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}

	@Column(name = "LEADTIME")
	public int getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}

	@Column(name = "DELIVERYDATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "PARTREQUIREMENTNUMBER")
	public String getPartRequirementNumber() {
		return partRequirementNumber;
	}

	public void setPartRequirementNumber(String partRequirementNumber) {
		this.partRequirementNumber = partRequirementNumber;
	}

	@Column(name = "REQUIREDDATE")
	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	@Column(name = "AMOUNT")
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_INTRNICURRECCODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_CERTIFICATETYPE")
	public CertificateType getM_CertificateType() {
		return m_CertificateType;
	}

	public void setM_CertificateType(CertificateType m_CertificateType) {
		this.m_CertificateType = m_CertificateType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_UNITOFMEASURECODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	@ManyToOne
	@JoinColumn(name = "M_MANUFACTURERCODE_ID")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	@ManyToOne
	@JoinColumn(name = "PROCUREMENTPLAN_ID")
	public ProcurementPlan getProcurementPlan() {
		return procurementPlan;
	}

	public void setProcurementPlan(ProcurementPlan procurementPlan) {
		this.procurementPlan = procurementPlan;
	}

}