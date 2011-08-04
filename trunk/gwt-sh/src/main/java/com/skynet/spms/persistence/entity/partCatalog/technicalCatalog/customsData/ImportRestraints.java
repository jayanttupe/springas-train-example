package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:22
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
@Table(name = "SPMS_IMPORT_RESTRAINTS")
public class ImportRestraints extends BaseEntity{
	/*
	 * 进口限制说明
	 */
	private String description;
	/*
	 * 是否进口限制
	 */
	private boolean isImportRestraints;
	/*
	 * 附件说明
	 */
	public List<Attachment> m_Attachment;
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column( name = "IS_IMPORT_RESTRAINTS")
	public boolean isImportRestraints() {
		return isImportRestraints;
	}
	public void setImportRestraints(boolean isImportRestraints) {
		this.isImportRestraints = isImportRestraints;
	}
	@Reference(itemCls=Attachment.class)
	@OneToMany()
	@JoinColumn(name = "RELATED_BUSSINESS_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}
	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}


}