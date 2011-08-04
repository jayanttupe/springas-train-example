package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookItems.ClearanceAccountBookItems;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:13
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CLEARANCE_ACC_BOOK")
public class ClearanceAccountBook extends BaseEntity{
	
	/**
	 * 入库业务类型
	 */
	private String businessType;
	/**
	 * 通关电子帐册号
	 */
	private String clearanceAccountBookNumber;
	/**
	 * 描述
	 */
	private String describe;
	
	private String m_BaseApprovalRecord;
	private String m_ClearanceAccountBookItems;
	private String m_BusinessScopeAccountBook;
	private String m_CargoSpaceEntity;
	private String m_StockRoomEntity;
	private String m_CAGECode;
	
	/**
	 * 通关电子帐册明细
	 */
	private List<ClearanceAccountBookItems> clearanceAccountBookItemsList;
	
	public ClearanceAccountBook(){

	}

	@Column(name = "BUSINESS_TYPE")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "CLEARANCE_ACC_BOOK_NUM") 
	public String getClearanceAccountBookNumber() {
		return clearanceAccountBookNumber;
	}

	public void setClearanceAccountBookNumber(String clearanceAccountBookNumber) {
		this.clearanceAccountBookNumber = clearanceAccountBookNumber;
	}

	@Column(name = "M_DESCRIBE")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Column(name = "M_BASE_APPROVAL_RECORD")
	public String getM_BaseApprovalRecord() {
		return m_BaseApprovalRecord;
	}

	public void setM_BaseApprovalRecord(String m_BaseApprovalRecord) {
		this.m_BaseApprovalRecord = m_BaseApprovalRecord;
	}

	@Column(name = "M_CLE_ACC_BOOK_ITEMS")
	public String getM_ClearanceAccountBookItems() {
		return m_ClearanceAccountBookItems;
	}

	public void setM_ClearanceAccountBookItems(String m_ClearanceAccountBookItems) {
		this.m_ClearanceAccountBookItems = m_ClearanceAccountBookItems;
	}

	@Column(name = "M_BUSINESS_SCOPE_ACC_BOOK")
	public String getM_BusinessScopeAccountBook() {
		return m_BusinessScopeAccountBook;
	}

	public void setM_BusinessScopeAccountBook(String m_BusinessScopeAccountBook) {
		this.m_BusinessScopeAccountBook = m_BusinessScopeAccountBook;
	}

	@Column(name = "M_CARGO_SPACE_ENTITY")
	public String getM_CargoSpaceEntity() {
		return m_CargoSpaceEntity;
	}

	public void setM_CargoSpaceEntity(String m_CargoSpaceEntity) {
		this.m_CargoSpaceEntity = m_CargoSpaceEntity;
	}

	@Column(name = "M_STOCK_ROOM_ENT") 
	public String getM_StockRoomEntity() {
		return m_StockRoomEntity;
	}

	public void setM_StockRoomEntity(String m_StockRoomEntity) {
		this.m_StockRoomEntity = m_StockRoomEntity;
	}

	@Column(name = "M_CAGE_CODE") 
	public String getM_CAGECode() {
		return m_CAGECode;
	}

	public void setM_CAGECode(String m_CAGECode) {
		this.m_CAGECode = m_CAGECode;
	}

	@OneToMany(targetEntity= ClearanceAccountBookItems.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="CLEARANCE_ACCOUNT_BOOK_ID")
	public List<ClearanceAccountBookItems> getClearanceAccountBookItemsList() {
		return clearanceAccountBookItemsList;
	}

	public void setClearanceAccountBookItemsList(List<ClearanceAccountBookItems> clearanceAccountBookItemsList) {
		this.clearanceAccountBookItemsList = clearanceAccountBookItemsList;
	}
	
	public void finalize() throws Throwable {

	}
	
}