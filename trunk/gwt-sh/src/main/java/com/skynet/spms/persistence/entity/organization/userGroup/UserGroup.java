package com.skynet.spms.persistence.entity.organization.userGroup;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.organization.userInformation.User;


/**
 * 实现用户与用户组之间多对多的绑定，通过用户组的绑定，可以对不同用户组内的用户进行相应的业务操作，例如删除用户，修改用户的权限等级等。
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:13
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_USERGROUP")
public class UserGroup extends BaseEntity {

	private String groupName;
	private String description;
	private BussinessStatusEntity m_BussinessStatusEntity;
	private List<User> m_User;
	private String roleNames;
	
	@Column(name="ROLE_NAMES")
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	/*@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			 mappedBy = "m_UserGroup",
	         targetEntity =User.class
	)*/
	
	@ManyToMany(cascade =CascadeType.PERSIST)
	@JoinTable(
			name = "SPMS_USER_USERGROUP", 
			joinColumns = { @JoinColumn(name = "USERGROUP_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "USER_ID") }
	)
	public List<User> getM_User() {
		return m_User;
	}
	public void setM_User(List<User> m_User) {
		this.m_User = m_User;
	}
	@Column(name="GROUP_NAME")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_STATUS_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}
	public void setM_BussinessStatusEntity(BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}
}