package com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.InStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;

/**
 * @author bh
 * @version 1.1
 * @created 2011-3-26
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_IN_STOCK_RECORD")
public class InStockRecord extends BaseEntity {
	
	/**
	 * 单位 
	 */
	private UnitOfMeasureCode unitOfMeasure; 
	 
	/**
	 * 合同编号 
	 */
	private String contratNumber;
	
	/**
	 * 检验单号
	 */
	private String checkAndAcceptSheetNumber;
	
	/**
	 * 收料单号
	 */
	private String receivingSheetNumber;

	/**
	 * 检验日期
	 */
	private Date inspectionDate;

	/**
	 * 检验员
	 */
	private String inspector;
	
	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;
	
	/**
	 * 件描述
	 */
	private String partName;

	/**
	 * 数量
	 */
	private int quantity;

	/**
	 * 供货单位
	 */
	private String supplyUnit;

	/**
	 * 下达发卡状态
	 */
	private SendStatus sendCardStatus;

	/**
	 * 下达上架状态
	 */
	private SendStatus shelvingStatus;

	/**
	 * 入库状态
	 */
	private InStockStatus inStockStatus;

	/**
	 * 是否寿命件
	 */
	private String isLifePart;

	/**
	 * 寿命期
	 */
	private String usefulLifePeriod;
	
	/**
	 * 寿命时限
	 */
	private Date limitDate;
	
	/**
	 * 是否时控件
	 */
	private String isTimePart;

	/**
	 * 时控件寿命期
	 */
	private String usefulTimePeriod;
	
	/**
	 * 时控件寿寿命时限
	 */
	private Date lifeTimeDate;

	/**
	 * 备件状况
	 */
	private String partStatusCode;

	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 库房名称
	 */
	private String stockRoomChineseName;

	/**
	 * 推荐货位
	 */
	private String recCargoSpaceNum;

	/**
	 * 设置货位
	 */
	private String cargoSpaceNumber;
	
	/**
	 * 入库日期 
	 */
	private Date inStockDate;
	
	/**
	 * 通关电子帐册项号
	 */
	private String accountBookItemsNumber;
	
	/**
	 * 通关电子帐册
	 */
	private String clearanceAccountBookNumber;
	
	/**
	 * 商品h编码
	 */
	private String hsCode;
	
	/**
	 * 入库记录号
	 */
	private String inStockRecordNumber;
	
	/**
	 * 发票号
	 */
	private String invoiceNumber;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 库存结存数量
	 */
	private String stockInventoryQuantity;
	
	/**
	 * 备件接收业务类型 
	 */
	private String businessType;
	
	/**
	 * 政府企业代码 
	 */
	private String cAGECode;
	
	/**
	 * 航材类型代码 
	 */
	private String sparePartClassCode;
	
	public InStockRecord() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "STOCKROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Column(name = "STOCKROOM_CHINESE_NAME")
    public String getStockRoomChineseName() {
		return stockRoomChineseName;
	}

	public void setStockRoomChineseName(String stockRoomChineseName) {
		this.stockRoomChineseName = stockRoomChineseName;
	}

	@Column(name = "REC_CARGO_SPACE_NUM")
	public String getRecCargoSpaceNum() {
		return recCargoSpaceNum;
	}

	public void setRecCargoSpaceNum(String recCargoSpaceNum) {
		this.recCargoSpaceNum = recCargoSpaceNum;
	}
    @Column(name = "CARGOSPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "CHECK_AND_ACCEPT_SHEET_NUMBER")
	public String getCheckAndAcceptSheetNumber() {
		return checkAndAcceptSheetNumber;
	}

	public void setCheckAndAcceptSheetNumber(String checkAndAcceptSheetNumber) {
		this.checkAndAcceptSheetNumber = checkAndAcceptSheetNumber;
	}

	@Column(name = "RECEIVING_SHEET_NUMBER")
	public String getReceivingSheetNumber() {
		return receivingSheetNumber;
	}

	public void setReceivingSheetNumber(String receivingSheetNumber) {
		this.receivingSheetNumber = receivingSheetNumber;
	}

	@Column(name = "INSPECTION_DATE")	
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	@Column(name = "INSPECTOR")
	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
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

	@Column(name = "SUPPLY_UNIT")
	public String getSupplyUnit() {
		return supplyUnit;
	}

	public void setSupplyUnit(String supplyUnit) {
		this.supplyUnit = supplyUnit;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SEND_CARD_STATUS")
	public SendStatus getSendCardStatus() {
		return sendCardStatus;
	}

	public void setSendCardStatus(SendStatus sendCardStatus) {
		this.sendCardStatus = sendCardStatus;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SHELVING_STATUS")
	public SendStatus getShelvingStatus() {
		return shelvingStatus;
	}

	public void setShelvingStatus(SendStatus shelvingStatus) {
		this.shelvingStatus = shelvingStatus;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "IN_STOCK_STATUS")
	public InStockStatus getInStockStatus() {
		return inStockStatus;
	}

	public void setInStockStatus(InStockStatus inStockStatus) {
		this.inStockStatus = inStockStatus;
	}

	@Column(name = "IS_LIFE_PART")
	public String getIsLifePart() {
		return isLifePart;
	}

	public void setIsLifePart(String isLifePart) {
		this.isLifePart = isLifePart;
	}

	@Column(name = "USEFUL_LIFE_PERIOD")
	public String getUsefulLifePeriod() {
		return usefulLifePeriod;
	}

	public void setUsefulLifePeriod(String usefulLifePeriod) {
		this.usefulLifePeriod = usefulLifePeriod;
	}
	
	@Column(name = "LIMIT_DATE")
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	@Column(name = "IS_TIME_PART")
	public String getIsTimePart() {
		return isTimePart;
	}

	public void setIsTimePart(String isTimePart) {
		this.isTimePart = isTimePart;
	}

	@Column(name = "USEFUL_TIME_PERIOD")
	public String getUsefulTimePeriod() {
		return usefulTimePeriod;
	}

	public void setUsefulTimePeriod(String usefulTimePeriod) {
		this.usefulTimePeriod = usefulTimePeriod;
	}

	@Column(name = "LIFE_TIME_DATE")
	public Date getLifeTimeDate() {
		return lifeTimeDate;
	}

	public void setLifeTimeDate(Date lifeTimeDate) {
		this.lifeTimeDate = lifeTimeDate;
	}

	@Column(name = "PART_STATUS_CODE")
	public String getPartStatusCode() {
		return partStatusCode;
	}

	public void setPartStatusCode(String partStatusCode) {
		this.partStatusCode = partStatusCode;
	}
	
	@Transient
	public UnitOfMeasureCode getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasureCode unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Column(name = "IN_STOCK_DATE")
	public Date getInStockDate() {
		return inStockDate;
	}

	public void setInStockDate(Date inStockDate) {
		this.inStockDate = inStockDate;
	}
	
	@Column(name = "CONTRAT_NUMBER")
	public String getContratNumber() {
		return contratNumber;
	}

	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}
	@Column(name = "ACCOUNT_BOOK_ITEMS_NUMBER")
	public String getAccountBookItemsNumber() {
		return accountBookItemsNumber;
	}

	public void setAccountBookItemsNumber(String accountBookItemsNumber) {
		this.accountBookItemsNumber = accountBookItemsNumber;
	}
	@Column(name = "CLEARANCE_ACCOUNT_BOOK_NUMBER")
	public String getClearanceAccountBookNumber() {
		return clearanceAccountBookNumber;
	}

	public void setClearanceAccountBookNumber(String clearanceAccountBookNumber) {
		this.clearanceAccountBookNumber = clearanceAccountBookNumber;
	}
	@Column(name = "HS_CODE")
	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	@Column(name = "IN_STOCK_RECORD_NUMBER")
	public String getInStockRecordNumber() {
		return inStockRecordNumber;
	}

	public void setInStockRecordNumber(String inStockRecordNumber) {
		this.inStockRecordNumber = inStockRecordNumber;
	}
	@Column(name = "INVOICE_NUMBER")
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "STOCK_INVENTORY_QUANTITY")
	public String getStockInventoryQuantity() {
		return stockInventoryQuantity;
	}

	public void setStockInventoryQuantity(String stockInventoryQuantity) {
		this.stockInventoryQuantity = stockInventoryQuantity;
	}
	@Column(name = "BUSINESS_TYPE")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@Column(name = "CAGE_CODE")
	public String getcAGECode() {
		return cAGECode;
	}

	public void setcAGECode(String cAGECode) {
		this.cAGECode = cAGECode;
	}
	@Column(name = "SPARE_PART_CLASS_CODE")
	public String getSparePartClassCode() {
		return sparePartClassCode;
	}

	public void setSparePartClassCode(String sparePartClassCode) {
		this.sparePartClassCode = sparePartClassCode;
	}

}