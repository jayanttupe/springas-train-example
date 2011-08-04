package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.pickingTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:16
 */
public class PickingTask extends BaseTask {

	public PickingTaskActionRecord m_PickingTaskActionRecord;
	public PickingTaskItem m_PickingTaskItem;

	public PickingTask(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}