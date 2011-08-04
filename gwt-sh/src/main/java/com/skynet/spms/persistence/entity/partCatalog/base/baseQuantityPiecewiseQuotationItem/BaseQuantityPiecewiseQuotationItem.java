package com.skynet.spms.persistence.entity.partCatalog.base.baseQuantityPiecewiseQuotationItem;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * 抽象层次上的依据采购备件数量的分段价格明细项信息。不同的业务可以扩展本抽象层次上的实体。
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:20
 */

/*************************************************************************
 *Update by : Huhf    2011-4-27
 *CHECKED BY: Shanyf  2011-4-27
 *Confirm by: 
 *Update list:
 * 
************************************************************************ */
@MappedSuperclass
public class BaseQuantityPiecewiseQuotationItem extends BaseEntity {

	/**
	 * 缩写 PBQ 长度 5位 N
	 * Specifies the "from" quantity in a specified price break quantity range.
	 */
	/**
	 * 项号是用来实现同一组分段价格明细项的排序。
	 */
	private int itemNumber;//项号
	/**
	 * 缩写 PBP 长度 13位 N
	 * The unit price for a specific price break quantity range
	 */
	/**
	 * 分段价格的结束数量
	 */
	private float priceAmount;//单价
	private float fromQuantity;//起始数量
	private float toQuantity; //终止数量
	private UnitOfMeasureCode m_UnitOfMeasureCode;//计量单位
	private InternationalCurrencyCode m_InternationalCurrencyCode;//币种
	private String quantityScope;//数量范围
	
	
	@Column(name = "QUANTITY_SCOPE")
	public String getQuantityScope() {
		return quantityScope;
	}
	public void setQuantityScope(String quantityScope) {
		this.quantityScope = quantityScope;
	}
	@Column(name="FORM_QUANTITY")
	public float getFromQuantity() {
		return fromQuantity;
	}
	public void setFromQuantity(float fromQuantity) {
		this.fromQuantity = fromQuantity;
	}
	@Column(name="ITEM_NUMBER")
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Column(name="PRICE_AMOUNT")
	public float getPriceAmount() {
		return priceAmount;
	}
	public void setPriceAmount(float priceAmount) {
		this.priceAmount = priceAmount;
	}
	@Column(name="TO_QUANTITY")
	public float getToQuantity() {
		return toQuantity;
	}
	public void setToQuantity(float toQuantity) {
		this.toQuantity = toQuantity;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_OF_MEASURE_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}
	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}
	
}