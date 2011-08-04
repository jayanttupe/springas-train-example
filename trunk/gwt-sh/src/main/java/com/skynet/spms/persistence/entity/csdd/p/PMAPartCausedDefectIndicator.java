package com.skynet.spms.persistence.entity.csdd.p;

/**
 * 缩写 PDI
 * Specifies that the cause of engine/part failure may be related to a PMA (Part
 * Manufacture Authority) part.
 * 
 * N Defect may not be caused by a PMA part
 * Y Defect may be caused by a PMA part
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 15:46:07
 */
public enum PMAPartCausedDefectIndicator {
	Y,N
}