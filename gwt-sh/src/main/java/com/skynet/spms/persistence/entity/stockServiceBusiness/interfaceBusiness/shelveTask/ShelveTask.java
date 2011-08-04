package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.shelveTask;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_SHELVE_TASK")
public class ShelveTask extends BaseTask {

	/**
	 * 入库状态(1:未入库/2:已入库)
	 */
	private String inStockStatus = "1";

	/**
	 * 指令明细数据
	 */
	private List<ShelveTaskItem> ShelveTaskItemList;

	@Column(name = "IN_STOCK_STATUS")
	public String getInStockStatus() {
		return inStockStatus;
	}

	public void setInStockStatus(String inStockStatus) {
		this.inStockStatus = inStockStatus;
	}

	@OneToMany(targetEntity= ShelveTaskItem.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="SHELVE_TASK_ID")
	public List<ShelveTaskItem> getShelveTaskItemList() {
		return ShelveTaskItemList;
	}

	public void setShelveTaskItemList(List<ShelveTaskItem> shelveTaskItemList) {
		ShelveTaskItemList = shelveTaskItemList;
	}

	public ShelveTask(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}