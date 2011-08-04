package com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_STOCK_AREA")
public class StockArea extends BaseEntity {
	
	/**
	 * 库房ID
	 */
	private String stockRoomID;

	/**
	 * 区域代码
	 */
	private String areaCode;

	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * 区域描述
	 */
	private String areaDescription;

	public StockArea() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "STOCK_ROOM_ID")
	public String getStockRoomID() {
		return stockRoomID;
	}

	public void setStockRoomID(String stockRoomID) {
		this.stockRoomID = stockRoomID;
	}

	@Column(name = "AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "AREA_NAME")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "AREA_DESCRIPTION")
	public String getAreaDescription() {
		return areaDescription;
	}

	public void setAreaDescription(String areaDescription) {
		this.areaDescription = areaDescription;
	}
}