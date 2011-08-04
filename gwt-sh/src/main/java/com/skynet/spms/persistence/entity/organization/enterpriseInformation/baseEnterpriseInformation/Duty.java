package com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 曹宏炜
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
@Table(name="SPMS_DUTY")
public class Duty extends BaseEntity{

	private String dutyName;
	private Integer dutyLevel;
	private String dutyDescription;
	private String departmentId;
	
	@Column(name="DUTY_NAME")
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	@Column(name="DUTY_LEVEL")
	public Integer getDutyLevel() {
		return dutyLevel;
	}
	public void setDutyLevel(Integer dutyLevel) {
		this.dutyLevel = dutyLevel;
	}
	@Column(name="DUTY_DESCRIPTION")
	public String getDutyDescription() {
		return dutyDescription;
	}
	public void setDutyDescription(String dutyDescription) {
		this.dutyDescription = dutyDescription;
	}
	/*@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	public DepartmentInformation getM_departmentInformation() {
		return m_departmentInformation;
	}
	public void setM_departmentInformation(
			DepartmentInformation m_departmentInformation) {
		this.m_departmentInformation = m_departmentInformation;
	}*/
	@Column(name="DEPARTMENT_ID")
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}