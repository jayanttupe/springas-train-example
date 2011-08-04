package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder;
/**
 * 基础物理装箱信息
 */
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 物流货物的基本信息，包括名称描述（型号），尺寸（长宽高），箱内的件数信息，
 * 是否危险品，特殊要求信息等，作为运输委托书的主要货物基本信息。
 * @author huangdj
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BasePickupDeliveryVanning extends BaseEntity {
	/**
	 * 指令ID
	 */
	private String orderID;

	/**
	 * 箱号
	 */
	private String pacakgeNumber;

	/**
	 * 货物箱数
	 */
	private String billOfLadingContainerCount;

	/**
	 * 货物重量(千克)
	 */
	private String billOfLadingWeight;

	/**
	 * 包装尺寸(体积)
	 */
	private String containerSizeandWeight;

	/**
	 * 危险品
	 */
	private String dangerousGoods;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 货物性质
	 */
	private String goodsNature;

	/**
	 * 装箱单号
	 */
	private String packingListNumber;

	/**
	 * 特殊需求
	 */
	private String specialRequirements;

	public BasePickupDeliveryVanning() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@Column(name = "PACAKGE_NUMBER")
	public String getPacakgeNumber() {
		return pacakgeNumber;
	}

	public void setPacakgeNumber(String pacakgeNumber) {
		this.pacakgeNumber = pacakgeNumber;
	}

	@Column(name = "BILL_OF_LADING_CONTAINER_COUNT")
	public String getBillOfLadingContainerCount() {
		return billOfLadingContainerCount;
	}

	public void setBillOfLadingContainerCount(String billOfLadingContainerCount) {
		this.billOfLadingContainerCount = billOfLadingContainerCount;
	}

	@Column(name = "BILL_OF_LADING_WEIGHT")
	public String getBillOfLadingWeight() {
		return billOfLadingWeight;
	}

	public void setBillOfLadingWeight(String billOfLadingWeight) {
		this.billOfLadingWeight = billOfLadingWeight;
	}

	@Column(name = "CONTAINER_SIZEAND_WEIGHT")
	public String getContainerSizeandWeight() {
		return containerSizeandWeight;
	}

	public void setContainerSizeandWeight(String containerSizeandWeight) {
		this.containerSizeandWeight = containerSizeandWeight;
	}

	@Column(name = "DANGEROUS_GOODS")
	public String getDangerousGoods() {
		return dangerousGoods;
	}

	public void setDangerousGoods(String dangerousGoods) {
		this.dangerousGoods = dangerousGoods;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "GOODS_NATURE")
	public String getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
	}

	@Column(name = "PACKING_LIST_NUMBER")
	public String getPackingListNumber() {
		return packingListNumber;
	}

	public void setPackingListNumber(String packingListNumber) {
		this.packingListNumber = packingListNumber;
	}

	@Column(name = "SPECIAL_REQUIREMENTS")
	public String getSpecialRequirements() {
		return specialRequirements;
	}

	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}
}