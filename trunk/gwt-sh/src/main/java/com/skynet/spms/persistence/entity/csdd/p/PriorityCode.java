package com.skynet.spms.persistence.entity.csdd.p;

/**
 * A precedence rating (assigned by the customer) designating the urgency for
 * processing and responding to the subject quotation or purchase order.
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:57
 */
public enum PriorityCode {
	/**
	 * AircraftOnGround
	 */
	AOG,
	/**
	 * UrgentStockReplenishment
	 */
	USR,
	/**
	 * Work Stoppage
	 */
	WSP
}