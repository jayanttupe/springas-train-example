package com.skynet.spms.persistence.entity.spmsdd;


/**
 * 贸易方式可参考海关使用手册2010年版第43页。监管方式代码表，其中贸易方式包括监管方式代码，监管方式简称，监管方式全称。其中最常用的是一般贸易，通常是指我国境
 * 内有进出口经营权的企业单边进口或者单边出口的贸易。 例如： 监管代码0110，简称一般贸易。详细数据字典信息可参考海关使用手册进行维护。
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:11
 */
public enum TradeMethods{
	/**
	 * 国内贸易 
	 */
	domesticTrade,
	/**
	 * 一般贸易 
	 */
	generalTrade,
	/**
	 * 保税仓库货物 
	 */
	bondedGoods ,
	/**
	 * 寄售代销 
	 */
	consignmentUnderwrites,
	/**
	 * 租赁贸易 
	 */
	rentingTrade,
	/**
	 * 补偿贸易 
	 */
	counterTrade,
	/**
	 * 修理物品 
	 */
	repairItem,
	/**
	 * 样品 
	 */
	sample
}