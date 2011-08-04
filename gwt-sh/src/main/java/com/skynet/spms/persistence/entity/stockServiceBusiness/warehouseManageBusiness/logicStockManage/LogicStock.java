package com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

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
@Table(name = "SPMS_LOGIC_STOCK")
public class LogicStock extends BaseEntity {
	
	/**
	 * 库房ID
	 */
	private String stockRoomID;
	
	/**
	 * 逻辑库代码
	 */
	private String logicStockCode;

	/**
	 * 逻辑库名称
	 */
	private String logicStockName;

	/**
	 * 备注
	 */
	private String memo;
	
	public LogicStock() {

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

	@Column(name = "LOGIC_STOCK_CODE")
	public String getLogicStockCode() {
		return logicStockCode;
	}

	public void setLogicStockCode(String logicStockCode) {
		this.logicStockCode = logicStockCode;
	}

	@Column(name = "LOGIC_STOCK_NAME")
	public String getLogicStockName() {
		return logicStockName;
	}

	public void setLogicStockName(String logicStockName) {
		this.logicStockName = logicStockName;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}