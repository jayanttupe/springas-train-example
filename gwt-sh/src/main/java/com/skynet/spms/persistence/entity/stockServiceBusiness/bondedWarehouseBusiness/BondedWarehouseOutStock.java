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
 * 保税库出库记录
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_BONDED_WAREHOUSE_OUTSTOCK")
public class BondedWarehouseOutStock extends BaseEntity{

	/**
	 * 保税库出库编号
	 */
	private String bondedOutStockNumber;
	/**
	 * 出仓日期
	 */
	private Date outStockDate;
	/**
	 * 合同编号
	 */
	private String contratNumber;
	/**
	 * 发送给
	 */
	private String sendTo;
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
	 * 仓位号
	 */
	private String position;
	/**
	 * 是否海关监管
	 */
	private boolean customs;

	@Column ( name = "IS_CUSTOMS" )
	public boolean isCustoms() {
		return customs;
	}
	public void setCustoms(boolean customs) {
		this.customs = customs;
	}
    @Column ( name = "BONDED_OUTSTOCK_NUMBER" )
	public String getBondedOutStockNumber() {
		return bondedOutStockNumber;
	}
	public void setBondedOutStockNumber(String bondedOutStockNumber) {
		this.bondedOutStockNumber = bondedOutStockNumber;
	}
	@Column ( name = "OUTSTOCK_DATE" )
	public Date getOutStockDate() {
		return outStockDate;
	}
	public void setOutStockDate(Date outStockDate) {
		this.outStockDate = outStockDate;
	}
	@Column ( name = "CONTRAT_NUMBER" )
	public String getContratNumber() {
		return contratNumber;
	}
	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}
	@Column ( name = "SEND_TO" )
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
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
	@Column ( name = "UNTI_OF_MEASURE" )
	public UnitOfMeasureCode getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(UnitOfMeasureCode unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	@Column ( name = "POSITON" )
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
