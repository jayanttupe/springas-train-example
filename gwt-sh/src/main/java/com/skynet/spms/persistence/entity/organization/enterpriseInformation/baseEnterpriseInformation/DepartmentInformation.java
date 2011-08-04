package com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 10-三月-2011 11:10:43
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_DEPARTMENT_INFO")
public class DepartmentInformation extends BaseEntity{

	private String department;
	private String remark;
	private List<DepartmentInformation> m_ChildDepartmentInformation = new ArrayList<DepartmentInformation>();
	private List<Duty> m_Duty = new ArrayList<Duty>();
	private String parentId;
	private String enterpriseId;
	
	

	@Column(name="DEPARTMENT")
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Reference(itemCls=DepartmentInformation.class)
	@OneToMany()
	@JoinColumn(name = "PARENT_ID")
	public List<DepartmentInformation> getM_ChildDepartmentInformation() {
		return m_ChildDepartmentInformation;
	}
	public void setM_ChildDepartmentInformation(
			List<DepartmentInformation> m_ChildDepartmentInformation) {
		this.m_ChildDepartmentInformation = m_ChildDepartmentInformation;
	}
	@Reference(itemCls=Duty.class)
	@OneToMany()
	@JoinColumn(name = "DEPARTMENT_ID")
	public List<Duty> getM_Duty() {
		return m_Duty;
	}
	public void setM_Duty(List<Duty> m_Duty) {
		this.m_Duty = m_Duty;
	}
	
	/*@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public DepartmentInformation getM_parentDepartmentInformation() {
		return m_parentDepartmentInformation;
	}
	public void setM_parentDepartmentInformation(
			DepartmentInformation m_parentDepartmentInformation) {
		this.m_parentDepartmentInformation = m_parentDepartmentInformation;
	}*/
	
	@Column(name="PARENT_ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name="ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}