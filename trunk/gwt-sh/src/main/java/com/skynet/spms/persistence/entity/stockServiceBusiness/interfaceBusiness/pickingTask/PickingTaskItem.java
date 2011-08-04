package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.pickingTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem.BaseTaskItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:16
 */
public class PickingTaskItem extends BaseTaskItem {

	public PickingTaskItemActionRecord m_PickingTaskItemActionRecord;

	public PickingTaskItem(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}