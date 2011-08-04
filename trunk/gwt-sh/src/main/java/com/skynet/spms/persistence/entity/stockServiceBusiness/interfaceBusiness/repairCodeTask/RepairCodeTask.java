package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.repairCodeTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */
public class RepairCodeTask extends BaseTask {

	public RepairCodeTaskItem m_RepairCodeTaskItem;
	public RepairCodeTaskActionRecord m_RepairCodeTaskActionRecord;

	public RepairCodeTask(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}