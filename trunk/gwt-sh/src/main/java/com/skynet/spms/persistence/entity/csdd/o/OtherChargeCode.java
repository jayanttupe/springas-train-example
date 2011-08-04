package com.skynet.spms.persistence.entity.csdd.o;

/**
 * 缩写 OCC
 * 
 * Identifies various special charges, including refundable types, which are
 * assessed on orders for given parts.  Other Charges exclude the normal local,
 * county and state sales taxes and the Value Added Tax (VAT).
 * 
 * 01 Packing Charge
 * 02 Set‐up Charge, Cutting Charge
 * 03 Die Charge
 * 04 Documentation Charge
 * 05 Returnable Container Charge (Refundable)
 * 06 Transportation Charge
 * 07 Miscellaneous Charge
 * 08 Federal Excise Tax
 * 09 Fees/Markup
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 15:54:25
 */
public enum OtherChargeCode {
  A,B,C,D,E,F,G,H,I
}