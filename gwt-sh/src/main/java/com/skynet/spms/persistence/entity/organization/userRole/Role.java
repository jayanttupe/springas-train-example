package com.skynet.spms.persistence.entity.organization.userRole;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.organization.userInformation.User;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:03
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_ROLE")
public class Role extends BaseEntity{

	private String roleName;
	
	private String roleTitle_zh;
	
	private String roleTitle_en;
	
	private Integer approvalQuota;
	
	private List<User> m_User;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			 mappedBy = "m_Role",
	         targetEntity =User.class
	)
	public List<User> getM_User() {
		return m_User;
	}
	public void setM_User(List<User> m_User) {
		this.m_User = m_User;
	}
	@Column(name="ROLE_TITLE_ZH")
	public String getRoleTitle_zh() {
		return roleTitle_zh;
	}
	public void setRoleTitle_zh(String roleTitle_zh) {
		this.roleTitle_zh = roleTitle_zh;
	}
	@Column(name="ROLE_TITLE_EN")
	public String getRoleTitle_en() {
		return roleTitle_en;
	}
	public void setRoleTitle_en(String roleTitle_en) {
		this.roleTitle_en = roleTitle_en;
	}
	@Column(name="ROLE_NAME",unique=true)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name="APPROVAL_QUOTA")
	public Integer getApprovalQuota() {
		return approvalQuota;
	}
	public void setApprovalQuota(Integer approvalQuota) {
		this.approvalQuota = approvalQuota;
	}
	
}