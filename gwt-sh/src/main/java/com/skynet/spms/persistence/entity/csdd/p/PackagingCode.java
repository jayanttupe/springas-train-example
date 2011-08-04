package com.skynet.spms.persistence.entity.csdd.p;

/**
 * Specifies the type of container,  packaging requirements or material handling
 * devices to be used when shipping subject parts per specified Customer Order
 * Number.
 * 
 * 0 No specific packaging requested
 * 1 Use ATA Spec 300 Category 1 container.
 * 2 Use ATA Spec 300 Category 2 container.
 * 3 Packaged and marked as described in ATA Spec 300, Chapter 3.
 * 4 Each Kit to be packaged and identified as described in ATA Spec 300, Chapter
 * 4.
 * 5 Material handling devices required as described in ATA Spec 300, Chapter 5.
 * 6 Hazardous Material requires specific packaging and approved markings as
 * described in
 * ATA Spec 300, Chapter 6.
 * 7 Special Reference Packaging as described in ATA Spec 300, Para. 1‐4.
 * Includes
 * electrostatic devices, photosynthetic processes  and other Chapter  1,
 * requirements
 * where protection is required from abnormal deterioration or damage in storage
 * or by
 * handling.
 * 8 Part not shipped in ATA Spec 300 Category 1 container as indicated in the
 * Purchase
 * Order for repair/overhaul services.
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:53
 */
public enum PackagingCode {
  A,B,C,D,E,F,G,H,I

}