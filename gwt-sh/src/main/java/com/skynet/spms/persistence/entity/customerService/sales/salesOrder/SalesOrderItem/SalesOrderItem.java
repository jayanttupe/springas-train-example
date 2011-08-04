package com.skynet.spms.persistence.entity.customerService.sales.salesOrder.SalesOrderItem;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.baseSupplyChain.VanningControl;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.p.PackagingCode;
import com.skynet.spms.persistence.entity.csdd.p.PartStatusCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;
import com.skynet.spms.persistence.entity.spmsdd.EquipmentType;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * @author Administrator
 * @version 1.0
 * @created 05-五月-2011 11:15:45
 */
@Entity
@Table(name = "SPMS_SALESORDERITEM")
public class SalesOrderItem extends BaseEntity {

	/**项号**/
	private Integer itemNumber;
	/**件号**/
	private String partNumber;
	/**描述**/
	private String description;
	/**数量**/
	private Float quantity;
	/**单价**/
	private Float unitPriceAmount;
	/**总金额**/
	private Float amount;
	/**交货日期**/
	private Date deliveryDate;
	/**条件与证书**/
	private String conditionCertificate;
	
	private EquipmentType m_EquipmentType;
	/**单位**/
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	//public IsEngine m_IsEngine;
	/**装箱控制**/
	private VanningControl m_VanningControl;
	/**备注**/
	private String  remark;
	//private RemarkTextEntity m_RemarkTextEntity;
	/**制造商代码**/
	private ManufacturerCode m_ManufacturerCode;
	/**证书类型**/
	public String m_CertificateType;
	/**优先级**/
	private Priority m_Priority;
	/**备件状态代码**/
	private PartStatusCode m_PartStatusCode;
	/**包装代码**/
	private PackagingCode m_PackagingCode;
	//public IsSerial m_IsSerial;
	/**币种**/
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	//public partSaleRelease m_partSaleRelease;
	/**销售明细项**/
	private SalesRequisitionSheetItem m_SalesRequisitionSheetItem;
	
	@Column(name = "ITEM_NUMBER")
	public Integer getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	@Column(name = "QUANTITY")
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "UNTI_AMOUNT")
	public Float getUnitPriceAmount() {
		return unitPriceAmount;
	}
	public void setUnitPriceAmount(Float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}
	
	
	@Column(name = "AMOUNT")
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	@Column(name = "DELIVERY_DATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Column(name = "CONDITION_CERTIFICATE")
	public String getConditionCertificate() {
		return conditionCertificate;
	}
	public void setConditionCertificate(String conditionCertificate) {
		this.conditionCertificate = conditionCertificate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}
	public void setM_UnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
		m_UnitOfMeasureCode = unitOfMeasureCode;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "VANNING_CONTROL")
	public VanningControl getM_VanningControl() {
		return m_VanningControl;
	}
	public void setM_VanningControl(VanningControl vanningControl) {
		m_VanningControl = vanningControl;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@ManyToOne
	@JoinColumn(name = "MANU_CODE")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}
	public void setM_ManufacturerCode(ManufacturerCode manufacturerCode) {
		m_ManufacturerCode = manufacturerCode;
	}
	
	
	@Column(name = "CERTIFICATE_TYPE")
	public String getM_CertificateType() {
		return m_CertificateType;
	}
	public void setM_CertificateType(String certificateType) {
		m_CertificateType = certificateType;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}
	public void setM_Priority(Priority priority) {
		m_Priority = priority;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PARTSTATUS_CODE")
	public PartStatusCode getM_PartStatusCode() {
		return m_PartStatusCode;
	}
	public void setM_PartStatusCode(PartStatusCode partStatusCode) {
		m_PartStatusCode = partStatusCode;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_CODE")
	public PackagingCode getM_PackagingCode() {
		return m_PackagingCode;
	}
	public void setM_PackagingCode(PackagingCode packagingCode) {
		m_PackagingCode = packagingCode;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "INTERN_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode internationalCurrencyCode) {
		m_InternationalCurrencyCode = internationalCurrencyCode;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SRSITEM_ID")
	public SalesRequisitionSheetItem getM_SalesRequisitionSheetItem() {
		return m_SalesRequisitionSheetItem;
	}
	public void setM_SalesRequisitionSheetItem(
			SalesRequisitionSheetItem salesRequisitionSheetItem) {
		m_SalesRequisitionSheetItem = salesRequisitionSheetItem;
	}

}