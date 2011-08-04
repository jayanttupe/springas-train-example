/**
 * 
 */
package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author Administrator
 * 保税库入库记录
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_BONDED_WAREHOUSE_INSTOCK")
public class BondedWarehouseInStock extends BaseEntity{
    
	/**
	 * 保税库入库编号
	 */
	private String bondedInStockNumber;
	/**
	 * 进仓日期
	 */
	private Date inStockDate;
	/**
	 * 合同编号
	 */
	private String contratNumber;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 件号
	 */
	private String partNumber;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 计量单位
	 */
	private UnitOfMeasureCode unitOfMeasure;
	/**
	 * 进口报关单号
	 */
	private String customsDeclarationNumber;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 仓位号
	 */
	private String position;
	/**
	 * 库房编号
	 */
	private String stockRoomNumber;
	/**
	 * 是否海关监管
	 */
	private boolean customs=false;
	/**
	 * 商品hs编号
	 */
	private String hsCode;
	
	@Column ( name = "HS_CODE" )
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	@Column ( name = "IS_CUSTOMS" )
	public boolean isCustoms() {
		return customs;
	}
	public void setCustoms(boolean customs) {
		this.customs = customs;
	}
	@Column (name="STOCK_ROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}
	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}
	@Column ( name = "POSITON" )
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Column (name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column ( name = "BONDED_INSTOCK_NUMBER" )
	public String getBondedInStockNumber() {
		return bondedInStockNumber;
	}
	public void setBondedInStockNumber(String bondedInStockNumber) {
		this.bondedInStockNumber = bondedInStockNumber;
	}
	@Column ( name = "INSTOCK_DATE" )
	public Date getInStockDate() {
		return inStockDate;
	}
	public void setInStockDate(Date inStockDate) {
		this.inStockDate = inStockDate;
	}
	@Column ( name = "CONTRAT_NUMBER" )
	public String getContratNumber() {
		return contratNumber;
	}
	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}
	@Column ( name = "SOURCE" )
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column ( name = "PART_NUMBER" )
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	@Column ( name = "QUANTITY" )
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Enumerated(EnumType.STRING)
	@Column ( name = "UNIT_OF_MEASURE" )
	public UnitOfMeasureCode getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(UnitOfMeasureCode unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	@Column ( name = "CUSTOMS_DEC_NUMBER" )
	public String getCustomsDeclarationNumber() {
		return customsDeclarationNumber;
	}
	public void setCustomsDeclarationNumber(String customsDeclarationNumber) {
		this.customsDeclarationNumber = customsDeclarationNumber;
	}
	
	
}
