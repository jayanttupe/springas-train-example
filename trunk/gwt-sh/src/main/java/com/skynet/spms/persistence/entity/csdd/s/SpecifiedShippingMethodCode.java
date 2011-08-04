package com.skynet.spms.persistence.entity.csdd.s;

/**
 * Specifies the method to be used for transporting a given shipment from the
 * supplier's  facility plus the method for paying the transportation costs.
 * 
 * 2D Second Business Day
 * AF Air Freight
 * AP Air Parcel Post
 * AX Air Express
 * CF Contract Freight ‐ in accordance with agreed airline freight contract
 * MF Motor Freight
 * ND Next Business Day
 * OT Our Truck
 * PP Parcel Post
 * PU Pick Up
 * SA Standard Air
 * SP Special Instructions
 * SS Standard Surface
 * UP United Parcel
 * YR Your Routing
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 15:51:38
 */
public enum SpecifiedShippingMethodCode {
	SD,AF ,AP ,AX ,CF ,MF ,ND ,OT ,PP ,PU ,SA ,SP ,SS ,UP ,YR 
}