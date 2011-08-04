package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.boxingTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:12
 */
public class BoxingTask extends BaseTask {

	public BoxingTaskActionRecord m_BoxingTaskActionRecord;
	public BoxingTaskItem m_BoxingTaskItem;

	public BoxingTask(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}