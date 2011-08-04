package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.shelveTask;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem.BaseTaskItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_SHELVE_TASK_ITEM")
public class ShelveTaskItem extends BaseTaskItem {

	/**
	 * 上架任务ID
	 */
	public String shelveTaskID;

	/**
	 * 入库记录ID
	 */
	public String inStockRecordID;

	public ShelveTaskItem(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "SHELVE_TASK_ID")
	public String getShelveTaskID() {
		return shelveTaskID;
	}

	public void setShelveTaskID(String shelveTaskID) {
		this.shelveTaskID = shelveTaskID;
	}

	@Column(name = "IN_STOCK_RECORD_ID")
	public String getInStockRecordID() {
		return inStockRecordID;
	}

	public void setInStockRecordID(String inStockRecordID) {
		this.inStockRecordID = inStockRecordID;
	}
}