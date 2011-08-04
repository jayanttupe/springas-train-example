package com.skynet.spms.persistence.entity.partCatalog.base.basePrice;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.l.LeaseIndicator;
import com.skynet.spms.persistence.entity.csdd.p.PackagingCode;
import com.skynet.spms.persistence.entity.csdd.p.PriceTypeCode;
import com.skynet.spms.persistence.entity.csdd.p.ProductCategoryCode;
import com.skynet.spms.persistence.entity.csdd.p.ProprietaryCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierClaimedStatusCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author 曹宏炜
 * @version 1.1
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
public class BasePrice extends BaseEntity {

	/**
	 * 缩写CAT   长度 13位 N
	 * The discounted unit selling price for one each of the Category I Container
	 * available for  transport and storage of the subject part number
	 */
	private float categoryOneContainersPrice;//一类容器价格
	private InternationalCurrencyCode cocpCurrencyCode; //一类容器价格币种
	/**
	 * 缩写 LED 长度 3位 N
	 * Specifies for orders (other than initial provisioning) the maximum number of
	 * calendar days required by the supplier after receipt of purchase order to make
	 * shipment of the quantity ordered.
	 */
	private int leadTime ;//交货期
	/**
	 * 缩写 MSQ 长度5位 N
	 * Specifies the minimum order quantity conforming to the Unit Of Measure Code and
	 * a specified Unit Price.
	 */
	private int minimumSalesQuantity;//最小销售数量
	private UnitOfMeasureCode minsqUnitCode;//最小销售数量单位代码
	/**
	 * 缩写 MOA 长度 4位 AN
	 * Identifies a specific airframe or engine model to which
	 * (1) procurement data for subject part applies,
	 * (2) provisioning data in a transmitted "V" File applies,
	 * (3) a repair quotation request or repair order placement applies,
	 * (4) warranty data applies,
	 * (5) consumption data applies or
	 * (6) delivery configuration data for a subject part applies.
	 */
	/**
	 * 缩写 PED
	 * Specifies the day, month and year on which the Unit Price Amount, applicable
	 * Price Break ‐ Quantities and Amounts, Other Charges, Lead Time, Minimum Sales
	 * Quantity, Repair Price Amount and Standard Package Quantity become effective.
	 * The Price Effective Date is used to indicate the effective date of either a
	 * future list price or a future discount, whichever occurs first.
	 */
	private Date priceEffectiveDate;//价格有效期
	/**
	 * 缩写 PFD
	 * Specifies the day, month and year on which the Unit Price Amount, applicable
	 * Price Break ‐ Quantities and Amounts, Other Charges, Lead Time, Minimum Sales
	 * Quantity and Standard Package Quantity expire. The Price Held Firm ‐ Date is
	 * used to indicate the expiration date of the Procurement Contract.
	 */
	private Date priceHeldFirm;//价格持续时间
	/**
	 * 缩写 PDP  长度4位 数字 例如 00.00
	 * Specifies the trade discount percent (to two decimal places) applicable to the
	 * Unit Price Amount and Repair Price Amount.  The associated price break
	 * information is also discounted.
	 */
	/**
	 * 缩写 PCC 长度 3位 AN
	 * A supplier‐assigned designator to a particular Part Number for use in the
	 * variable pricing functionality.
	 * 
	 * This information is applied against the part numbe record and is submitted to
	 * the Discount Matrix Tab as part of a combination of Product Category Code (PCC)
	 * / Customer Category Code (CCC).  Only one PCC can be assigned to a part number.
	 * It not included on output to the customer.  The  maximum number of occurrences
	 * in the Discount  Matrix Table is 70.
	 */
	 public String modelOfApplicabilityCode;//型号适用性代码
	/**
	 * 缩写 SPQ 长度 3位 N
	 * Specifies the standard selling quantity per package of the subject part number,
	 * conforming to the Unit of Measure.
	 */
	private int standardPackageQuantity ;//标准包装数量
	private UnitOfMeasureCode spqUnitCode;//标准包装数量单位代码
	/**
	 * 缩写 UNC
	 * Specifies the specific quantity and unit for a non definitive Unit of Measure;
	 * e.g., quantity of feet per length (Unit of Measure), quantity of gallons per
	 * drum (Unit of Measure).
	 */
	private String unitOfMeasureClarificationText;//单位说明
	/**
	 * Unit of price 单价 The sell price for one unit of the subject part conforming to
	 * the Currency Code, Unit of Measure, and when applicable, Price Break Quantity
	 * range.
	 * 
	 * 长度 13位 N 缩写 UNP
	 */
	private float unitPriceAmount ;//单价
	private InternationalCurrencyCode upaCurrencyCode;//单价币种
	//private String alternateSupplyLocation;//可选供应地址
	private List<AlternateSupplyLocationText> m_AlternateSupplyLocationText;//可选供应地址
	private ProductCategoryCode m_ProductCategoryCode;//产品分类代码
	private PackagingCode m_PackagingCode;//包装代码
	private ProprietaryCode m_ProprietaryCode;//所有权代码
	private PriceTypeCode m_PriceTypeCode;//价格类型代码
	private SupplierClaimedStatusCode m_SupplierClaimedStatusCode;//供应商声明状态代码
	private List<OtherCharge> m_OtherCharge;//其他费用
	private LeaseIndicator m_LeaseIndicator;//是否租赁件
	private SpecialPackaging m_SpecialPackaging;//特殊包装
	private String certificateType;//证书类型（多选，逗号分隔）
	
	
	@Reference(itemCls=AlternateSupplyLocationText.class)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "PRICE_ID")
	public List<AlternateSupplyLocationText> getM_AlternateSupplyLocationText() {
		return m_AlternateSupplyLocationText;
	}
	public void setM_AlternateSupplyLocationText(
			List<AlternateSupplyLocationText> m_AlternateSupplyLocationText) {
		this.m_AlternateSupplyLocationText = m_AlternateSupplyLocationText;
	}
	@Column(name = "CERTIFICATE_TYPE")
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	@Embedded
	public SpecialPackaging getM_SpecialPackaging() {
		return m_SpecialPackaging;
	}
	public void setM_SpecialPackaging(SpecialPackaging m_SpecialPackaging) {
		this.m_SpecialPackaging = m_SpecialPackaging;
	}
	
	@Column(name = "CATE_ONE_CONT_PRICE")
	public float getCategoryOneContainersPrice() {
		return categoryOneContainersPrice;
	}
	public void setCategoryOneContainersPrice(float categoryOneContainersPrice) {
		this.categoryOneContainersPrice = categoryOneContainersPrice;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "COCP_CURRENCY_CODE")
	public InternationalCurrencyCode getCocpCurrencyCode() {
		return cocpCurrencyCode;
	}
	public void setCocpCurrencyCode(InternationalCurrencyCode cocpCurrencyCode) {
		this.cocpCurrencyCode = cocpCurrencyCode;
	}
	@Column(name = "MODEL_OF_APPLI_CODE")
	public String getModelOfApplicabilityCode() {
		return modelOfApplicabilityCode;
	}
	public void setModelOfApplicabilityCode(String modelOfApplicabilityCode) {
		this.modelOfApplicabilityCode = modelOfApplicabilityCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PROPRIETARY_CODE")
	public ProprietaryCode getM_ProprietaryCode() {
		return m_ProprietaryCode;
	}
	public void setM_ProprietaryCode(ProprietaryCode m_ProprietaryCode) {
		this.m_ProprietaryCode = m_ProprietaryCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "LEASE_IDNICATOR")
	public LeaseIndicator getM_LeaseIndicator() {
		return m_LeaseIndicator;
	}
	public void setM_LeaseIndicator(LeaseIndicator m_LeaseIndicator) {
		this.m_LeaseIndicator = m_LeaseIndicator;
	}
	
	@Column(name="LEAD_TIME")
	public int getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}
	@Column(name="MINIMUM_SALES_QUANTITY")
	public int getMinimumSalesQuantity() {
		return minimumSalesQuantity;
	}
	public void setMinimumSalesQuantity(int minimumSalesQuantity) {
		this.minimumSalesQuantity = minimumSalesQuantity;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "MINSQ_UNIT_CODE")
	public UnitOfMeasureCode getMinsqUnitCode() {
		return minsqUnitCode;
	}
	public void setMinsqUnitCode(UnitOfMeasureCode minsqUnitCode) {
		this.minsqUnitCode = minsqUnitCode;
	}
	@Column(name="PRICE_EFFECTIVE_DATE")
	public Date getPriceEffectiveDate() {
		return priceEffectiveDate;
	}
	public void setPriceEffectiveDate(Date priceEffectiveDate) {
		this.priceEffectiveDate = priceEffectiveDate;
	}
	@Column(name="PRICE_HELD_FIRM")
	public Date getPriceHeldFirm() {
		return priceHeldFirm;
	}
	public void setPriceHeldFirm(Date priceHeldFirm) {
		this.priceHeldFirm = priceHeldFirm;
	}
	@Column(name="STANDARD_PACKAGE_QUANTITY")
	public int getStandardPackageQuantity() {
		return standardPackageQuantity;
	}
	public void setStandardPackageQuantity(int standardPackageQuantity) {
		this.standardPackageQuantity = standardPackageQuantity;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "SPQ_UNIT_CODE")
	public UnitOfMeasureCode getSpqUnitCode() {
		return spqUnitCode;
	}
	public void setSpqUnitCode(UnitOfMeasureCode spqUnitCode) {
		this.spqUnitCode = spqUnitCode;
	}
	@Column(name="UNIT_OF_MEASURE_CT")
	public String getUnitOfMeasureClarificationText() {
		return unitOfMeasureClarificationText;
	}
	public void setUnitOfMeasureClarificationText(
			String unitOfMeasureClarificationText) {
		this.unitOfMeasureClarificationText = unitOfMeasureClarificationText;
	}
	
	@Column(name="UNIT_PRICE_AMOUNT")
	public float getUnitPriceAmount() {
		return unitPriceAmount;
	}
	public void setUnitPriceAmount(float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "UPA_CURRENCY_CODE")
	public InternationalCurrencyCode getUpaCurrencyCode() {
		return upaCurrencyCode;
	}
	public void setUpaCurrencyCode(InternationalCurrencyCode upaCurrencyCode) {
		this.upaCurrencyCode = upaCurrencyCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PRODUCT_CATEGORY_CODE")
	public ProductCategoryCode getM_ProductCategoryCode() {
		return m_ProductCategoryCode;
	}
	public void setM_ProductCategoryCode(ProductCategoryCode m_ProductCategoryCode) {
		this.m_ProductCategoryCode = m_ProductCategoryCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_CODE")
	public PackagingCode getM_PackagingCode() {
		return m_PackagingCode;
	}
	public void setM_PackagingCode(PackagingCode m_PackagingCode) {
		this.m_PackagingCode = m_PackagingCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PRICE_TYPE_CODE")
	public PriceTypeCode getM_PriceTypeCode() {
		return m_PriceTypeCode;
	}
	public void setM_PriceTypeCode(PriceTypeCode m_PriceTypeCode) {
		this.m_PriceTypeCode = m_PriceTypeCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "SUPPLIER_CS_CODE")
	public SupplierClaimedStatusCode getM_SupplierClaimedStatusCode() {
		return m_SupplierClaimedStatusCode;
	}
	public void setM_SupplierClaimedStatusCode(
			SupplierClaimedStatusCode m_SupplierClaimedStatusCode) {
		this.m_SupplierClaimedStatusCode = m_SupplierClaimedStatusCode;
	}
	@Reference(itemCls=OtherCharge.class)
	@OneToMany()
	@JoinColumn(name = "PRICE_ID")
	public List<OtherCharge> getM_OtherCharge() {
		return m_OtherCharge;
	}
	public void setM_OtherCharge(List<OtherCharge> m_OtherCharge) {
		this.m_OtherCharge = m_OtherCharge;
	}
}