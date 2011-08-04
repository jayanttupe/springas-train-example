package com.skynet.spms.client.gui.customerplatform.common;

import com.skynet.spms.client.gui.customerplatform.common.ModuleKey;

/**
 * @category 客户服务模块名及数据源维护常量
 * 
 * @author tqc
 * 
 */
public interface ModuleKey {
	/**
	 * 客户询价信息
	 */
	public static final String C_CONTACT = "customerplat.queryPrice.contact";

	/**
	 * 客户报价反馈
	 */
	public static final String C_CONTACTDETAIL = "customerplat.queryPrice.contactDetail";

	/**
	 * 下订单
	 */
	public static final String C_ORDERCREATE = "customerplat.takeProcurementOrder.orderCreate";
	
	/**
	 * 客户订单跟踪
	 */
	public static final String C_ORDERTRACK = "customerplat.takeProcurementOrder.orderTrack";

	/**
	 * 单件号查询
	 */
	public static final String C_SINGLE_SPARE_QUERY = "customerplat.singleSpareQuery";

	/**
	 * 账户信息
	 */
	public static final String C_ACCOUNT_INFO = "customerplat.customerData.accountInfo";

	/**
	 * 客户资料
	 */
	public static final String C_CUSTOMER_INFO = "customerplat.customerData.customerInfo";

	/***
	 * 交换
	 */
	public static final String C_EXCHANGE_REQUISITION = "customerplat.exchangeRequisition.exchangeRequisition";
	/** 交换数据源 **/
	public static final String C_EXCHANGE_REQUISITION_DS = "exchangeRequisition_dataSource";
	/**
	 * 回购
	 */
	public static final String C_BUYBACKSHEET = "customerplat.buybackSheetService";

	/**
	 * 担保索赔
	 */
	public static final String C_GUARANTEEFORM = "customerplat.guaranteeForm";
	
	/** 客户送修申请单 **/
	public static final String CUSTOMERREPAIRSHEET = "customerplat.repairSheet";
	
	
	
	
}
