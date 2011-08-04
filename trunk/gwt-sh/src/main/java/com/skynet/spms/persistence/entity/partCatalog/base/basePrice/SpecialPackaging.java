package com.skynet.spms.persistence.entity.partCatalog.base.basePrice;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;

/**
 * @author 王坤杰
 * @version 1.0
 * @created 30-四月-2011 13:41:59
 */

@Embeddable
public class SpecialPackaging{
    
	private boolean isSpecialPack;//是否是特殊包装
	private float specialPackPrice;//特殊包装价格
	private InternationalCurrencyCode sppCurrencyCode;//特殊包装价格币种
	private String specialPackingInstruction;//特殊包装说明
	
	@Column(name="IS_SPECIAL_PACK")
	public boolean isSpecialPack() {
		return isSpecialPack;
	}
	public void setSpecialPack(boolean isSpecialPack) {
		this.isSpecialPack = isSpecialPack;
	}
	@Column(name="SPECIAL_PACK_PRICE")
	public float getSpecialPackPrice() {
		return specialPackPrice;
	}
	public void setSpecialPackPrice(float specialPackPrice) {
		this.specialPackPrice = specialPackPrice;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "SPP_CURRENCY_CODE")
	public InternationalCurrencyCode getSppCurrencyCode() {
		return sppCurrencyCode;
	}
	public void setSppCurrencyCode(InternationalCurrencyCode sppCurrencyCode) {
		this.sppCurrencyCode = sppCurrencyCode;
	}
	@Column(name="SPECICAL_PACKING_INSTR")
	public String getSpecialPackingInstruction() {
		return specialPackingInstruction;
	}
	public void setSpecialPackingInstruction(String specialPackingInstruction) {
		this.specialPackingInstruction = specialPackingInstruction;
	}
}