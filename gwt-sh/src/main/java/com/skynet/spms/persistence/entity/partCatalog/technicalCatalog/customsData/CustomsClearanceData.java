package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.csdd.i.InternationalCommodityCode;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:21
 */
/*
 *Update by : Huhf    2011-4-21
 *CHECKED BY: Shanyf  2011-4-21
 *Confirm by: 
 * 
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_CUSTOMS_CLEARANCE_DATA")
public class CustomsClearanceData extends BaseEntity {

	/**
	 * harmonized Commodity Description and Coding
	 * 商品编码
	 */
	private String hsCode;
	/**
	 * 备件用途的说明对于物流海关报关有十分必要的说明作用。
	 */
	private String purpose;
	/*
	 * 国际商品编码
	 */
	private InternationalCommodityCode m_InternationalCommodityCode;
	/*
	 * 进口限制ID
	 */
	private ImportRestraints m_ImportRestraints;
	/*
	 * 出口限制ID
	 */
	private ExportRestraints m_ExportRestraints;
	
	
	
	@Column(name="HS_CODE")
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	@Column(name="PURPOSE")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "INTERNATIONAL_COMMODITY_CODE")
	public InternationalCommodityCode getM_InternationalCommodityCode() {
		return m_InternationalCommodityCode;
	}
	public void setM_InternationalCommodityCode(
			InternationalCommodityCode m_InternationalCommodityCode) {
		this.m_InternationalCommodityCode = m_InternationalCommodityCode;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IMPORT_RESTRAINTS_ID")
	public ImportRestraints getM_ImportRestraints() {
		return m_ImportRestraints;
	}
	public void setM_ImportRestraints(ImportRestraints m_ImportRestraints) {
		this.m_ImportRestraints = m_ImportRestraints;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "EXPORT_RESTRAINTS_ID")
	public ExportRestraints getM_ExportRestraints() {
		return m_ExportRestraints;
	}
	public void setM_ExportRestraints(ExportRestraints m_ExportRestraints) {
		this.m_ExportRestraints = m_ExportRestraints;
	}

}