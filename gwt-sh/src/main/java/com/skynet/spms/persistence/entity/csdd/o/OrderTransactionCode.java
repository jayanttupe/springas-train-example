package com.skynet.spms.persistence.entity.csdd.o;

/**
 * Specifies the type, category and condition of a customer purchase order
 * (S1BOOKED AND R1CPOXMT transactions).
 * 指定客户订单的类型、种类以及条件。
 * 
 * ~~1 Routine Action
 * ~~2 Acceptance of  Quotation
 * ~~3 Alteration
 * ~~6 Cancel/Decrease Quantity
 * ~~8 Increase Quantity
 * ~~9 Request Partial Ship  Quantity of an open order.
 * ~2~ Initial Provisioning Item
 * ~3~ New Stock Item
 * ~4~ Replenishment Stock Item
 * ~5~ Allocation Item
 * ~6~ Special Requirement Item
 * ~7~ Repair Item
 * ~8~ Warranty Item
 * ~9~ Special Business Agreement
 * 3~~ Order ‐ Normal
 * 4~~ Order ‐ Expedite
 * L~~ Order ‐ Normal, Confirming
 * M~~ Order ‐ Expedite, Confirming
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:52
 */
public enum OrderTransactionCode {

}