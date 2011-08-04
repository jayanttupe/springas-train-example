package com.skynet.spms.persistence.entity.base.partCatalog.base.baseQuantityPiecewiseQuotationItem;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
/**
 * 抽象层次上的依据采购备件数量的分段价格明细项信息。不同的业务可以扩展本抽象层次上的实体。
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:04
 */
@MappedSuperclass
public class baseQuantityPiecewiseQuotationItem extends BaseEntity {
	
	/**
	 * 项号是用来实现同一组分段价格明细项的排序。
	 */
	private Integer itemNumber;
	/**
	 * 数量分为来自：起始数量和终止数量；
	 * 
	 * 起始数量：
	 * 缩写 PBQ 长度 5位 N
	 * Specifies the "from" quantity in a specified price break quantity range.
	 * 
	 * 终止数量：
	 * 分段价格的结束数量。
	 */
	private Float quantityScope;
	/**
	 * 缩写 PBP 长度 13位 N
	 * The unit price for a specific price break quantity range
	 */
	private Float priceAmount;
	/**
	 *分段价格的开始数量
	 * Specifies the "from" quantity in a specified price break quantity range.
	 */
	private Float fromQuantity;
	/**
	 * 分段价格的结束数量
	 */
	private Float toQuantity;
	
	/**单位**/
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	
	/**币种**/
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	
	
	
	
	private String quantityType;
	
	@Transient
	public String getQuantityType() {
		return quantityType;
	}
	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}
	@Column(name ="ITEMNUMBER")
	public Integer getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Column(name ="QUANTITYSCOPE")
	public Float getQuantityScope() {
		return quantityScope;
	}
	public void setQuantityScope(Float quantityScope) {
		this.quantityScope = quantityScope;
	}
	
	@Column(name ="PRICEAMOUNT")
	public Float getPriceAmount() {
		return priceAmount;
	}
	public void setPriceAmount(Float priceAmount) {
		this.priceAmount = priceAmount;
	}
	
	@Column(name ="FROMQUANTITY")
	public Float getFromQuantity() {
		return fromQuantity;
	}
	public void setFromQuantity(Float fromQuantity) {
		this.fromQuantity = fromQuantity;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}
	
	
	
	@Column(name ="TOQUANTITY")
	public Float getToQuantity() {
		return toQuantity;
	}
	public void setToQuantity(Float toQuantity) {
		this.toQuantity = toQuantity;
	}
	
	
	
}