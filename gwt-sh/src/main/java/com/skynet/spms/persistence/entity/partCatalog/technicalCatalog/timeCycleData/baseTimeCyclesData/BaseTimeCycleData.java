package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.timeCycleData.baseTimeCyclesData;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.csdd.u.UnitCode;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:21
 */
@MappedSuperclass
public class BaseTimeCycleData extends BaseEntity {

	//周期数
	private int cycle;
	//描述
	private String description;
	//处理方法
	private String handling;
	//周期数单位
	private UnitCode m_UnitCode;
	
	@Column(name = "CYCLE")
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "HANDLING")
	public String getHandling() {
		return handling;
	}
	public void setHandling(String handling) {
		this.handling = handling;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_CODE")
	public UnitCode getM_UnitCode() {
		return m_UnitCode;
	}
	public void setM_UnitCode(UnitCode m_UnitCode) {
		this.m_UnitCode = m_UnitCode;
	}


}