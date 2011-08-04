package com.skynet.spms.persistence.entity.spmsdd;

/**
 * Indicates whether the subject part is classified as an expendable spare or a
 * rep
 * spare with or without a supporting Component Maintenance Manual (CMM).
 * 
 * 0 Reference item (part is not considered to be a potential spare part)
 * 1 Expendable spare
 * 2 Repairable spare, having a supporting Component Maintenance Manual  (CMM)
 * 6 Repairable spare, not having a supporting Component Maintenance Manual (CMM)
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:26:39
 */
public enum SparePartClassCode {
	expendable,
	rotable,
	repairable
}