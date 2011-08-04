package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.optionalData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.i.InterchangeabilityCode;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;

/**
 * 互换备件信息是指当前件号索引的备件可以被交换，更换，或者可以替换其他备件的目录域说明。
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:23
 */
/*************************************************************************
 *Update by : Huhf    2011-4-21
 *CHECKED BY: Shanyf  2011-4-21
 *Confirm by: 
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_OPTIONAL_PART")
public class OptionalPart extends BaseEntity {

	/**
	 * 
	 * OEM原指由采购方提供设备和技术，由制造方提供人力和场地，采购方负责销售，制造方负责生产的一种现代流行的生产方式。但是，目前大多采用由采购方提供品牌和授权，由制
	 * 造方生产贴有该品牌产品的方式。 
	 */
	//protected boolean isOEM;
	/**
	 * 标明可替换的其他件号，True标识记录数据的件号可以被当前可选备件聚合的件号所替换。
	 */
	//protected boolean isReplaced; 
	/**
	 * 长度 32位 AN
	 * Identifies a part which is completely interchangeable in form, fit and function
	 * with the subject part number, thus providing a choice of parts for procurement
	 * and support purposes.
	 */
	/*
	 * 备件外键
	 */
	private String partIndexId;
	/*
	 * 可互换件号
	 */
	private String optionalPartNumber;
	/**
	 * 缩写 OPT  26位 字符长度
	 * 
	 * Identifies a part which is completely interchangeable in form, fit and function
	 * with the subject part number, thus providing a choice of parts for procurement
	 * and support
	 * purposes.
	 * Refer to Part Number, Manufacturer Code and Optional Part Number for definition
	 * and structure.
	 */
	/*
	 * 描述
	 */
	private String optionalPartNumberText;
    /*
     * 互换类别
     */
	private InterchangeabilityCode m_InterchangeabilityCode;
	
	
	@Column(name = "PART_INDEX_ID")
	public String getPartIndexId() {
		return partIndexId;
	}
	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}
	@Column(name = "OPTIONAL_PART_NUMBER")
	public void setOptionalPartNumber(String optionalPartNumber) {
		this.optionalPartNumber = optionalPartNumber;
	}
	public void setOptionalPartNumberText(String optionalPartNumberText) {
		this.optionalPartNumberText = optionalPartNumberText;
	}
	@Column(name = "OPTIONAL_PART_TEXT")
	public String getOptionalPartNumberText() {
		return optionalPartNumberText;
	}
	public String getOptionalPartNumber() {
		return optionalPartNumber;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "INTERCHANGEABILITY_CODE")
	public InterchangeabilityCode getM_InterchangeabilityCode() {
		return m_InterchangeabilityCode;
	}
	public void setM_InterchangeabilityCode(
			InterchangeabilityCode m_InterchangeabilityCode) {
		this.m_InterchangeabilityCode = m_InterchangeabilityCode;
	}

}