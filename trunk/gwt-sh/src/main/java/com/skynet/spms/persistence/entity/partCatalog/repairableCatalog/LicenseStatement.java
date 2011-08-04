package com.skynet.spms.persistence.entity.partCatalog.repairableCatalog;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 18-四月-2011 14:14:22
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_LICENSE_STATEMENT")
public class LicenseStatement extends BaseEntity {
    
	private Date authorizationValidDate;//授权有效期
	private Date authorizedFromDate;//授权日期
	private String authorizedInstitutions;//授权机构
	private int licenseNumber;//许可证编号
	
	
	@Column(name = "AUTH_VALID_DATE")
	public Date getAuthorizationValidDate() {
		return authorizationValidDate;
	}
	public void setAuthorizationValidDate(Date authorizationValidDate) {
		this.authorizationValidDate = authorizationValidDate;
	}
	@Column(name = "AUTH_FORM_DATE")
	public Date getAuthorizedFromDate() {
		return authorizedFromDate;
	}
	public void setAuthorizedFromDate(Date authorizedFromDate) {
		this.authorizedFromDate = authorizedFromDate;
	}
	@Column(name = "AUTH_INSTI")
	public String getAuthorizedInstitutions() {
		return authorizedInstitutions;
	}
	public void setAuthorizedInstitutions(String authorizedInstitutions) {
		this.authorizedInstitutions = authorizedInstitutions;
	}
	@Column(name = "LICENSE_NUMBER")
	public int getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(int licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
}