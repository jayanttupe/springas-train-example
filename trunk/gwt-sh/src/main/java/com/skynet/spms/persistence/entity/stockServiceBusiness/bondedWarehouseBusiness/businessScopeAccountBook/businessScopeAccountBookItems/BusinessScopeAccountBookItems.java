package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.businessScopeAccountBookItems;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author wangyx
 * @version 1.1
 * @created 2011-5-11
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_BUSINESS_SCOPE_ITEMS")
public class BusinessScopeAccountBookItems extends BaseEntity {
	
	
	/**
	 * 删除标志
	 */
	private Boolean delFlg;

	/**
	 * 项号 
	 */
	private String itemNumber;

	/**
	 * 中文名称 
	 */
	private String chineseName;

	/**
	 * 描述 
	 */
	private String describe;

	/**
	 * 英文名称 
	 */
	private String englishName;

	/**
	 * 商品H编码
	 */
	private String hsCode;
	
	/**
	 * 备案计量单位 
	 */
	private String registerUnitMeasureCode;
	
	/**
	 * 法定计量单位 
	 */
	private int legalUnitMeasureCode;

	/**
	 * 用途 
	 */
	private String purpose;

	/**
	 * 备案日期 
	 */
	private Date registerDate;

	/**
	 * 库房编号 
	 */
	private String stockRoomNumber;

	/**
	 * 工作原理 
	 */
	private String workPrinciple;
	
	/**
	 * 备注 
	 */
	private String remark;
	
	/**
	 * 经营范围电子帐册ID 
	 */
	private String businessScopeAccountBookID;
	
	public UnitOfMeasureCode m_UnitOfMeasureCode;
	
	public BusinessScopeAccountBookItems(){

	}

	@Column(name = "DEL_FLG")
	public Boolean getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}

	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "CHINESE_NAME")
	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name = "DESCRIBE")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Column(name = "ENGLISH_NAME")
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Column(name = "HS_CODE")
	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	@Column(name = "REGISTER_UNIT_MEASURE_CODE")
	public String getRegisterUnitMeasureCode() {
		return registerUnitMeasureCode;
	}

	public void setRegisterUnitMeasureCode(String registerUnitMeasureCode) {
		this.registerUnitMeasureCode = registerUnitMeasureCode;
	}

	@Column(name = "LEGAL_UNIT_MEASURE_CODE")
	public int getLegalUnitMeasureCode() {
		return legalUnitMeasureCode;
	}

	public void setLegalUnitMeasureCode(int legalUnitMeasureCode) {
		this.legalUnitMeasureCode = legalUnitMeasureCode;
	}

	@Column(name = "PURPOSE")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name = "REGISTER_DATE")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "STOCK_ROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Column(name = "WORK_PRINCIPLE")
	public String getWorkPrinciple() {
		return workPrinciple;
	}

	public void setWorkPrinciple(String workPrinciple) {
		this.workPrinciple = workPrinciple;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "BUSINESS_SCOPE_ID")
	public String getBusinessScopeAccountBookID() {
		return businessScopeAccountBookID;
	}

	public void setBusinessScopeAccountBookID(String businessScopeAccountBookID) {
		this.businessScopeAccountBookID = businessScopeAccountBookID;
	}

	public void finalize() throws Throwable {

	}

}