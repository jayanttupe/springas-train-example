package com.skynet.spms.persistence.entity.partCatalog.base.basePrice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.o.OtherChargeCode;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 
 * Specifies the type and amount of special charge (excluding normal Sales and
 * Value Added Taxes) assessed on orders for the subject part.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:23
 */
@Entity
@FilterDefs({ @FilterDef(name = "active", 
		defaultCondition = "IS_DELETED = :isDele", 
		parameters = @ParamDef(name = "isDele", type = "boolean")) })
@Filter(name = "active")
@Table(name="SPMS_OTHER_CHARGE")
public class OtherCharge extends BaseEntity {

	private float otherChargeAmount;//其他费用金额
	private String remarkText;//备注
	private OtherChargeCode m_OtherChargeCode; //其他费用代码
	private String priceId; //外键，引用价格表
	private InternationalCurrencyCode m_InternationalCurrencyCode;//国际货币代码
	
		
	@Column(name="OTHER_CHARGE_AMOUNT")
	public float getOtherChargeAmount() {
		return otherChargeAmount;
	}
	public void setOtherChargeAmount(float otherChargeAmount) {
		this.otherChargeAmount = otherChargeAmount;
	}
	@Column(name="REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "OTHER_CHARGE_CODE")
	public OtherChargeCode getM_OtherChargeCode() {
		return m_OtherChargeCode;
	}
	public void setM_OtherChargeCode(OtherChargeCode m_OtherChargeCode) {
		this.m_OtherChargeCode = m_OtherChargeCode;
	}
	@Column(name="BASE_PRICE_ID")
	public String getPriceId() {
		return priceId;
	}
	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "INTER_CURR_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}


}