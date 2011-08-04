package com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.timeControlPartBusiness;
import java.util.Date;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.csdd.P.PeriodicCheckCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.csdd.P.RenewalProcedureCode;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:20
 */
public class TimeControlLimitRecord extends BaseEntity {

	private String partNumber;
	/**
	 * 部件序号
	 */
	private String partSerialNumber;
	/**
	 * 处理日期
	 */
	private Date processDate;
	/**
	 * 工作者
	 */
	private String processor;
	/**
	 * 库房编号
	 */
	private String stockRoomNumber;
	/**
	 * 时控件到限记录编号
	 */
	private String timeControlLimitRecordNumber;
	/**
	 * 时限控制件任务完成日期
	 */
	private Date timeControlTaskCompleteDate;
	/**
	 * 时限控制件任务完成说明
	 */
	private String timeControlTaskCompleteExplain;
	/**
	 * 时限控制件任务周期
	 */
	private int timeControlTaskCycle;
	/**
	 * 时限控制件任务到期日期
	 */
	private Date timeControlTaskEndDate;
	/**
	 * 时限控制件任务控制开始日期
	 */
	private Date timeControlTaskStartDate;
	public RenewalProcedureCode m_RenewalProcedureCode;
	public PeriodicCheckCode m_PeriodicCheckCode;

	public TimeControlLimitRecord(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}