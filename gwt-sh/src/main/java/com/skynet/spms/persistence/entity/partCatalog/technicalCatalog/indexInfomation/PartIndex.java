package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.financeData.FinanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.optionalData.OptionalPart;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.partBagItem.PartBagItem;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;

/**
 * 件号索引保存了零件索引信息。在本系统中，有许多业务都与零件技术目录有关联，通常都是通过件号、通用件号以及超长件号来进行弱关联。
 * @author 曹宏炜
 * @version 1.1
 * @created 18-四月-2011 14:14:24
 */

/*************************************************************************
 *Update by : Huhf    2011-4-25
 *CHECKED BY: Shanyf  2011-4-21
 *  m_AircraftModelIdentifier,
 *  m_PartSupplierPriceIndex
 *  m_partSaleRelease
 *  m_RepairData   should be check later
 *
 *Confirm by: 
 * 
 *************************************************************************/
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_PART_INDEX")
public class PartIndex extends BaseEntity {

	/**
	 * MNR 制造商完整件号 长度为32数字及英文字母NA(N标识数字，A标识英文字符)。 该件号一般情况下表示为制造商提供的厂商完整件号。
	 */
	private String manufacturerPartNumber;
	
	/**
	 * PNR 零件号 长度为15 数字及英文字母 NA。该件号一般情况下表示为ARJ21统一规定的零件号。 
	 */
	private String partNumber;

	/**
	 * OPN 超长零件号 长度为16-32 数字及英文字母 NA。 该件号一般情况下表示特别长度的零件号。
	 */
	public String overlengthPartNumber;
	/**
	 * UCN 唯一部件标识号，The permanent tracking identity assigned to an in‐service part in
	 * lieu of the manufacturer's serial number. 长度 1-15A/N。
	 */
	public String uniqueComponentIdentificationNumber;
	
	/**
	 * KWD 关键词 长度为8， 数字及英文字母 NA。
	 */
	private String keyword_en;
	/**
	 * KWD 关键词 长度为8， 数字及英文字母 NA。
	 */
	private String keyword_zh;
	
	/**
	 * 是否航材包
	 */
	private boolean m_IsPartbag;
	
    private PartTechnicalData m_PartTechnicalData;
	private ManufacturerCode m_ManufacturerCode;
	private List<OptionalPart> m_OptionalPart;
	private FinanceData m_FinanceData;
	private BasicInformation m_BasicInformation;
	private PartApplicationData m_PartApplicationData;
	private CustomsClearanceData m_CustomsClearanceData;
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;
	private List<PartBagItem> m_PartBagItem;
	
	@Column(name="OVERLENGTH_PART_NUMBER")
	public String getOverlengthPartNumber() {
		return overlengthPartNumber;
	}
	public void setOverlengthPartNumber(String overlengthPartNumber) {
		this.overlengthPartNumber = overlengthPartNumber;
	}
	@Column(name="UNIQUE_COMPONENT_IN")
	public String getUniqueComponentIdentificationNumber() {
		return uniqueComponentIdentificationNumber;
	}
	public void setUniqueComponentIdentificationNumber(
			String uniqueComponentIdentificationNumber) {
		this.uniqueComponentIdentificationNumber = uniqueComponentIdentificationNumber;
	}
	
	@Column(name="MANUVACTURER_PART_NUMBER")
	public String getManufacturerPartNumber() {
		return manufacturerPartNumber;
	}
	public void setManufacturerPartNumber(String manufacturerPartNumber) {
		this.manufacturerPartNumber = manufacturerPartNumber;
	}
	@Column(name="PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	@Column(name="KEYWORD_ZH")
	public String getKeyword_zh() {
		return keyword_zh;
	}
	public void setKeyword_zh(String keywordZh) {
		this.keyword_zh = keywordZh;
	}
	
	@Column(name="KEYWORD_EN")
	public String getKeyword_en() {
		return keyword_en;
	}
	public void setKeyword_en(String keywordEn) {
		this.keyword_en = keywordEn;
	}
	
	@Column(name = "IS_PART_BAG")
	public boolean isM_IsPartbag() {
		return m_IsPartbag;
	}
	public void setM_IsPartbag(boolean mIsPartbag) {
		this.m_IsPartbag = mIsPartbag;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PART_TECHNICAL_DATA_ID")
	public PartTechnicalData getM_PartTechnicalData() {
		return m_PartTechnicalData;
	}
	public void setM_PartTechnicalData(PartTechnicalData m_PartTechnicalData) {
		this.m_PartTechnicalData = m_PartTechnicalData;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="MANUFACTURER_CODE_ID")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}
	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FINANCE_DATA_ID")
	public FinanceData getM_FinanceData() {
		return m_FinanceData;
	}
	public void setM_FinanceData(FinanceData m_FinanceData) {
		this.m_FinanceData = m_FinanceData;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="BASIC_INFO_ID")
	public BasicInformation getM_BasicInformation() {
		return m_BasicInformation;
	}
	public void setM_BasicInformation(BasicInformation m_BasicInformation) {
		this.m_BasicInformation = m_BasicInformation;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMS_CLEARANCE_DATA_ID")
	public CustomsClearanceData getM_CustomsClearanceData() {
		return m_CustomsClearanceData;
	}
	public void setM_CustomsClearanceData(
			CustomsClearanceData m_CustomsClearanceData) {
		this.m_CustomsClearanceData = m_CustomsClearanceData;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_PUBLISH_STATUS_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}
	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}
	@Reference(itemCls=OptionalPart.class)
	@OneToMany()
	@JoinColumn(name = "PART_INDEX_ID")
	public List<OptionalPart> getM_OptionalPart() {
		return m_OptionalPart;
	}
	public void setM_OptionalPart(List<OptionalPart> m_OptionalPart) {
		this.m_OptionalPart = m_OptionalPart;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="APPLICATION_DATA_ID")
	public PartApplicationData getM_PartApplicationData() {
		return m_PartApplicationData;
	}
	public void setM_PartApplicationData(
			PartApplicationData m_PartApplicationData) {
		this.m_PartApplicationData = m_PartApplicationData;
	}
	@Reference(itemCls=PartBagItem.class)
	@OneToMany()
	@JoinColumn(name = "PART_INDEX_ID")
	public List<PartBagItem> getM_PartBagItem() {
		return m_PartBagItem;
	}
	public void setM_PartBagItem(List<PartBagItem> mPartBagItem) {
		this.m_PartBagItem = mPartBagItem;
	}

	/*@Reference(itemCls=AircraftConfiguration.class)
	@OneToMany()
	@JoinColumn(name = "PART_INDEX_ID")
	public List<AircraftConfiguration> getM_AircraftConfig() {
		return m_AircraftConfig;
	}
	public void setM_AircraftConfig(List<AircraftConfiguration> m_AircraftConfig) {
		this.m_AircraftConfig = m_AircraftConfig;
	}*/
	
}