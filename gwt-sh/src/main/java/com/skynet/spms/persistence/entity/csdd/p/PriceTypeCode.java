package com.skynet.spms.persistence.entity.csdd.p;

/**
 * 缩写 PTC
 * Advises either the type of Unit Price, its availability by quotation or its
 * applicability to an
 * Optional Part Number.
 * 
 * 0 Price not required per specification.
 * 1 Firm price.
 * 2 Budget or estimated price.
 * 3 Price applies to the manufacturer's assigned Optional Part Number noted in
 * the
 * Explanation Code 39 (Optional Part Number) or TEI‐OPT text and only when
 * procured
 * from the End Item Manufacturer. NOTE:  Does not apply to Repair Administration.
 * 
 * 4 Price available by quotation only.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 15:46:07
 */
public enum PriceTypeCode {
	A,B,C,D,E
}