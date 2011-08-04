package com.skynet.spms.persistence.entity.csdd.e;

/**
 * ESS 标识是否影响飞机签派
 * Indicates whether the subject part is essential for dispatch of the aircraft.
 * 
 * 0  Not applicable ‐ used when Maintenance/Overhaul/Repair Code is
 * Overhaul/Repair
 * (Code 3) or Insurance (Code 4).
 * 1  Flight cannot be dispatched for commercial services with the part
 * inoperative.
 * 2  Flight may be dispatched for commercial services with the part inoperative
 * dependent
 * upon permissible conditions detailed in the Minimum Equipment List (MEL).
 * 3  Flight can always be dispatched for commercial services with the part
 * inoperative.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:00:41
 */
public enum EssentialityCode {
	NONE,NOGO,GOIF,GO

}