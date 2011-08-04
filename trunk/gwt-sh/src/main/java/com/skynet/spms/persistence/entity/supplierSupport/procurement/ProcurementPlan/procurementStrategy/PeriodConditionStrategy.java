package com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementStrategy;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.DatetimePeriodType;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:34
 */
public class PeriodConditionStrategy {

	private Date datetime;
	private boolean isRecycle;
	private String year;
	private String quarter;
	/**
	 * 必须是2位月份数字，且月份为1-12之间数字，当选择非重复模式的情况下，可通过设定月份确定周期条件。
	 */
	private String month;
	private String day;
	public DatetimePeriodType m_DatetimePeriodType;

}