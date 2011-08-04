package com.skynet.spms.persistence.entity.stockServiceBusiness.containerManage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.PackagingMaterial;
import com.skynet.spms.persistence.entity.spmsdd.StockContainerType;

/**
 * @author wangyx
 * @version 1.1
 * @created 2011-5-9
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CONTAINER")
public class Container extends BaseEntity {
	/**
	 * 容器编号 
	 */
	private String containerNumber;
	/**
	 * 所属库房 ID
	 */
	private String stockRoomID;
	/**
	 * 所属库房编号
	 */
	private String stockRoomNumber;
	/**
	 * 所属库房名称
	 */
	private String stockRoomName;
	/**
	 * 容器类型 
	 */
	private StockContainerType containerType;
	/**
	 * 材质 
	 */
	private PackagingMaterial containerMaterial;
	
	/**
	 * RFID标签唯一编号
	 */
	private String rFIDtagIdentifierCode;
	
	/**
	 * 备注 
	 */
	private String remark;
	
	@Column(name = "CONTAINER_NUMBER")
	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}
	@Column(name = "STOCK_ROOM_ID")
	public String getStockRoomID() {
		return stockRoomID;
	}
	public void setStockRoomID(String stockRoomID) {
		this.stockRoomID = stockRoomID;
	}
	@Transient
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}
	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}
	@Transient
	public String getStockRoomName() {
		return stockRoomName;
	}
	public void setStockRoomName(String stockRoomName) {
		this.stockRoomName = stockRoomName;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "CONTAINER_TYPE")
	public StockContainerType getContainerType() {
		return containerType;
	}
	public void setContainerType(StockContainerType containerType) {
		this.containerType = containerType;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "CONTAINER_MATERIAL")
	public PackagingMaterial getContainerMaterial() {
		return containerMaterial;
	}
	public void setContainerMaterial(PackagingMaterial containerMaterial) {
		this.containerMaterial = containerMaterial;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "RFIDTAG_IDENTIFIER_CODE")
	public String getRFIDtagIdentifierCode() {
		return rFIDtagIdentifierCode;
	}
	public void setRFIDtagIdentifierCode(String rFIDtagIdentifierCode) {
		this.rFIDtagIdentifierCode = rFIDtagIdentifierCode;
	}
}
