package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsDeclarationPartItem;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author FANYX
 * @version 1.0    基础报关备件项
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BaseCustomsPartItem extends BaseEntity {

	/**
	 * 指令ID(与基础任务关联)
	 */
	private String orderID;
	/**
	 * 报关ID(基础报关记录备件项)
	 */
	private String customsID;
	/**
	 * 总价(基础报关记录备件项)
	 */
	private String amount;
	/**
	 *名称(基础报关记录备件项)
	 */
	private String name;
	/**
	 * 征免性质(基础报关记录备件项)
	 */
	private String freeGoodsPropertie;
	/**
	 * 项号(基础报关记录备件项)
	 */
	private String itemNumber;
	/**
	 * 件号(基础报关记录备件项)
	 */
	private String partNumber;
	/**
	 * 件描述
	 */
	private String partName;
	/**
	 * 数量(基础报关记录备件项)
	 */
	private String quantity;
	/**
	 *税号(HS商品编码) (基础报关记录备件项)
	 */
	private String taxFileNumber;
	/**
	 * 单价(基础报关记录备件项)
	 */
	private String unitPrice;
	/**
	 * 计量单位(基础报关记录备件项)
	 */
	private UnitOfMeasureCode unitOfMeasure;
	/**
	 * 担保金额(发票备件)
	 */
	private String invoiceGuarantyAmount;
	/**
	 * 税率(%)(发票备件)
	 */
	private String invoiceRates;
	/**
	 * 完税金额(发票备件)
	 */
	private String invoiceTaxAmount;
	/**
	 * (计量)单位(发票备件)
	 */
	private String invoiceUnit;
	/**
	 * 关税完税价格(关税备件)
	 */
	private String payTafiffPrice;
	/**
	 * 关税税率(关税备件)
	 */
	private String tariffRate;
	/**
	 * 关税税款金额(关税备件)
	 */
	private String tafiffPayment;
	/**
	 *增值税完税价格 (增值税备件)
	 */
	private String payTaxesPrice;
	/**
	 * 增值税税款金额(增值税备件)
	 */
	private String taxPayment;
	/**
	 * 增值税税率(增值税备件)
	 */
	private String taxRate;
	
	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	@Column(name = "CUSTOMS_ID")
	public String getCustomsID() {
		return customsID;
	}
	public void setCustomsID(String customsID) {
		this.customsID = customsID;
	}
	@Column(name = "AMOUT")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "FREE_GOODS_PROPERTIE")
	public String getFreeGoodsPropertie() {
		return freeGoodsPropertie;
	}
	public void setFreeGoodsPropertie(String freeGoodsPropertie) {
		this.freeGoodsPropertie = freeGoodsPropertie;
	}
	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	@Transient
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Column(name = "TAX_FILE_NUMBER")
	public String getTaxFileNumber() {
		return taxFileNumber;
	}
	public void setTaxFileNumber(String taxFileNumber) {
		this.taxFileNumber = taxFileNumber;
	}
	@Column(name = "UNIT_PRICE")
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Transient
	public UnitOfMeasureCode getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(UnitOfMeasureCode unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	@Column(name = "INVOICE_GUARANTY_AMOUNT")
	public String getInvoiceGuarantyAmount() {
		return invoiceGuarantyAmount;
	}
	public void setInvoiceGuarantyAmount(String invoiceGuarantyAmount) {
		this.invoiceGuarantyAmount = invoiceGuarantyAmount;
	}
	@Column(name = "INVOIC_RATES")
	public String getInvoiceRates() {
		return invoiceRates;
	}
	public void setInvoiceRates(String invoiceRates) {
		this.invoiceRates = invoiceRates;
	}
	@Column(name = "INVOICE_TAX_AMOUT")
	public String getInvoiceTaxAmount() {
		return invoiceTaxAmount;
	}
	public void setInvoiceTaxAmount(String invoiceTaxAmount) {
		this.invoiceTaxAmount = invoiceTaxAmount;
	}
	@Column(name = "INVOICE_UNIT")
	public String getInvoiceUnit() {
		return invoiceUnit;
	}
	public void setInvoiceUnit(String invoiceUnit) {
		this.invoiceUnit = invoiceUnit;
	}
	@Column(name = "PAY_TAFIFF_PRICE")
	public String getPayTafiffPrice() {
		return payTafiffPrice;
	}
	public void setPayTafiffPrice(String payTafiffPrice) {
		this.payTafiffPrice = payTafiffPrice;
	}
	@Column(name = "TARIFF_RATE")
	public String getTariffRate() {
		return tariffRate;
	}
	public void setTariffRate(String tariffRate) {
		this.tariffRate = tariffRate;
	}
	@Column(name = "TAFIFF_PAYMENT")
	public String getTafiffPayment() {
		return tafiffPayment;
	}
	public void setTafiffPayment(String tafiffPayment) {
		this.tafiffPayment = tafiffPayment;
	}
	@Column(name = "PAY_TAXES_PRICE")
	public String getPayTaxesPrice() {
		return payTaxesPrice;
	}
	public void setPayTaxesPrice(String payTaxesPrice) {
		this.payTaxesPrice = payTaxesPrice;
	}
	@Column(name = "TAX_PAYMENT")
	public String getTaxPayment() {
		return taxPayment;
	}
	public void setTaxPayment(String taxPayment) {
		this.taxPayment = taxPayment;
	}
	@Column(name = "TAX_RATE")
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	
}