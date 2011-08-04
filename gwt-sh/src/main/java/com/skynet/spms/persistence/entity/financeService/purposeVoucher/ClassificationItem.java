package com.skynet.spms.persistence.entity.financeService.purposeVoucher;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import    com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 黄赟
 * @version 1.0
 * @created 21-四月-2011 19:57:54
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_CLASSIFICATION_ITEM")
public class ClassificationItem extends BaseEntity {

	private String classificationItemCode;
	private String classificationItemName;
	private String currency;
	private int itemNumber;
	private String summery;
	private double tradeAmount;
	private double creditAmount;
	private double debitAmount;
	/**
	 * 记账方向：
	 * 0：贷方
	 * 1：借方
	 */
	private int accountDirection;
	private double unitPrice;
	private int number;
	private double exchangeRate;
	public Subjects subject;
	private String classificationItemId;
	
	
/*	private String subjectName;
	
	
	@Column(name="SUBJECT_NAME")
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}*/
	@Column(name="CLASSIFICATION_ITEM_CODE")
	public String getClassificationItemCode() {
		return classificationItemCode;
	}
	public void setClassificationItemCode(String classificationItemCode) {
		this.classificationItemCode = classificationItemCode;
	}
	@Column(name="CLASSIFICATION_ITEM_NAME")
	public String getClassificationItemName() {
		return classificationItemName;
	}
	public void setClassificationItemName(String classificationItemName) {
		this.classificationItemName = classificationItemName;
	}
	@Column(name="CURRENCY")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Column(name="ITEM_NUMBER")
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Column(name="SUMMERY")
	public String getSummery() {
		return summery;
	}
	public void setSummery(String summery) {
		this.summery = summery;
	}
	@Column(name="TRADE_AMOUNT")
	public double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	@Column(name="CREDIT_AMOUNT")
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	@Column(name="DEBIT_AMOUNT")
	public double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}
	@Column(name="ACCOUNT_DIRECTION")
	public int getAccountDirection() {
		return accountDirection;
	}
	public void setAccountDirection(int accountDirection) {
		this.accountDirection = accountDirection;
	}
	@Column(name="UNIT_PRICE")
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Column(name="CI_NUMBER")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Column(name="EXCHANGE_RATE")
	public double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	@ManyToOne()
	@JoinColumn(name="SUBJECT_ID")
	public Subjects getSubject() {
		return subject;
	}
	public void setSubject(Subjects subject) {
		this.subject = subject;
	}
	
	@Column(name="CLASSIFICATION_ITEM_ID")
	public String getClassificationItemId() {
		return classificationItemId;
	}
	public void setClassificationItemId(String classificationItemId) {
		this.classificationItemId = classificationItemId;
	}
	
	


	
	

}