package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.s.ShelfLifeCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.csdd.u.UnscheduledRemovalRateDecimalCode;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BuyerFurnishedEquipmentIndicator;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.timeCycleData.TimeCyclesData;

/**
 * Part Technical Data 是指备件本身设计要求的可靠性数据。
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:25
 */

/*************************************************************************
 *Update by : Huhf    2011-4-21
 *CHECKED BY: Shanyf  2011-4-21
 *Confirm by: 
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_PART_TECHNICAL_DATA")
public class PartTechnicalData extends BaseEntity {

	/*
	 * 平均非计划拆卸间隔
	 */
	private int mtbur;
	private UnitCode mtburUnitCode;
	/*
	 * 平均维修间隔
	 */
	private int mtbm;
	private UnitCode mtbmUnitCode;
	/*
	 * 货架寿命（时间）
	 */
	private int shelfLife;
	private UnitCode shelfLifeUnitCode; 
	
	/**
	 * 缩写 TQA 长度 5位 N
	 * Specifies the total number of units of the subject part (in implied units of
	 * each) used in the complete assembly of one aircraft or engine.
	 */
	//public float totalQuantityPerAircraftEngine;
	/**
	 * 缩写URR
	 * The Unscheduled Removal Rate (URR) expresses for one unit of a subject part the
	 * mean number of unscheduled removals (replacements) on an aircraft, engine, or
	 * end item per 1000 flight hours, operating cycles, or landings as qualified by
	 * the Time/Cycle Code.
	 * The URR is the equivalent of 1000 flight hours, operating cycles or landings
	 * divided by the Mean Time/Cycle Between Unscheduled Removals (MTUR/MCUR).  This
	 * rate
	 * includes both justified and unjustified removals/replacements.  For parts
	 * having multiple applications within a specified airframe, engine or component
	 * end item, the URR represents an average rate considering all applications.
	 * Blanks (spaces) will be referenced for parts classified as standard parts.
	 */
	/*
	 * 不定期拆换率
	 */
	private float unscheduledRemovalRate;

	/*
	 * 不定期拆换率小数点代码
	 */
	private UnscheduledRemovalRateDecimalCode m_UnscheduledRemovalRateDecimalCode;
	/*
	 * 时控数据
	 */
	private List<TimeCyclesData> m_TimeCyclesData;
	
	private ShelfLifeCode m_ShelfLifeCode;//货架周期类型代码
	
    private boolean isTimeCycles; //是否时寿件
    
    private boolean isTimeControl; //是否时控件
    
    private float maintenancePercent;// 维护航线百分比
	public String overhaulManualReferenceNumber; //大修手册检索号码
	public float ScrapRate;//报废率
	
	
	@Column(name = "SHELF_LIFE")
	public int getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "SHELF_LIFE_UNIT_CODE")
	public UnitCode getShelfLifeUnitCode() {
		return shelfLifeUnitCode;
	}
	public void setShelfLifeUnitCode(UnitCode shelfLifeUnitCode) {
		this.shelfLifeUnitCode = shelfLifeUnitCode;
	}
	@Column(name = "IS_TIME_CONTROL")
	public boolean isTimeControl() {
		return isTimeControl;
	}
	public void setTimeControl(boolean isTimeControl) {
		this.isTimeControl = isTimeControl;
	}
	@Column(name = "IS_TIME_CYCLES")
	public boolean isTimeCycles() {
		return isTimeCycles;
	}
	public void setTimeCycles(boolean isTimeCycles) {
		this.isTimeCycles = isTimeCycles;
	}
	@Column(name="MTBUR")
	public int getMtbur() {
		return mtbur;
	}
	public void setMtbur(int mtbur) {
		this.mtbur = mtbur;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "MTBUR_UNIT_CODE")
	public UnitCode getMtburUnitCode() {
		return mtburUnitCode;
	}
	public void setMtburUnitCode(UnitCode mtburUnitCode) {
		this.mtburUnitCode = mtburUnitCode;
	}
	@Column(name="MTBM")
	public int getMtbm() {
		return mtbm;
	}
	public void setMtbm(int mtbm) {
		this.mtbm = mtbm;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "MTBM_UNIT_CODE")
	public UnitCode getMtbmUnitCode() {
		return mtbmUnitCode;
	}
	public void setMtbmUnitCode(UnitCode mtbmUnitCode) {
		this.mtbmUnitCode = mtbmUnitCode;
	}
	
	
	@Column(name = "UNSCHEDULED_REMOVAL_RATE")
	public float getUnscheduledRemovalRate() {
		return unscheduledRemovalRate;
	}
	public void setUnscheduledRemovalRate(float unscheduledRemovalRate) {
		this.unscheduledRemovalRate = unscheduledRemovalRate;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "UNSCHEDULED_REMOVAL_RD_CODE")
	public UnscheduledRemovalRateDecimalCode getM_UnscheduledRemovalRateDecimalCode() {
		return m_UnscheduledRemovalRateDecimalCode;
	}
	public void setM_UnscheduledRemovalRateDecimalCode(
			UnscheduledRemovalRateDecimalCode m_UnscheduledRemovalRateDecimalCode) {
		this.m_UnscheduledRemovalRateDecimalCode = m_UnscheduledRemovalRateDecimalCode;
	}
	@Reference(itemCls=TimeCyclesData.class)
	@OneToMany()
	@JoinColumn(name = "PART_TECHNICAL_DATA_ID")
	public List<TimeCyclesData> getM_TimeCyclesData() {
		return m_TimeCyclesData;
	}
	public void setM_TimeCyclesData(List<TimeCyclesData> m_TimeCyclesData) {
		this.m_TimeCyclesData = m_TimeCyclesData;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "SHELF_LIFE_CODE")
	public ShelfLifeCode getM_ShelfLifeCode() {
		return m_ShelfLifeCode;
	}
	public void setM_ShelfLifeCode(ShelfLifeCode m_ShelfLifeCode) {
		this.m_ShelfLifeCode = m_ShelfLifeCode;
	}
	
	
	
	@Column(name = "MAINTENANCE_PERCENT")
    public float getMaintenancePercent() {
		return maintenancePercent;
	}
	public void setMaintenancePercent(float maintenancePercent) {
		this.maintenancePercent = maintenancePercent;
	}
	@Column(name = "OVERHAUL_MANUAL_RN")
	public String getOverhaulManualReferenceNumber() {
		return overhaulManualReferenceNumber;
	}
	public void setOverhaulManualReferenceNumber(
			String overhaulManualReferenceNumber) {
		this.overhaulManualReferenceNumber = overhaulManualReferenceNumber;
	}
	@Column(name = "SCRAP_RATE")
	public float getScrapRate() {
		return ScrapRate;
	}
	public void setScrapRate(float scrapRate) {
		ScrapRate = scrapRate;
	}
}