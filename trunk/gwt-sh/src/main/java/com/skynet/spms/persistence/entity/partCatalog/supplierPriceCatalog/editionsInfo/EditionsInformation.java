package com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo;

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
 * @author 王坤杰
 * @version 1.0
 * @created 30-四月-2011 11:27:48
 */

@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_EDITIONS_INFO")
public class EditionsInformation extends BaseEntity {

	private Date editionsReviseDate;//版次修订日期
	private String editionsReviseMan;//修订人
	private String releaseMan;//发布人
	private Date releaseVersionDate;//发布日期
	
	@Column(name = "EDITIONS_REVISE_DATE")
	public Date getEditionsReviseDate() {
		return editionsReviseDate;
	}
	public void setEditionsReviseDate(java.util.Date editionsReviseDate) {
		this.editionsReviseDate = editionsReviseDate;
	}
	
	@Column(name = "EDITIONS_REVISE_MAN")
	public String getEditionsReviseMan() {
		return editionsReviseMan;
	}
	public void setEditionsReviseMan(String editionsReviseMan) {
		this.editionsReviseMan = editionsReviseMan;
	}
	
	@Column(name = "RELEASE_MAN")
	public String getReleaseMan() {
		return releaseMan;
	}
	public void setReleaseMan(String releaseMan) {
		this.releaseMan = releaseMan;
	}
	
	@Column(name = "RELEASE_VERSION_DATE")
	public java.util.Date getReleaseVersionDate() {
		return releaseVersionDate;
	}
	public void setReleaseVersionDate(Date releaseVersionDate) {
		this.releaseVersionDate = releaseVersionDate;
	}
}