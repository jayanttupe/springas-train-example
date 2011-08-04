package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.partsPackageTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:16
 */
public class PartsPackageTask extends BaseTask {

	public PartsPackageTaskItem m_PartsPackageTaskItem;
	public PartsPackageTaskActionRecord m_PartsPackageTaskActionRecord;

	public PartsPackageTask(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}