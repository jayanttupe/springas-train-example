package com.skynet.spms.persistence.entity.financeService.salesSettleAccounts;

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

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.spmsdd.InvoiceActionType;
import com.skynet.spms.persistence.entity.spmsdd.InvoiceType;
import com.skynet.spms.persistence.entity.spmsdd.PayActionType;

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
@Table(name="SPMS_INVOICE_APPLY")
public class InvoiceApplyTable extends BaseEntity {

	private String applyNumber;
	private Date applyDate;
	private String applyDepartment;
	private String recipeCashierName;
	private String applyUser;
	private double invoiceAmout;
	private String checkUser;
	private Date checkDate;
	private String check;
	private String orderNumber;
	
	//税率
	private float tax;
	//项数总计
	private Integer totalCount;
	// 金额总计 
	private float extendedValueTotalAmount;

	public InternationalCurrencyCode m_InternationalCurrencyCode;
	public InvoiceType m_InvoiceType;
	public InvoiceActionType m_InvoiceActionType;
	public PayActionType m_PayActionType;
	public List<Attachment> m_Attachment;
	// public BaseContactEntity m_BaseContactEntity;
	
	@Column(name="APPLY_NUMBER")
	public String getApplyNumber() {
		return applyNumber;
	}
	@Column(name="TAX")
	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	@Column(name="TOTAL_COUNT")
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Column(name="TOTAL_AMOUNT")
	public float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BUSINESS_TYPE")
	public PayActionType getM_PayActionType() {
		return m_PayActionType;
	}

	public void setM_PayActionType(PayActionType m_PayActionType) {
		this.m_PayActionType = m_PayActionType;
	}

	@Column(name="ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	@Column(name = "INVOICE_TYPE")
	public InvoiceType getM_InvoiceType() {
		return m_InvoiceType;
	}
	public void setM_InvoiceType(InvoiceType m_InvoiceType) {
		this.m_InvoiceType = m_InvoiceType;
	}
	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}
	@Column(name="INVOICE_APPLY_CHECK")
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	
	@Column(name="APPLY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	@Column(name="APPLY_DEPARTMENT")
	public String getApplyDepartment() {
		return applyDepartment;
	}
	public void setApplyDepartment(String applyDepartment) {
		this.applyDepartment = applyDepartment;
	}
	@Column(name="RECIPE_CASHIER_NAME")
	public String getRecipeCashierName() {
		return recipeCashierName;
	}
	public void setRecipeCashierName(String recipeCashierName) {
		this.recipeCashierName = recipeCashierName;
	}
	@Column(name="APPLY_USER")
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	
	@Column(name="INVOICE_AMOUT")
	public double getInvoiceAmout() {
		return invoiceAmout;
	}
	public void setInvoiceAmout(double invoiceAmout) {
		this.invoiceAmout = invoiceAmout;
	}
	
	@Column(name="CHECK_USER")
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	
	@Column(name="CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	

	
	@Enumerated(EnumType.STRING)
	@Column(name = "INVOICE_ACTION_TYPE")
	public InvoiceActionType getM_InvoiceActionType() {
		return m_InvoiceActionType;
	}
	public void setM_InvoiceActionType(InvoiceActionType m_InvoiceActionType) {
		this.m_InvoiceActionType = m_InvoiceActionType;
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
	
	

}