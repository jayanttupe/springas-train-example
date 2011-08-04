package com.skynet.spms.persistence.entity.csdd.t;

/**
 * 缩写 TCC
 * Specifies whether the Unscheduled Removal Rate (URR), Time/Cycles Between Shop
 * Visits (TBSV/CBSV), Time/Cycles Between Overhaul (TBO/CBO) and various
 * consumption rates are stated in terms of flight hours, operating cycles or
 * landings.
 * 
 * C Operating cycles
 * H Flight hours
 * L Landing cycles
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:13:26
 */
public enum TimeCycleCode {
	C,H,L
}