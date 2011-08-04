package com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation;


/**
 * Flags a part number as Buyer Furnished Equipment/Seller Furnished Equipment
 * (BFE/SFE) in aircraft provisioning data, as determined by the airline customer.
 * 
 * 
 * 长度 1位 A  Y标识 Buyer Furnished Equipment，非Y标识 Seller Furnished Equipment
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:21
 */
/*
 *Update by : Huhf    2011-4-21
 *CHECKED BY: Shanyf  2011-4-21
 *   isBuyerFurnishedEquipment from String to boolean
 *Confirm by: 
 * 
 */

public enum BuyerFurnishedEquipmentIndicator {

	BFE,SFE

}