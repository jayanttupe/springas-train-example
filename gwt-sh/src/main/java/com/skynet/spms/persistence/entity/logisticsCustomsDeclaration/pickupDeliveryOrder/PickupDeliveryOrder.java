package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder;
/**
 * 物流提发货指令
 */
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder.BasePickupDeliveryOrder;

/**
 * 发货指令是主要包括了基础发货指令所需的基本属性，发货指令一旦物流人员设定为接受状态，
 * 则库管人员需进行装箱，并将装箱数据提供给物流人员。
 * @author 乔海龙
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@ViewFormAnno(value="id")
@Entity
@Table( name = "SPMS_PICKUP_DELIVERY_ORDER")
public class PickupDeliveryOrder extends BasePickupDeliveryOrder {
	
	/**
	 * 交货日期(发货)
	 */
	private Date deliveryDate;
	/**
	 * 起运日期(发货)
	 */
	private Date shippedDate;

	/**
	 * 到货日期(提货)
	 */
	private Date arrivalDate;
	
	/**
	 * 提货日期(提货)
	 */
	private Date pickupDate;
	
	/**
	 * 是否已收料(0:未收料/1:已收料)
	 */
	private String isReceiving = "0";
	
	/**
	 * 是否已发料(0:未配料/1:已配料)
	 */
	private String isPicking = "0";
	
	/**
	 * 报送类型(1:国内/2:进口/3:出口)
	 */
	private String customsDeclarationType;
	
	/**
     * (0:即不是保证金也不是关税)/(1:保证金)/(2:关税)
     */
    private String securityOrCustomsTariff = "0";
	
	/**
     * (0:即不是保证金也不是关税)/(1:保证金)/(2:关税)
     */
    private String securityOrCustomsTariffName;

	/**
	 * 主运单号
	 */
	private String mainWayBill;

	/**
	 * 分运单号
	 */
	private String houseWayBill;

	/**
	 * 提发货装箱信息
	 */
	private List<PickupDeliveryVanning> pickupDeliveryVanningList;
	
	@Column(name = "DELIVERY_DATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "SHIPPED_DATE")
	public Date getShippedDate() {
		return shippedDate;
	}
	
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	
	@Column(name = "ARRIVAL_DATE")
	public Date getArrivalDate() {
		return arrivalDate;
	}
	
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@Column(name = "PICKUP_DATE")
	public Date getPickupDate() {
		return pickupDate;
	}
	
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	@Column(name = "IS_RECEIVING")
	public String getIsReceiving() {
		return isReceiving;
	}

	public void setIsReceiving(String isReceiving) {
		this.isReceiving = isReceiving;
	}

	@Column(name = "IS_PICKING")
	public String getIsPicking() {
		return isPicking;
	}

	public void setIsPicking(String isPicking) {
		this.isPicking = isPicking;
	}

	@Column(name = "CUSTOMS_DECLARATION_TYPE")
	public String getCustomsDeclarationType() {
		return customsDeclarationType;
	}

	public void setCustomsDeclarationType(String customsDeclarationType) {
		this.customsDeclarationType = customsDeclarationType;
	}
	
	@Column(name="SECURITY_OR_CUSTOMS_TARIFF")
	public String getSecurityOrCustomsTariff() {
		return securityOrCustomsTariff;
	}
	
	public void setSecurityOrCustomsTariff(String securityOrCustomsTariff) {
		this.securityOrCustomsTariff = securityOrCustomsTariff;
	}

	@Transient
	public String getSecurityOrCustomsTariffName() {
		return securityOrCustomsTariffName;
	}

	public void setSecurityOrCustomsTariffName(String securityOrCustomsTariffName) {
		this.securityOrCustomsTariffName = securityOrCustomsTariffName;
	}

	@Column(name = "MAIN_WAY_BILL")
	public String getMainWayBill() {
		return mainWayBill;
	}

	public void setMainWayBill(String mainWayBill) {
		this.mainWayBill = mainWayBill;
	}

	@Column(name = "HOUSE_WAY_BILL")
	public String getHouseWayBill() {
		return houseWayBill;
	}

	public void setHouseWayBill(String houseWayBill) {
		this.houseWayBill = houseWayBill;
	}

	@OneToMany(targetEntity= PickupDeliveryVanning.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="ORDER_ID")
	public List<PickupDeliveryVanning> getPickupDeliveryVanningList() {
		return pickupDeliveryVanningList;
	}

	public void setPickupDeliveryVanningList(List<PickupDeliveryVanning> pickupDeliveryVanningList) {
		this.pickupDeliveryVanningList = pickupDeliveryVanningList;
	}
}