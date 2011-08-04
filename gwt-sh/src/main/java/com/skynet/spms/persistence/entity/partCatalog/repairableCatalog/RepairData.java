package com.skynet.spms.persistence.entity.partCatalog.repairableCatalog;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.a.AircraftSystemCode;
import com.skynet.spms.persistence.entity.csdd.e.ExchangeUnitAvailableIndicator;
import com.skynet.spms.persistence.entity.csdd.p.PriceTypeCode;
import com.skynet.spms.persistence.entity.csdd.r.RepairProcessCodes;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitCode;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 18-四月-2011 14:14:25
 */
/*************************************************************************
 *Update by : Huhf    2011-4-25
 *CHECKED BY: Shanyf  2011-4-25
 *Confirm by: 
 *Update list:
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_REPAIR_DATA")
public class RepairData extends BaseEntity {

	
	private int meanShopProcessingTime;//车间平均修理时间
	private RepairShopCode m_RepairShopCode;//承修商代码
	private UnitCode m_UnitCode;//单位代码
	private int referenceProcessingTime;//参考修理工时
	private RepairProcessCodes m_RepairProcessCodes;//修理工作类别
	private PriceTypeCode m_PriceTypeCode;//价格类型代码
	private ExchangeUnitAvailableIndicator m_ExchangeUnitAvailableIndicator;//设备可交换指示
	private AircraftSystemCode m_AircraftSystemCode;//飞机系统代码
	private String repairProcessRemarks;//维修依据文件
	private String remarkText;//备注
	private String partIndexId;//件号索引ID
	private LicenseStatement m_LicenseStatement;//授权许可声明
	
	//Transient字段
	private String repairCodeId;
	
	/*private String keywordName; //关键字
	private int minimumRepairQuantity; //最小修理数量
	private String overhaulManualReferenceNumber; //大修手册参考号码
	private Date priceEffectiveDate; //价格有效日期
	private float repairPriceAmount ; //修理价格金额
	 */	
	
	@OneToOne()
	@JoinColumn(name="REPAIR_SHOP_CODE_ID")
	public RepairShopCode getM_RepairShopCode() {
		return m_RepairShopCode;
	}
	public void setM_RepairShopCode(RepairShopCode m_RepairShopCode) {
		this.m_RepairShopCode = m_RepairShopCode;
	}
	@Column(name = "UNIT_CODE")
	public UnitCode getM_UnitCode() {
		return m_UnitCode;
	}
	public void setM_UnitCode(UnitCode m_UnitCode) {
		this.m_UnitCode = m_UnitCode;
	}
	@Column(name = "REF_PRO_TIME")
	public int getReferenceProcessingTime() {
		return referenceProcessingTime;
	}
	public void setReferenceProcessingTime(int referenceProcessingTime) {
		this.referenceProcessingTime = referenceProcessingTime;
	}	
	@Column(name="PART_INDEX_ID")
	public String getPartIndexId() {
		return partIndexId;
	}
	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}
	@Column(name="MEAN_SHOP_PRO_TIME")
	public int getMeanShopProcessingTime() {
		return meanShopProcessingTime;
	}
	public void setMeanShopProcessingTime(int meanShopProcessingTime) {
		this.meanShopProcessingTime = meanShopProcessingTime;
	}
	@Column(name="REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	@Column(name="REPAIR_PROCESS_REMARKS")
	public String getRepairProcessRemarks() {
		return repairProcessRemarks;
	}
	public void setRepairProcessRemarks(String repairProcessRemarks) {
		this.repairProcessRemarks = repairProcessRemarks;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "REPAIR_PROCESS_CODES")
	public RepairProcessCodes getM_RepairProcessCodes() {
		return m_RepairProcessCodes;
	}
	public void setM_RepairProcessCodes(RepairProcessCodes m_RepairProcessCodes) {
		this.m_RepairProcessCodes = m_RepairProcessCodes;
	}
	@Enumerated(EnumType.STRING)
	@Column(name ="PRICE_TYPE_CODE")
	public PriceTypeCode getM_PriceTypeCode() {
		return m_PriceTypeCode;
	}
	public void setM_PriceTypeCode(PriceTypeCode m_PriceTypeCode) {
		this.m_PriceTypeCode = m_PriceTypeCode;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LICENSE_STATEMENT_ID")
	public LicenseStatement getM_LicenseStatement() {
		return m_LicenseStatement;
	}
	public void setM_LicenseStatement(LicenseStatement m_LicenseStatement) {
		this.m_LicenseStatement = m_LicenseStatement;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "EXCHANGE_UNIT_AI")
	public ExchangeUnitAvailableIndicator getM_ExchangeUnitAvailableIndicator() {
		return m_ExchangeUnitAvailableIndicator;
	}
	public void setM_ExchangeUnitAvailableIndicator(
			ExchangeUnitAvailableIndicator m_ExchangeUnitAvailableIndicator) {
		this.m_ExchangeUnitAvailableIndicator = m_ExchangeUnitAvailableIndicator;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "AIRCRAFT_SYSTEM_CODE")
	public AircraftSystemCode getM_AircraftSystemCode() {
		return m_AircraftSystemCode;
	}
	public void setM_AircraftSystemCode(AircraftSystemCode m_AircraftSystemCode) {
		this.m_AircraftSystemCode = m_AircraftSystemCode;
	}

	@Transient
	public String getRepairCodeId(){
		return repairCodeId;
	}
	public void setRepairCodeId(String repairCodeId){
		this.repairCodeId = repairCodeId;
	}

}