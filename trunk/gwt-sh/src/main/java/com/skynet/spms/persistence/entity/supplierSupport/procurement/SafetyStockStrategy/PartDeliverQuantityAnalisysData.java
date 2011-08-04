package com.skynet.spms.persistence.entity.supplierSupport.procurement.SafetyStockStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @category 件发货数量分析数据
 * @created 07-五月-2011 10:33:31
 */
@Entity
@Table(name = "SPMS_PARTDELANALISSDATA")
public class PartDeliverQuantityAnalisysData extends BaseEntity {
	/** 件号 **/
	private String partNumber;
	/** 最近三个月发货量 **/
	private float trimesterDeliverQuantity;
	/** 最近半年发货量 **/
	private float halfYearDeliverQuantity;
	/** 两年发货量 **/
	private float twoYearsDeliverQuantity;
	/** 年发货量 **/
	private float yearDeliverQuantity;

	private SafetyStockStrategy safetyStockStrategy;
	@ManyToOne
	@JoinColumn(name="SAFETYSTOCKSTRATEGY_ID")
	public SafetyStockStrategy getSafetyStockStrategy() {
		return safetyStockStrategy;
	}

	public void setSafetyStockStrategy(SafetyStockStrategy safetyStockStrategy) {
		this.safetyStockStrategy = safetyStockStrategy;
	}

	@Column(name = "PARTNUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "TRIMEERLIVUITY")
	public float getTrimesterDeliverQuantity() {
		return trimesterDeliverQuantity;
	}

	public void setTrimesterDeliverQuantity(float trimesterDeliverQuantity) {
		this.trimesterDeliverQuantity = trimesterDeliverQuantity;
	}

	@Column(name = "HALFYEARDVERNTITY")
	public float getHalfYearDeliverQuantity() {
		return halfYearDeliverQuantity;
	}

	public void setHalfYearDeliverQuantity(float halfYearDeliverQuantity) {
		this.halfYearDeliverQuantity = halfYearDeliverQuantity;
	}

	@Column(name = "TWOYSDELIVEAITY")
	public float getTwoYearsDeliverQuantity() {
		return twoYearsDeliverQuantity;
	}

	public void setTwoYearsDeliverQuantity(float twoYearsDeliverQuantity) {
		this.twoYearsDeliverQuantity = twoYearsDeliverQuantity;
	}
	@Column(name="YEARDELIVERQUANTITY")
	public float getYearDeliverQuantity() {
		return yearDeliverQuantity;
	}

	public void setYearDeliverQuantity(float yearDeliverQuantity) {
		this.yearDeliverQuantity = yearDeliverQuantity;
	}

}