package com.skynet.spms.persistence.entity.organization.enterpriseInformation.COMACSC;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 24-三月-2011 14:44:59
 */
@Entity
@DiscriminatorValue("COMACSC")
public class COMACSC extends BaseEnterpriseInformation {

	public List<DepartmentInformation> m_DepartmentInformation; 
	
	@Reference(itemCls=DepartmentInformation.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<DepartmentInformation> getM_DepartmentInformation() {
		return m_DepartmentInformation;
	}
	public void setM_DepartmentInformation(
			List<DepartmentInformation> m_DepartmentInformation) {
		this.m_DepartmentInformation = m_DepartmentInformation;
	}

}