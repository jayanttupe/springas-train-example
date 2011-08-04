package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.moveTask;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem.BaseTaskItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:14
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_MOVE_TASK_ITEM")
public class MoveTaskItem extends BaseTaskItem {

	public String m_MoveTaskItemActionRecord;

	public MoveTaskItem(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}