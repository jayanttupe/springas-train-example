package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-4-6
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_INSTOCK_APPROVAL_RECORD")
public class InStockApprovalRecord extends BaseEntity {
	/**
	 * 检验单号
	 */
	private String checkAndAcceptSheetNumber;

	/**
	 * 通关电子帐册项号
	 */
	private String accountBookItemsNumber;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;

	/**
	 * 数量
	 */
	private String quantity;

	/**
	 * 入库审批号
	 */
	private String inStockApprovalNumber;

	/**
	 * 入库日期
	 */
	private Date inStockDate;

	/**
	 * 进口报关编号
	 */
	private String customsNumber;

	/**
	 * 入库单号
	 */
	private String inStockRecordNumber;

	public InStockApprovalRecord() {

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

	@Column(name = "ACCOUNT_BOOK_ITEMS_NUMBER")
	public String getAccountBookItemsNumber() {
		return accountBookItemsNumber;
	}

	public void setAccountBookItemsNumber(String accountBookItemsNumber) {
		this.accountBookItemsNumber = accountBookItemsNumber;
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

	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Column(name = "IN_STOCK_APPROVAL_NUMBER")
	public String getInStockApprovalNumber() {
		return inStockApprovalNumber;
	}

	public void setInStockApprovalNumber(String inStockApprovalNumber) {
		this.inStockApprovalNumber = inStockApprovalNumber;
	}

	@Column(name = "IN_STOCK_DATE")
	public Date getInStockDate() {
		return inStockDate;
	}

	public void setInStockDate(Date inStockDate) {
		this.inStockDate = inStockDate;
	}

	@Column(name = "CUSTOMS_NUMBER")
	public String getCustomsNumber() {
		return customsNumber;
	}

	public void setCustomsNumber(String customsNumber) {
		this.customsNumber = customsNumber;
	}

	@Column(name = "IN_STOCK_RECORD_NUMBER")
	public String getInStockRecordNumber() {
		return inStockRecordNumber;
	}

	public void setInStockRecordNumber(String inStockRecordNumber) {
		this.inStockRecordNumber = inStockRecordNumber;
	}

}