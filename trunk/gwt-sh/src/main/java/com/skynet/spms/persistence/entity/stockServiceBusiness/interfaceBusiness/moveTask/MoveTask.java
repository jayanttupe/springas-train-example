package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.moveTask;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:14
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_MOVE_TASK")
public class MoveTask extends BaseTask {

	public String m_MoveTaskActionRecord;
	

	public MoveTask(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}