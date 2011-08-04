package com.skynet.spms.persistence.entity.organization.userInformation;
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
 * @created 18-四月-2011 8:54:18
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_BUSSINE_MON_REC")
public class UserBussinessMonitoringRecord extends BaseEntity {

	private String businessCreateDate;
	private String bussinessName;
	private String bussinesType;
	private String uuid;
	private String userId;
	
	@Column(name = "BUSINESS_CREATE_DATE")
	public String getBusinessCreateDate() {
		return businessCreateDate;
	}
	public void setBusinessCreateDate(String businessCreateDate) {
		this.businessCreateDate = businessCreateDate;
	}
	@Column(name = "BUSSINESS_NAME")
	public String getBussinessName() {
		return bussinessName;
	}
	public void setBussinessName(String bussinessName) {
		this.bussinessName = bussinessName;
	}
	@Column(name = "BUSSINESS_TYPE")
	public String getBussinesType() {
		return bussinesType;
	}
	public void setBussinesType(String bussinesType) {
		this.bussinesType = bussinesType;
	}
	@Column(name = "UUID")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}