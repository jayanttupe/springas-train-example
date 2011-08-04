package com.skynet.spms.persistence.entity.partCatalog.technicalDocumentsCatalog;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.TechnicalPublishType;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:27
 */
/*************************************************************************
 *Update by : Huhf    2011-4-21
 *CHECKED BY: 
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
@Table(name = "SPMS_TECH_PUBLISH_DOC")
public class TechnicalPublishDoc extends BaseEntity {

	private String location;//技术文件位置
	
	private TechnicalPublishType m_TechnicalPublishType;//技术出版物类型
	
	@Column(name="LOCATION")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TECHNICAL_PUBLISH_TYPE")
	public TechnicalPublishType getM_TechnicalPublishType() {
		return m_TechnicalPublishType;
	}
	public void setM_TechnicalPublishType(
			TechnicalPublishType m_TechnicalPublishType) {
		this.m_TechnicalPublishType = m_TechnicalPublishType;
	}

}