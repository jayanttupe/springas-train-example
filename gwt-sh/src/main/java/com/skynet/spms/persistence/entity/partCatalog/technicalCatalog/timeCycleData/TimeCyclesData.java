package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.timeCycleData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.csdd.t.TimeCycleCode;
import com.skynet.spms.persistence.entity.csdd.t.TimeCycleReferenceCode;
import com.skynet.spms.persistence.entity.csdd.t.TimeCycleType;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.timeCycleData.baseTimeCyclesData.BaseTimeCycleData;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:27
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
@Table(name = "SPMS_TIME_CYCLES_DATA")
public class TimeCyclesData extends BaseTimeCycleData {
	
	//时控代码
	private TimeCycleCode m_TimeCycleCode;
	//时控参考代码
	private TimeCycleReferenceCode m_TimeCycleReferenceCode;
	//周期类型
	private TimeCycleType m_TimeCycleType;
	//技术数据ID(外键)
	private String partTechnicalDataId;
	
	@Column(name = "PART_TECHNICAL_DATA_ID")
	public String getPartTechnicalDataId() {
		return partTechnicalDataId;
	}
	public void setPartTechnicalDataId(String partTechnicalDataId) {
		this.partTechnicalDataId = partTechnicalDataId;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "TIME_CYCLE_TYPE")
	public TimeCycleType getM_TimeCycleType() {
		return m_TimeCycleType;
	}
	public void setM_TimeCycleType(TimeCycleType m_TimeCycleType) {
		this.m_TimeCycleType = m_TimeCycleType;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "TIME_CYCLE_CODE")
	public TimeCycleCode getM_TimeCycleCode() {
		return m_TimeCycleCode;
	}
	public void setM_TimeCycleCode(TimeCycleCode m_TimeCycleCode) {
		this.m_TimeCycleCode = m_TimeCycleCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "TIME_CYCLE_REFERENCE_CODE")
	public TimeCycleReferenceCode getM_TimeCycleReferenceCode() {
		return m_TimeCycleReferenceCode;
	}
	public void setM_TimeCycleReferenceCode(
			TimeCycleReferenceCode m_TimeCycleReferenceCode) {
		this.m_TimeCycleReferenceCode = m_TimeCycleReferenceCode;
	}
	

}