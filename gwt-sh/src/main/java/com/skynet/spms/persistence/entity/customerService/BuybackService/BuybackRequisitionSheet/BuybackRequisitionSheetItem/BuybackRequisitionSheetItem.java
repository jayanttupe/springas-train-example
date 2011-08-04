package com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheetItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheet;

/**
 * 回购申请单明细
 */
@Entity
@Table(name = "SPMS_BUYBACKSHEETITEM")
public class BuybackRequisitionSheetItem extends BaseApplicationFormItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7164022617740859584L;
	/**
	 * 单价
	 */
	private float unitPriceAmount;
	/** 金额 */
	private float price;
	/** 币种 */
	private InternationalCurrencyCode currency;
	/** 收料追溯号 */
	private String num;
	/** 货架寿命 */
	private int age;
	/** 序号/批号 */
	private String orderNum;
	/** 关联的回购申请单 */
	private BuybackRequisitionSheet sheet;

	@ManyToOne
	@JoinColumn(name = "SHEETID")
	public BuybackRequisitionSheet getSheet() {
		return sheet;
	}

	public void setSheet(BuybackRequisitionSheet sheet) {
		this.sheet = sheet;
	}

	/** 币种 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENCY")
	public InternationalCurrencyCode getCurrency() {
		return currency;
	}

	public void setCurrency(InternationalCurrencyCode currency) {
		this.currency = currency;
	}

	/** 收料追溯号 */
	@Column(name = "NUM")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	/** 货架寿命 */
	@Column(name = "AGE")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/** 序号/批号 */
	@Column(name = "ORDERNUM")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/** 金额 */
	@Column(name = "PRICE")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * 单价
	 * 
	 * @return
	 */
	@Column(name = "UNITPRICEAMOUNT")
	public float getUnitPriceAmount() {
		return unitPriceAmount;
	}

	/**
	 * 单价
	 */
	public void setUnitPriceAmount(float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}
}