package com.skynet.spms.persistence.entity.csdd.s;

/**
 * 缩写 SPC
 * Indicates whether the subject part is classified as an expendable spare or a
 * repairable
 * spare with or without a supporting Component Maintenance Manual (CMM).
 * 
 * 0 Reference item (part is not considered to be a potential spare part)
 * 1 Expendable spare  消耗件
 * 2 Repairable spare, having a supporting Component Maintenance Manual  (CMM)
 * 周转件
 * 6 Repairable spare, not having a supporting Component Maintenance Manual (CMM)
 * 可修件
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 15:51:38
 */
public enum SparePartClassCode {

	//expendable,rotable,repairable,reference
	SPC1,SPC2,SPC6,SPC0
}