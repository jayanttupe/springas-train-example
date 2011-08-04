package com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.credentials;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.CredentialsUseStatus;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CREDENTIALS")
public class Credentials extends BaseEntity {
	/**
	 * 备件中心位置
	 */
	private String partCentreLocation;

	/**
	 * 证书存档编号
	 */
	private String credentialsCode;

	/**
	 * 证书存档位置
	 */
	private String stackPositionCode;

	/**
	 * 序列号(From)
	 */
	private String partNumberFrom;

	/**
	 * 序列号(To)
	 */
	private String partNumberTo;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;

	/**
	 * 证书扫描文件
	 */
	private String credentialsFile;
	
	/**
	 * 使用状况
	 */
	private CredentialsUseStatus credentialsUseStatus; 

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 是否生成过的证书(0:未生成/1:已经生成)
	 */
	private String createdFlg = "0";
	

	public Credentials() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "PART_CENTRE_LOCATION")
	public String getPartCentreLocation() {
		return partCentreLocation;
	}

	public void setPartCentreLocation(String partCentreLocation) {
		this.partCentreLocation = partCentreLocation;
	}

	@Column(name = "CREDENTIALS_CODE")
	public String getCredentialsCode() {
		return credentialsCode;
	}

	public void setCredentialsCode(String credentialsCode) {
		this.credentialsCode = credentialsCode;
	}

	@Column(name = "STACK_POSITION_CODE")
	public String getStackPositionCode() {
		return stackPositionCode;
	}

	public void setStackPositionCode(String stackPositionCode) {
		this.stackPositionCode = stackPositionCode;
	}

	@Transient
	public String getPartNumberFrom() {
		return partNumberFrom;
	}

	public void setPartNumberFrom(String partNumberFrom) {
		this.partNumberFrom = partNumberFrom;
	}

	@Transient
	public String getPartNumberTo() {
		return partNumberTo;
	}

	public void setPartNumberTo(String partNumberTo) {
		this.partNumberTo = partNumberTo;
	}

	@Transient
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Transient
	public String getPartSerialNumber() {
		return partSerialNumber;
	}

	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}

	@Transient
	public String getCredentialsFile() {
		return credentialsFile;
	}

	public void setCredentialsFile(String credentialsFile) {
		this.credentialsFile = credentialsFile;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CREDENTIALS_USE_STATUS")
	public CredentialsUseStatus getCredentialsUseStatus() {
		return credentialsUseStatus;
	}

	public void setCredentialsUseStatus(CredentialsUseStatus credentialsUseStatus) {
		this.credentialsUseStatus = credentialsUseStatus;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient 
	public String getCreatedFlg() {
		return createdFlg;
	}

	public void setCreatedFlg(String createdFlg) {
		this.createdFlg = createdFlg;
	}
	

}