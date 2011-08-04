package com.skynet.spms.persistence.entity.financeService.buySettleAccounts;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.spmsdd.Payment;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.PayActionType;


/**
 * @author 黄赟
 * @version 1.0
 * @created 21-四月-2011 19:57:55
 */

@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_PAY_APPLY")
public class PayApplyTable extends BaseEntity {

	private Date applyDate;
	private String applyDepartment;
	private String applyNumber;
	private String cashierName;
	private String orderNumber;
	private int payMode;
	private String payMoneyMode;
	private String applyUser;
	private String check;
	private String checkUser;
	private String countersignResult;
	private Double payAmount;
    private String accountName;
    private String accountAddress;
    private String account;
    private Integer totalCount;
    private float totalAmount;
	
    private String payDescription;
	public Payment m_Payment;
	public InternationalCurrencyCode m_InternationalCurrencyCode;
	public PayActionType m_PayActionType;
	public List<Attachment> m_Attachment;

	
	
	@Column(name="TOTAL_AMOUNT")
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="TOTAL_COUNT")
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	//public BaseContactEntity m_BaseContactEntity;
	@Column(name="APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Column(name="PAY_DESCRIPTION")
	public String getPayDescription() {
		return payDescription;
	}
	public void setPayDescription(String payDescription) {
		this.payDescription = payDescription;
	}
	@Column(name="APPLY_DEPARTMENT")
	public String getApplyDepartment() {
		return applyDepartment;
	}
	public void setApplyDepartment(String applyDepartment) {
		this.applyDepartment = applyDepartment;
	}
	@Column(name="APPLY_NUMBER")
	public String getApplyNumber() {
		return applyNumber;
	}
	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}
	@Column(name="CASHIER_NAME")
	public String getCashierName() {
		return cashierName;
	}
	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}
	@Column(name="ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Column(name="PAY_MODE")
	public int getPayMode() {
		return payMode;
	}
	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}
	@Column(name="PAY_MONEY_MODE")
	public String getPayMoneyMode() {
		return payMoneyMode;
	}
	public void setPayMoneyMode(String payMoneyMode) {
		this.payMoneyMode = payMoneyMode;
	}
	@Column(name="APPLY_USER")
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	@Column(name="PAY_APPLY_CHECK")
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	@Column(name="CHECK_USER")
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	@Column(name="COUNTERSIGN_RESULT")
	public String getCountersignResult() {
		return countersignResult;
	}
	public void setCountersignResult(String countersignResult) {
		this.countersignResult = countersignResult;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="PAYMENT")
	public Payment getM_Payment() {
		return m_Payment;
	}
	public void setM_Payment(Payment m_Payment) {
		this.m_Payment = m_Payment;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="INTERNATIONAL_CURRENCY_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="PAY_ACTION_TYPE")
	public PayActionType getM_PayActionType() {
		return m_PayActionType;
	}
	public void setM_PayActionType(PayActionType m_PayActionType) {
		this.m_PayActionType = m_PayActionType;
	}
	
	@Reference(itemCls=Attachment.class)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "RELATED_BUSSINESS_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}
	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}
	
	@Column(name="PAY_AMOUNT")
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	@Column(name="ACCOUNT_NAME")
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	@Column(name="ACCOUNT_ADDRESS")
	public String getAccountAddress() {
		return accountAddress;
	}
	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}
	@Column(name="ACCOUNT")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	

}