package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.taskActionRecord;
import java.util.Date;

import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.SPMS.TaskSource;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.SPMS.TaskState;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:19
 */
public class TaskActionRecord {

	private String actionBy;
	private Date actionDate;
	private String actionDevice;
	private String itemStateNo;
	private String taskNo;
	private String taskSource;
	public TaskState m_TaskState;
	public TaskSource m_TaskSource;

	public TaskActionRecord(){

	}

	public void finalize() throws Throwable {

	}

}