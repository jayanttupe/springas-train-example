package com.skynet.spms.persistence.entity.organization.enterpriseInformation.qualityAssurance.supplierQAManage;
import java.util.ArrayList;
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
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.qualityAssurance.base.BaseQAEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:10
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_SUPPLIER_QA")
public class SupplierQAEntity extends BaseQAEntity{

	private String enterpriseId;
	
	private List<Attachment> m_Attachment = new ArrayList<Attachment>();
	
	@Column(name="ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
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