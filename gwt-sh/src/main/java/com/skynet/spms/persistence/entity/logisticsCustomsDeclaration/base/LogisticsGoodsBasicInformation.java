package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base;

/**
 * 物流货物的基本信息，包括名称描述（型号），尺寸（长宽高），箱内的件数信息，是否危险品，特殊要求信息等，作为运输委托书的主要货物基本信息。
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 17:21:07
 */
public class LogisticsGoodsBasicInformation {

	private int billOfLadingContainerCount;
	/**
	 * Specifies the total weight and associated Unit of Measure Code of each of the
	 * containers shipped under the subject Bill of Lading Number.
	 */
	private String billOfLadingWeight;
	/**
	 * Specifies the gross dimensions and weight of the shipment container.
	 * 
	 * 如果在运单中，出现多个包装容器，一般需在此处填写所有包装的尺寸大小，或者直接填写包装的累计体积的大小。物流将依据重量或者体积尺寸来计算相关的费用。
	 */
	private String containerSizeandWeight;
	private String dangerousGoods;
	private String description;
	private String goodsNature;
	private int itemNumber;
	private String pacakgeNumber;
	private String packingListNumber;
	private String specialRequirements;

}