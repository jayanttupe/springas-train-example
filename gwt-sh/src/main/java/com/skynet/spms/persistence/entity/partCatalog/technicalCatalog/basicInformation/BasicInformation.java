package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.e.EssentialityCode;
import com.skynet.spms.persistence.entity.csdd.p.PMAPartCausedDefectIndicator;
import com.skynet.spms.persistence.entity.csdd.p.ProductCategoryCode;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.partCatalog.technicalDocumentsCatalog.TechnicalPublishRecord;
import com.skynet.spms.persistence.entity.spmsdd.DangerCategory;
import com.skynet.spms.persistence.entity.spmsdd.EquipmentType;

/**
 * 件号基本数据是指该件的平均数据，基本不随机型的变化而发生变化的最基本基础技术数据。
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:21
 */
/*
 *Update by : Huhf    2011-4-21
 *CHECKED BY: Shanyf  2011-4-21
 *Confirm by: 
 * 
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_BASIC_INFO")
public class BasicInformation extends BaseEntity {
	
	
	/**
	 * KWD 关键词 长度为8， 数字及英文字母 NA。
	 */
	private String keyword_en;
	/**
	 * KWD 关键词 长度为8， 数字及英文字母 NA。
	 */
	private String keyword_zh;
	/**
	 * 零件的英文名称及说明。
	 */
	private String partName_en;
	/**
	 * 零件的中文名称及说明。
	 */
	private String partName_zh;
	
	/**
	 * 数据来源 长度 2位 N
	 */
	private int dataSource;
	/**
	 * 缩写 hazd Boolean that indicates if a consumable material is hazardous.
	 * 0 False
	 * 1 True
	 */
	private boolean isHazardousMaterial;//是否危险品材料
	/*
	 * 危险品材料描述
	 */
	private String hazardousMaterialDescription;
	/*
	 * 危险品材料分类
	 */
	private DangerCategory m_DangerCategory;
	/*
	 * 静电
	 */
	private boolean isElectrostaticSensitiveDeviceIndicator;
	/*
	 * 上级组合件号
	 */
	private String nextHigherAssemblyPartNumber;
	/**
	 * 缩写 SOR
	 * 提供当前件的原始信息
	 * Provides information in code form about the origin of the part (e.g.,'OEM',
	 * '121', or '129').
	 */
	private String partSourceCode;
	
	
	private int QPA;
	/**
	 * REM
	 */
	private String remarkText;
	/*
	 * 航材类型代码
	 */
	private SparePartClassCode m_SparePartClassCode;
	/*
	 * pMA标识件
	 */
	private PMAPartCausedDefectIndicator m_PMAPartCausedDefectIndicator;
	/*
	 * 重要性代码
	 */
	private EssentialityCode m_EssentialityCode;
	/*
	 * 技术出版记录
	 */
	private TechnicalPublishRecord m_technicalPublishRecord;
	/*
	 * 产品分类代码     没有用到
	 */
	private ProductCategoryCode m_ProductCategoryCode;
	/*
	 * 计量代码
	 */
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	/*
	 * BFE/SFE
	 */
	private BuyerFurnishedEquipmentIndicator m_BuyerFurnishedEquipmentIndicator;
	
	/*
	 * 适用机型
	 */
	private  String suitableAircraftModel;
	/**
	 * 是否COMAC专利件 false标识不是 True标识是
	 */
	private boolean isCOMACPatent;
	/**
	 * COMAC专利件描述
	 */
	private String patentDescription;
	
	private boolean isSerial;//增加是否序号控制
	
	private boolean isEngine;//增加是否发动机件
	
	private EquipmentType m_EquipmentType;//设备类型
	
	
	private String assy; //分组件
	
	private String ataNumber; //ATA章节号，飞机系统代码
	
	@Column(name = "ATA_NUMBER")
	public String getAtaNumber() {
		return ataNumber;
	}
	public void setAtaNumber(String ataNumber) {
		this.ataNumber = ataNumber;
	}
	@Column(name = "IS_SERIAL")
	public boolean isSerial() {
		return isSerial;
	}
	public void setSerial(boolean isSerial) {
		this.isSerial = isSerial;
	}
	@Column(name = "IS_ENGINE")
	public boolean isEngine() {
		return isEngine;
	}
	public void setEngine(boolean isEngine) {
		this.isEngine = isEngine;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "EQUIPMENT_TYPE")
	public EquipmentType getM_EquipmentType() {
		return m_EquipmentType;
	}
	public void setM_EquipmentType(EquipmentType m_EquipmentType) {
		this.m_EquipmentType = m_EquipmentType;
	}	
	@Column(name = "IS_ELECTROSTATIC_SENSITIVE")
	public boolean isElectrostaticSensitiveDeviceIndicator() {
		return isElectrostaticSensitiveDeviceIndicator;
	}
	public void setElectrostaticSensitiveDeviceIndicator(
			boolean isElectrostaticSensitiveDeviceIndicator) {
		this.isElectrostaticSensitiveDeviceIndicator = isElectrostaticSensitiveDeviceIndicator;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_MEASURE_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}
	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}
	@Column(name="SUITABLE_AIRCRAFT_MODEL")
	public String getSuitableAircraftModel() {
		return suitableAircraftModel;
	}
	public void setSuitableAircraftModel(String suitableAircraftModel) {
		this.suitableAircraftModel = suitableAircraftModel;
	}
	@Column(name="KEYWORD_EN")
	public String getKeyword_en() {
		return keyword_en;
	}
	public void setKeyword_en(String keyword_en) {
		this.keyword_en = keyword_en;
	}
	@Column(name="KEYWORD_ZH")
	public String getKeyword_zh() {
		return keyword_zh;
	}
	public void setKeyword_zh(String keyword_zh) {
		this.keyword_zh = keyword_zh;
	}
	@Column(name="PART_NAME_EN")
	public String getPartName_en() {
		return partName_en;
	}
	public void setPartName_en(String partName_en) {
		this.partName_en = partName_en;
	}
	@Column(name="PART_NAME_ZH")
	public String getPartName_zh() {
		return partName_zh;
	}
	public void setPartName_zh(String partName_zh) {
		this.partName_zh = partName_zh;
	}

	@Column(name="DATA_SOURCE")
	public int getDataSource() {
		return dataSource;
	}
	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}
	@Column(name="IS_HAZARDOUS_MATERIAL")
	public boolean isHazardousMaterial() {
		return isHazardousMaterial;
	}
	public void setHazardousMaterial(boolean isHazardousMaterial) {
		this.isHazardousMaterial = isHazardousMaterial;
	}
	@Column(name="NEXT_HIGHER_ASSEMBLY_PN")
	public String getNextHigherAssemblyPartNumber() {
		return nextHigherAssemblyPartNumber;
	}
	public void setNextHigherAssemblyPartNumber(String nextHigherAssemblyPartNumber) {
		this.nextHigherAssemblyPartNumber = nextHigherAssemblyPartNumber;
	}
	@Column(name="PART_SOURCE_CODE")
	public String getPartSourceCode() {
		return partSourceCode;
	}
	public void setPartSourceCode(String partSourceCode) {
		this.partSourceCode = partSourceCode;
	}
	@Column(name="QPA")
	public int getQpa() {
		return QPA;
	}
	public void setQpa(int qPA) {
		this.QPA = qPA;
	}
	@Column(name="REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "SPARE_PART_CLASS_CODE")
	public SparePartClassCode getM_SparePartClassCode() {
		return m_SparePartClassCode;
	}
	public void setM_SparePartClassCode(SparePartClassCode m_SparePartClassCode) {
		this.m_SparePartClassCode = m_SparePartClassCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PMA_PART_CAUSED_DI")
	public PMAPartCausedDefectIndicator getM_PMAPartCausedDefectIndicator() {
		return m_PMAPartCausedDefectIndicator;
	}
	public void setM_PMAPartCausedDefectIndicator(
			PMAPartCausedDefectIndicator m_PAMPartCausedDefectIndicator) {
		this.m_PMAPartCausedDefectIndicator = m_PAMPartCausedDefectIndicator;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "ESSENTIALITY_CODE")
	public EssentialityCode getM_EssentialityCode() {
		return m_EssentialityCode;
	}
	public void setM_EssentialityCode(EssentialityCode m_EssentialityCode) {
		this.m_EssentialityCode = m_EssentialityCode;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="TECHNICAL_PR_ID")
	public TechnicalPublishRecord getM_technicalPublishRecord() {
		return m_technicalPublishRecord;
	}
	public void setM_technicalPublishRecord(
			TechnicalPublishRecord m_technicalPublishRecord) {
		this.m_technicalPublishRecord = m_technicalPublishRecord;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PRODUCT_CATEGORY_CODE")
	public ProductCategoryCode getM_ProductCategoryCode() {
		return m_ProductCategoryCode;
	}
	public void setM_ProductCategoryCode(ProductCategoryCode m_ProductCategoryCode) {
		this.m_ProductCategoryCode = m_ProductCategoryCode;
	}
	
	@Column(name="HAZARDOUS_METERIAL_DESCN")
	public String getHazardousMaterialDescription() {
		return hazardousMaterialDescription;
	}
	public void setHazardousMaterialDescription(String hazardousMaterialDescription) {
		this.hazardousMaterialDescription = hazardousMaterialDescription;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="DANGER_CATEGORY")
	public DangerCategory getM_DangerCategory() {
		return m_DangerCategory;
	}
	public void setM_DangerCategory(DangerCategory m_DangerCategory) {
		this.m_DangerCategory = m_DangerCategory;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="BUYER_FURNISHED_EI")
	public BuyerFurnishedEquipmentIndicator getM_BuyerFurnishedEquipmentIndicator() {
		return m_BuyerFurnishedEquipmentIndicator;
	}
	public void setM_BuyerFurnishedEquipmentIndicator(
			BuyerFurnishedEquipmentIndicator m_BuyerFurnishedEquipmentIndicator) {
		this.m_BuyerFurnishedEquipmentIndicator = m_BuyerFurnishedEquipmentIndicator;
	}
	@Column(name="IS_COMAC_PATENT")
	public boolean isComacPatent() {
		return isCOMACPatent;
	}
	public void setComacPatent(boolean isCOMACPatent) {
		this.isCOMACPatent = isCOMACPatent;
	}
	@Column(name="PATENT_DESCRIPTION")
	public String getPatentDescription() {
		return patentDescription;
	}
	public void setPatentDescription(String patentDescription) {
		this.patentDescription = patentDescription;
	}
	@Column(name="ASSY")
	public String getAssy() {
		return assy;
	}
	public void setAssy(String assy) {
		this.assy = assy;
	}
}