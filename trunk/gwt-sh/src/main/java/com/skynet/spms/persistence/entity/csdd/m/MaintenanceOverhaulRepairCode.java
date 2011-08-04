package com.skynet.spms.persistence.entity.csdd.m;

/**
 * 缩写 MOR
 * Indicates whether the part number at the subject Catalog Sequence Number is
 * normally replaced during line maintenance and/or unit overhaul/repair.
 * 
 * 0 Not normally replaced.
 * 1 Line Maintenance (on aircraft).  Routine check, inspection and malfunction
 * rectification
 * performed enroute and at base stations during transit turnaround or night stop.
 * 
 * 3 Overhaul/Repair.  Part required for overhaul or repair of an engine, rotable
 * or repairabl
 * unit.
 * 4 Insurance.  An item which, for the first two or three years of aircraft
 * operation, should
 * not require replacement because of wear or deterioration, but might be replaced
 * 
 * because of damage to the aircraft or because of other unpredictable
 * requirements.  If
 * the item would not be available within 24 hours, it would cause delays in
 * aircraft
 * schedules and loss of revenue.
 * 6 Both Line Maintenance and Overhaul/Repair.
 * 
 * 长度 1位 N
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:09:55
 */
public enum MaintenanceOverhaulRepairCode {
	NR,LM,OR,INS,BLM
}