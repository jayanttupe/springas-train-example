package com.skynet.spms.persistence.entity.spmsdd;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:12
 */
public enum OutStockStatus {
	// 已配料
	Picking,
	// 已下达
	OrderPick,
	// 已拣货
	Pick,
	// 装箱中
	Packing,
	// 已装箱
	Pack,
	// 合格放行
	CheckOut,
	// 已出库
	OutStock
}