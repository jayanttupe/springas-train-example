package com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;

/**
 * @author huangdj
 * @version 1.1
 * @created 2011-4-7
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_PACKING_LIST")
public class PackingList extends BaseEntity {
	/**
	 * 装箱单编号
	 */
	private String packingListNumber;
	
	/**
	 * 配料单ID
	 */
	private String pickingListID;
	
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
	 * 指令编号
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
	 * 交货日期
	 */
	private Date deliveryDate;

	/**
	 * 箱数
	 */
	private int boxQty;

	/**
	 * 状态
	 */
	private OutStockStatus status;
	
	/**
	 * 检验人
	 */
    private String identifier;
    
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
	 * 装箱明细
	 */
	private List<PackingListPartItems> packingListPartItemsList;
	
	public PackingList() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "PACKING_LIST_NUMBER")
	public String getPackingListNumber() {
		return packingListNumber;
	}

	public void setPackingListNumber(String packingListNumber) {
		this.packingListNumber = packingListNumber;
	}

	@Column(name = "PICKING_LIST_ID")
	public String getPickingListID() {
		return pickingListID;
	}

	public void setPickingListID(String pickingListID) {
		this.pickingListID = pickingListID;
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

	@Column(name = "BOX_QTY")
	public int getBoxQty() {
		return boxQty;
	}

	public void setBoxQty(int boxQty) {
		this.boxQty = boxQty;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public OutStockStatus getStatus() {
		return status;
	}

	public void setStatus(OutStockStatus status) {
		this.status = status;
	}

	@Column(name = "IDENTIFIER")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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

	@OneToMany(targetEntity= PackingListPartItems.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="PACKING_LIST_ID")
	public List<PackingListPartItems> getPackingListPartItemsList() {
		return packingListPartItemsList;
	}

	public void setPackingListPartItemsList(
			List<PackingListPartItems> packingListPartItemsList) {
		this.packingListPartItemsList = packingListPartItemsList;
	}

}