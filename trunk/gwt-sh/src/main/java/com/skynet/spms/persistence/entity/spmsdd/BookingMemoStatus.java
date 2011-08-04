package com.skynet.spms.persistence.entity.spmsdd;

/**
 * 订舱单状态主要有委托书编写的状态来确定
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:37
 */
public enum BookingMemoStatus {
	confirmation,
	reAllocated,
	cancle,
	finish
}