package com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_PART_LIFE_TIME")
public class PartLifeTime extends BaseEntity {
	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 备件分类
	 */
	private String sparePartClassCode;

	/**
	 * 检测周期
	 */
	private String timeControlTaskCycle;

	/**
	 * 检测代码
	 */
	private String periodicCheckCode;

	/**
	 * 恢复方式
	 */
	private String renewalProcedureCode;

	/**
	 * 注意代码
	 */
	private String payAttentionCode;

	/**
	 * 制造商
	 */
	private String manufacturerCode;

	/**
	 * ATA
	 */
	private String ata;

	/**
	 * 重要性
	 */
	private String essentialityCode;

	public PartLifeTime() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "SPARE_PART_CLASS_CODE")
	public String getSparePartClassCode() {
		return sparePartClassCode;
	}

	public void setSparePartClassCode(String sparePartClassCode) {
		this.sparePartClassCode = sparePartClassCode;
	}

	@Column(name = "TIME_CONTROL_TASK_CYCLE")
	public String getTimeControlTaskCycle() {
		return timeControlTaskCycle;
	}

	public void setTimeControlTaskCycle(String timeControlTaskCycle) {
		this.timeControlTaskCycle = timeControlTaskCycle;
	}

	@Column(name = "PERIODIC_CHECK_CODE")
	public String getPeriodicCheckCode() {
		return periodicCheckCode;
	}

	public void setPeriodicCheckCode(String periodicCheckCode) {
		this.periodicCheckCode = periodicCheckCode;
	}

	@Column(name = "RENEWAL_PROCEDURE_CODE")
	public String getRenewalProcedureCode() {
		return renewalProcedureCode;
	}

	public void setRenewalProcedureCode(String renewalProcedureCode) {
		this.renewalProcedureCode = renewalProcedureCode;
	}

	@Column(name = "PAY_ATTENTION_CODE")
	public String getPayAttentionCode() {
		return payAttentionCode;
	}

	public void setPayAttentionCode(String payAttentionCode) {
		this.payAttentionCode = payAttentionCode;
	}

	@Column(name = "MANUFACTURER_CODE")
	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	@Column(name = "ATA")
	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	@Column(name = "ESSENTIALITY_CODE")
	public String getEssentialityCode() {
		return essentialityCode;
	}

	public void setEssentialityCode(String essentialityCode) {
		this.essentialityCode = essentialityCode;
	}

}