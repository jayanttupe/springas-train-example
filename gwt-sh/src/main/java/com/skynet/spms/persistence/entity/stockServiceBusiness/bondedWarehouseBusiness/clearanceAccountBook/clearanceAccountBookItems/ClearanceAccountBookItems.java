package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookItems;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:13
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CLEARANCE_ACC_BOOK_ITEMS")
public class ClearanceAccountBookItems extends BaseEntity{
	
	/**
	 * 项号 
	 */	
	private String itemNumber;
	/**
	 * 电子账册项号
	 */
	private String accountBookItemsNumber;
	/**
	 * 名称
	 */
	private String chineseName;
	/**
	 * 电子账册号
	 */
	private String clearanceAccountBookNumber;
	/**
	 * 描述
	 */
	private String describe;
	/**
	 * 英文名称
	 */
	private String englishName;
	/**
	 * 商品编码
	 */
	private String hsCode;
	/**
	 * 是否延期
	 */
	private boolean isDefer;
	/**
	 * 是否备案
	 */
	private boolean isRegister;
	/**
	 * 用途
	 */
	private String purpose;
	/**
	 * 备案日期
	 */
	private Date registerDate;
	/**
	 * 备案到期日期
	 */
	private Date registerExpireDate;
	/**
	 * 备案计量单位
	 */
	private String registerUnitMeasureCode;
	/**
	 * 工作原理
	 */
	private String workPrinciple;
	/**
	 * 电子帐册ID
	 */
	private String clearanceAccountBookID;
	private String m_BondedWarehousePartsInventory;
	private String m_OutStockRecord;
	private String m_CheckAndAcceptSheet;
	private String m_InStockRecord;
	
	/**
	 * 备注 
	 */
	private String remark;
	
	public ClearanceAccountBookItems(){

	}

	@Column(name = "ACCOUN_TBOOK_ITEMS_NUM")
	public String getAccountBookItemsNumber() {
		return accountBookItemsNumber;
	}

	public void setAccountBookItemsNumber(String accountBookItemsNumber) {
		this.accountBookItemsNumber = accountBookItemsNumber;
	}

	@Column(name = "CHINESE_NAME")
	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	@Column(name = "CLEARANCE_ACC_BOOK_NUM")
	public String getClearanceAccountBookNumber() {
		return clearanceAccountBookNumber;
	}

	public void setClearanceAccountBookNumber(String clearanceAccountBookNumber) {
		this.clearanceAccountBookNumber = clearanceAccountBookNumber;
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

	@Column(name = "IS_DEFER") 
	public boolean isDefer() {
		return isDefer;
	}

	public void setDefer(boolean isDefer) {
		this.isDefer = isDefer;
	}

	@Column(name = "IS_REGISTER") 
	public boolean isRegister() {
		return isRegister;
	}

	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
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

	@Column(name = "REGISTER_EXPIRE_DATE") 
	public Date getRegisterExpireDate() {
		return registerExpireDate;
	}

	public void setRegisterExpireDate(Date registerExpireDate) {
		this.registerExpireDate = registerExpireDate;
	}

	@Column(name = "REGISTER_UNIT_MEA_CODE") 
	public String getRegisterUnitMeasureCode() {
		return registerUnitMeasureCode;
	}

	public void setRegisterUnitMeasureCode(String registerUnitMeasureCode) {
		this.registerUnitMeasureCode = registerUnitMeasureCode;
	}

	@Column(name = "WORK_PRINCIPLE") 
	public String getWorkPrinciple() {
		return workPrinciple;
	}

	public void setWorkPrinciple(String workPrinciple) {
		this.workPrinciple = workPrinciple;
	}

	@Column(name = "CLEARANCE_ACCOUNT_BOOK_ID")
	public String getClearanceAccountBookID() {
		return clearanceAccountBookID;
	}

	public void setClearanceAccountBookID(String clearanceAccountBookID) {
		this.clearanceAccountBookID = clearanceAccountBookID;
	}

	@Column(name = "M_BONDED_WAR_PARTS_INVENTORY") 
	public String getM_BondedWarehousePartsInventory() {
		return m_BondedWarehousePartsInventory;
	}

	public void setM_BondedWarehousePartsInventory(
			String m_BondedWarehousePartsInventory) {
		this.m_BondedWarehousePartsInventory = m_BondedWarehousePartsInventory;
	}

	@Column(name = "M_OUT_STOCK_RECORD") 
	public String getM_OutStockRecord() {
		return m_OutStockRecord;
	}

	public void setM_OutStockRecord(String m_OutStockRecord) {
		this.m_OutStockRecord = m_OutStockRecord;
	}

	@Column(name = "M_CHECK_AND_ACC_SHEET") 
	public String getM_CheckAndAcceptSheet() {
		return m_CheckAndAcceptSheet;
	}

	public void setM_CheckAndAcceptSheet(String m_CheckAndAcceptSheet) {
		this.m_CheckAndAcceptSheet = m_CheckAndAcceptSheet;
	}

	@Column(name = "M_INSTOCK_RECORD") 
	public String getM_InStockRecord() {
		return m_InStockRecord;
	}

	public void setM_InStockRecord(String m_InStockRecord) {
		this.m_InStockRecord = m_InStockRecord;
	}

	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void finalize() throws Throwable {

	}
}