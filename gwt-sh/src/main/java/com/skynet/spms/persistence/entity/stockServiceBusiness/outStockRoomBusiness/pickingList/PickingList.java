package com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

/**
 * @author 
 * @version 1.1
 * @created 
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Table(name = "SPMS_PICKING_LIST")
public class PickingList extends BaseEntity {
	/**
	 * 配料单号
	 */
	private String pickingListNumber;

	/**
	 * 业务类型
	 */
	private BusinessType businessType;

	/**
	 * 指令ID
	 */
	private String orderID;

	/**
	 * 指令单号
	 */
	private String orderNumber;

	/**
	 * 合同编号
	 */
	private String contractNumber;

	/**
	 * 优先级
	 */
	private Priority priority;

	/**
	 * 发货日期
	 */
	private Date deliveryDate;

	/**
	 * 贸易方式
	 */
	private TradeMethods tradeMethods;

	/**
	 * 业务员
	 */
	private String operator;

	/**
	 * 机号
	 */
	private String machineNumber;

	/**
	 * 是否保税
	 */
	private String isBonded;

	/**
	 * 状态
	 */
	private OutStockStatus status;

	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 发货人姓名
	 */
    private String shipper;
    
	/**
	 * 发货单位
	 */
    private String companyOfShipper;
    
	/**
	 * 发货地址及详细地址
	 */
    private String addressOfShipper;
    
	/**
	 * 发货人联系方式
	 */
    private String telephonOfShipper;
    
	/**
	 * 收货人姓名
	 */
    private String consignee;
    
	/**
	 * 收货单位
	 */
    private String companyOfConsignee;
    
	/**
	 * 收货地址及详细地址
	 */
    private String addressOfConsignee;
    
	/**
	 * 收货人联系方式
	 */
    private String telephoneOfConsignee;

	/**
	 * 配料明细
	 */
	private List<PickingListPartItems> pickingListPartItemsList;

	public PickingList() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "PICKING_LIST_NUMBER")
	public String getPickingListNumber() {
		return pickingListNumber;
	}

	public void setPickingListNumber(String pickingListNumber) {
		this.pickingListNumber = pickingListNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BUSINESS_TYPE")
	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Column(name = "DELIVERY_DATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TRADE_METHODS")
	public TradeMethods getTradeMethods() {
		return tradeMethods;
	}

	public void setTradeMethods (TradeMethods tradeMethods) {
		this.tradeMethods = tradeMethods;
	}

	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "MACHINE_NUMBER")
	public String getMachineNumber() {
		return machineNumber;
	}

	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}

	@Column(name = "IS_BONDED")
	public String getIsBonded() {
		return isBonded;
	}

	public void setIsBonded(String isBonded) {
		this.isBonded = isBonded;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public OutStockStatus getStatus() {
		return status;
	}

	public void setStatus(OutStockStatus status) {
		this.status = status;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Transient
	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	@Transient
	public String getCompanyOfShipper() {
		return companyOfShipper;
	}

	public void setCompanyOfShipper(String companyOfShipper) {
		this.companyOfShipper = companyOfShipper;
	}

	@Transient
	public String getAddressOfShipper() {
		return addressOfShipper;
	}

	public void setAddressOfShipper(String addressOfShipper) {
		this.addressOfShipper = addressOfShipper;
	}

	@Transient
	public String getTelephonOfShipper() {
		return telephonOfShipper;
	}

	public void setTelephonOfShipper(String telephonOfShipper) {
		this.telephonOfShipper = telephonOfShipper;
	}

	@Transient
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@Transient
	public String getCompanyOfConsignee() {
		return companyOfConsignee;
	}

	public void setCompanyOfConsignee(String companyOfConsignee) {
		this.companyOfConsignee = companyOfConsignee;
	}

	@Transient
	public String getAddressOfConsignee() {
		return addressOfConsignee;
	}

	public void setAddressOfConsignee(String addressOfConsignee) {
		this.addressOfConsignee = addressOfConsignee;
	}

	@Transient
	public String getTelephoneOfConsignee() {
		return telephoneOfConsignee;
	}

	public void setTelephoneOfConsignee(String telephoneOfConsignee) {
		this.telephoneOfConsignee = telephoneOfConsignee;
	}

	@Transient
	public List<PickingListPartItems> getPickingListPartItems() {
		return pickingListPartItemsList;
	}

	public void setPickingListPartItems(List<PickingListPartItems> pickingListPartItemsList) {
		this.pickingListPartItemsList = pickingListPartItemsList;
	}

}