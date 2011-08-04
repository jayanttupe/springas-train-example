package com.skynet.spms.persistence.entity.csdd.w;

/**
 * Identifies whether Warranty Time/Cycle Count represents days, cycles or hours,
 * and  whether it represents time or cycles since receipt, purchase or
 * installation of the part.
 * 
 * CI Operating Cycles since installation of the part
 * CP Operating Cycles since part was purchased
 * CR Operating Cycles since part was received
 * DI Calendar Days since installation of the part
 * DP Calendar Days since part was purchased
 * DR Calendar Days since part was received
 * HI Operating Hours since installation of the part
 * HP Operating Hours since part was purchased
 * HR Operating Hours since part was received
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:13
 */
public enum WarrantyTimeCycleReferenceCode {
	CI,
	CP,
	CR
}