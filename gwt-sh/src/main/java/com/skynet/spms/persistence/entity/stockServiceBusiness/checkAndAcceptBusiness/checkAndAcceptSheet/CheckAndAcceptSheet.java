package com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CHECK_AND_ACCEPT_SHEET")
public class CheckAndAcceptSheet extends BaseEntity {
	/**
	 * 检验单编号
	 */
	private String checkAndAcceptSheetNumber;

	/**
	 * 检验日期
	 */
	private Date checkDate;

	/**
	 * 检验人
	 */
	private String checkUser;

	/**
	 * 业务类型
	 */
	private BusinessType businessType;

	/**
	 * 收料单ID
	 */
	private String receivingSheetID;

	/**
	 * 收料单明细ID
	 */
	private String receivingSheetItemsID;

	/**
	 * 收料单编号
	 */
	private String receivingSheetNumber;

	/**
	 * 合同编号
	 */
	private String contratNumber;

	/**
	 * 装箱单号
	 */
	private String packingListNumber;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;

	/**
	 * 零件名称
	 */
	private String partName;

	/**
	 * 数量
	 */
	private int quantity;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode unit;

	/**
	 * 制造商
	 */
	private String manufacturerName;

	/**
	 * 供应商
	 */
	private String providerName;

	/**
	 * 是否抽样
	 */
	private String isSampling;

	/**
	 * 备件分类
	 */
	private SparePartClassCode partType;

	/**
	 * 抽样方案
	 */
	private String samplingScheme;

	/**
	 * 合格数量
	 */
	private String succeedQuantity;

	/**
	 * 不合格数量
	 */
	private String failQuantity;

	/**
	 * 备件状况
	 */
	private String partStatus;

	/**
	 * 备件质量外观
	 */
	private String partsQualityAppearance;

	/**
	 * 是否时控件
	 */
	private String isTimePart;

	/**
	 * 是否时寿件
	 */
	private String isLifePart;

	/**
	 * 时控件寿命周期
	 */
	private String storageRacksLife;

	/**
	 * 时寿件寿命周期
	 */
	private String lifePartCycle;

	/**
	 * 生产日期
	 */
	private Date manufactureDate;

	/**
	 * 时控件到限日期
	 */
	private Date limitDate;

	/**
	 * 时寿件到限日期
	 */
	private Date lifeDate;

	/**
	 * 随机证件
	 */
	private String credentials;

	/**
	 * 随机证件
	 */
	private String[] credentialsList;

	/**
	 * 证书文件编号
	 */
	private String certificateCode;

	/**
	 * 证书扫描文件名称
	 */
	private String certificateFileName;

	/**
	 * 验收结论
	 */
	private String checkResult;
	
	/**
	 * 0:正常执行
	 * 1:当前证书编号不存在
	 * 2:当前证书已经被使用
	 */
	private String executeResult = "0";

	public CheckAndAcceptSheet() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "CHECK_AND_ACCEPT_SHEET_NUMBER")
	public String getCheckAndAcceptSheetNumber() {
		return checkAndAcceptSheetNumber;
	}

	public void setCheckAndAcceptSheetNumber(String checkAndAcceptSheetNumber) {
		this.checkAndAcceptSheetNumber = checkAndAcceptSheetNumber;
	}

	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "CHECK_USER")
	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BUSINESS_TYPE")
	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Column(name = "RECEIVING_SHEET_ID")
	public String getReceivingSheetID() {
		return receivingSheetID;
	}

	public void setReceivingSheetID(String receivingSheetID) {
		this.receivingSheetID = receivingSheetID;
	}

	@Column(name = "RECEIVING_SHEET_ITEMS_ID")
	public String getReceivingSheetItemsID() {
		return receivingSheetItemsID;
	}

	public void setReceivingSheetItemsID(String receivingSheetItemsID) {
		this.receivingSheetItemsID = receivingSheetItemsID;
	}

	@Column(name = "RECEIVING_SHEET_NUMBER")
	public String getReceivingSheetNumber() {
		return receivingSheetNumber;
	}

	public void setReceivingSheetNumber(String receivingSheetNumber) {
		this.receivingSheetNumber = receivingSheetNumber;
	}

	@Column(name = "CONTRAT_NUMBER")
	public String getContratNumber() {
		return contratNumber;
	}

	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}

	@Column(name = "PACKING_LIST_NUMBER")
	public String getPackingListNumber() {
		return packingListNumber;
	}

	public void setPackingListNumber(String packingListNumber) {
		this.packingListNumber = packingListNumber;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "PART_SERIAL_NUMBER")
	public String getPartSerialNumber() {
		return partSerialNumber;
	}

	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}

	@Transient
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Enumerated(EnumType.STRING)
	@Transient
	public UnitOfMeasureCode getUnit() {
		return unit;
	}

	public void setUnit(UnitOfMeasureCode unit) {
		this.unit = unit;
	}

	@Column(name = "MANUFACTURER_NAME")
	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	@Column(name = "PROVIDER_NAME")
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@Column(name = "IS_SAMPLING")
	public String getIsSampling() {
		return isSampling;
	}

	public void setIsSampling(String isSampling) {
		this.isSampling = isSampling;
	}

	@Enumerated(EnumType.STRING)
	@Transient
	public SparePartClassCode getPartType() {
		return partType;
	}

	public void setPartType(SparePartClassCode partType) {
		this.partType = partType;
	}

	@Column(name = "SAMPLING_SCHEME")
	public String getSamplingScheme() {
		return samplingScheme;
	}

	public void setSamplingScheme(String samplingScheme) {
		this.samplingScheme = samplingScheme;
	}

	@Column(name = "SUCCEED_QUANTITY")
	public String getSucceedQuantity() {
		return succeedQuantity;
	}

	public void setSucceedQuantity(String succeedQuantity) {
		this.succeedQuantity = succeedQuantity;
	}

	@Column(name = "FAIL_QUANTITY")
	public String getFailQuantity() {
		return failQuantity;
	}

	public void setFailQuantity(String failQuantity) {
		this.failQuantity = failQuantity;
	}

	@Column(name = "PART_STATUS")
	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}

	@Column(name = "PARTS_QUALITY_APPEARANCE")
	public String getPartsQualityAppearance() {
		return partsQualityAppearance;
	}

	public void setPartsQualityAppearance(String partsQualityAppearance) {
		this.partsQualityAppearance = partsQualityAppearance;
	}

	@Column(name = "IS_TIME_PART")
	public String getIsTimePart() {
		return isTimePart;
	}

	public void setIsTimePart(String isTimePart) {
		this.isTimePart = isTimePart;
	}

	@Column(name = "IS_LIFE_PART")
	public String getIsLifePart() {
		return isLifePart;
	}

	public void setIsLifePart(String isLifePart) {
		this.isLifePart = isLifePart;
	}

	@Column(name = "STORAGE_RACKS_LIFE")
	public String getStorageRacksLife() {
		return storageRacksLife;
	}

	public void setStorageRacksLife(String storageRacksLife) {
		this.storageRacksLife = storageRacksLife;
	}
	
	@Column(name = "LIFE_PART_CYCLE")
	public String getLifePartCycle() {
		return lifePartCycle;
	}

	public void setLifePartCycle(String lifePartCycle) {
		this.lifePartCycle = lifePartCycle;
	}

	@Column(name = "MANUFACTURE_DATE")
	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	@Column(name = "LIMIT_DATE")
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	@Column(name = "LIFE_DATE")
	public Date getLifeDate() {
		return lifeDate;
	}

	public void setLifeDate(Date lifeDate) {
		this.lifeDate = lifeDate;
	}

	@Column(name = "CREDENTIALS")
	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	@Transient
	public String[] getCredentialsList() {
		return credentialsList;
	}

	public void setCredentialsList(String[] credentialsList) {
		this.credentialsList = credentialsList;
	}

	@Column(name = "CERTIFICATE_CODE")
	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	@Transient
	public String getCertificateFileName() {
		return certificateFileName;}

	public void setCertificateFileName(String certificateFileName) {
		this.certificateFileName = certificateFileName;
	}

	@Column(name = "CHECK_RESULT")
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	@Transient
	public String getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}
}