package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * Part Application Data 是指备件在实际应用过程中所需要用到的业务数据。
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:24
 * 
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
@Table(name = "SPMS_PART_APPLICATION_DATA")
public class PartApplicationData  extends BaseEntity{

	//单机装机数量
	private String totalQuantityPerAircraftEngine;
	/**
	 * 缩写 IsETOPS 长度 1位 A ETOP飞行指示
	 * 
	 * Indicates whether or not a flight is categorized as ETOPS.
	 * D Does Not Apply
	 * N No
	 * Y Yes
	 */
	private boolean isEtops;//?boolean类型
	/**
	 * 缩写 ECC
	 * A classification used in the Commerce Control List to identify items for export
	 * control purposes.
	 * 
	 * 详细参考 美国 http://www.bis.doc.gov/licensing/exportingbasics.htm
	 */
	/*
	 * 出口控制分类号码
	 */
	private String m_ExportControlClassificationCode;
	/**
	 * Indenture Code Indicates the relationship of an item to the order of assembly
	 * in the same manner as shown in the Illustrated Parts Catalog (IPC) or
	 * Illustrated Parts List (IPL).
	 * 
	 * The first left hand (highest) column up to 9 for succeeding (lower) indenture
	 * columns, progressing from left to right, as in the IPC or IPL.
	 * Enter number of indenture level 1‐9.
	 * 
	 * 长度 1位 N
	 */
	private String effectivityRangeCode;//有效性范围代码
	
	//再订货点
	private int reorderLine;
	
	//安全库存点
	private int safeStockLine;
	
	//再订货量
	private int reorderQuantity;
	
	private int indentureCode;//IPC及IPL级别代码（未启用）
	
	private float recommendedQuantity;//推荐数量（未启用）
	/**
	 * 缩写 MSC 长度 520字符 NA
	 * Diverse information not provided in dedicated data element format.
	 */
	/*
	 * 航线维护备注说明
	 */
	private String miscellaneousText;
	/**
	 * 缩写REC 长度4位 N
	 * 1.  Provisioning 'S' Files and 'V' File Part Number Segments (PNS). Recommended
	 * Quantity specifies the quantity of the subject part (in implied units of each)
	 * recommended for main base and station support of the customer's aircraft or
	 * engines based on fleet flight hours and other program parameters mutually
	 * agreed between the airline customer and the aircraft/engine manufacturer.
	 * Parts classified as standards will be entered as "000".
	 * 2.  Provisioning 'T' Files and 'V' File End Item Segments (EIS). Recommended
	 * Quantity specifies the quantity of the subject part (in implied units of each)
	 * recommended for repair and overhaul of a component end item based on fleet
	 * flight hours or shop visits plus other program parameters mutually agreed
	 * between the airline customer and the aircraft/engine manufacturer.  Parts
	 * classified as standards will be entered as "000".
	 */

	/*
	 * 快速转发
	 */
	private boolean isQuickEngineChange;
	/*
	 * 发动机级维修代码
	 
	private EngineLevelOfMaintenanceCode m_EngineLevelOfMaintenanceCode;
	
	 * 维护大修修理代码
	 
	private MaintenanceOverhaulRepairCode m_MaintenanceOverhaulRepairCode;
	*/
	
	@Column(name = "REORDER_LINE")
	public int getReorderLine() {
		return reorderLine;
	}
	public void setReorderLine(int reorderLine) {
		this.reorderLine = reorderLine;
	}
	@Column(name = "SAFE_STOCK_LINE")
	public int getSafeStockLine() {
		return safeStockLine;
	}
	public void setSafeStockLine(int safeStockLine) {
		this.safeStockLine = safeStockLine;
	}
	@Column(name = "REORDER_QUANTITY")
	public int getReorderQuantity() {
		return reorderQuantity;
	}
	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}
	
	@Column(name = "EXPORT_CONTROL_CLS_CODE")
	public String getM_ExportControlClassificationCode() {
		return m_ExportControlClassificationCode;
	}
	public void setM_ExportControlClassificationCode(
			String m_ExportControlClassificationCode) {
		this.m_ExportControlClassificationCode = m_ExportControlClassificationCode;
	}
	
	@Column(name = "IS_ETOPS")
	public boolean isEtops() {
		return isEtops;
	}
	public void setEtops(boolean isEtops) {
		this.isEtops = isEtops;
	}

	@Column(name="MISCELLANEOUS_TEXT")
	public String getMiscellaneousText() {
		return miscellaneousText;
	}
	public void setMiscellaneousText(String miscellaneousText) {
		this.miscellaneousText = miscellaneousText;
	}
	@Column(name="RECOMMENDED_QUANTITY")
	public float getRecommendedQuantity() {
		return recommendedQuantity;
	}
	public void setRecommendedQuantity(float recommendedQuantity) {
		this.recommendedQuantity = recommendedQuantity;
	}
	@Column(name = "IS_QUICK_ENGINE_CHANGE")
	public boolean isQuickEngineChange() {
		return isQuickEngineChange;
	}
	public void setQuickEngineChange(boolean isQuickEngineChange) {
		this.isQuickEngineChange = isQuickEngineChange;
	}
	
	@Column(name = "TOTAL_QUANTITY_PER_AE")
	public String getTotalQuantityPerAircraftEngine() {
		return totalQuantityPerAircraftEngine;
	}
	public void setTotalQuantityPerAircraftEngine(
			String totalQuantityPerAircraftEngine) {
		this.totalQuantityPerAircraftEngine = totalQuantityPerAircraftEngine;
	}
	@Column(name = "EFFECTIVITY_RANGE_CODE")
	public String getEffectivityRangeCode() {
		return effectivityRangeCode;
	}
	public void setEffectivityRangeCode(String effectivityRangeCode) {
		this.effectivityRangeCode = effectivityRangeCode;
	}
	@Column(name = "INDENTURE_CODE")
	public int getIndentureCode() {
		return indentureCode;
	}
	public void setIndentureCode(int indentureCode) {
		this.indentureCode = indentureCode;
	}
	
	

}