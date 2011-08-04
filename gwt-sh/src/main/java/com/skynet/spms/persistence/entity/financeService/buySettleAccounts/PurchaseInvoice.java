package    com.skynet.spms.persistence.entity.financeService.buySettleAccounts;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.InvoiceApplyTable;
import com.skynet.spms.persistence.entity.spmsdd.InvoiceType;
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
@Table(name="SPMS_PURCHASE_INVOICE")
public class PurchaseInvoice extends BaseEntity {

	private String amountType;
	private Double amountWithoutTaxs;
	private String contractNumber;
	private String invoiceCompany;
	private Date invoiceDate;
	private String invoiceNumber;
	private String invoiceType;
	private Byte isChecked;
	private String payCondition;
	private String payMode;
	private Date payTimeLimit;
	private Double taxRate;
	private Double tax;
	private Double taxsAmount;
	private Double totalAmount;
	private Double amount;
	public PayActionType m_InvoiceBusinessType;
	public InvoiceType m_InvoiceType;
	private String invoiceUser;
	public InternationalCurrencyCode m_InternationalCurrencyCode;
	private InvoiceApplyTable invoiceApplyTable;
	private String invoiceApplyId;
	//public InStockRecord m_InStockRecord;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="INVOICEAPPLY_ID")
	public InvoiceApplyTable getInvoiceApplyTable() {
		return invoiceApplyTable;
	}
	
	
	@Transient
	public String getInvoiceApplyId() {
		return invoiceApplyId;
	}



	public void setInvoiceApplyId(String invoiceApplyId) {
		this.invoiceApplyId = invoiceApplyId;
	}



	public void setInvoiceApplyTable(InvoiceApplyTable invoiceApplyTable) {
		this.invoiceApplyTable = invoiceApplyTable;
	}



	@Column(name="AMOUNT_TYPE")
	public String getAmountType() {
		return amountType;
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
	@Column(name="INVOICE_USER")
	public String getInvoiceUser() {
		return invoiceUser;
	}

	public void setInvoiceUser(String invoiceUser) {
		this.invoiceUser = invoiceUser;
	}
	@Column(name="tax")
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
	@Column(name="AMOUNT_WITHOUT_TAXES")
	public Double getAmountWithoutTaxs() {
		return amountWithoutTaxs;
	}
	public void setAmountWithoutTaxs(Double amountWithoutTaxs) {
		this.amountWithoutTaxs = amountWithoutTaxs;
	}
	@Column(name="CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@Column(name="INVOICE_COMPANY")
	public String getInvoiceCompany() {
		return invoiceCompany;
	}
	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}
	@Column(name="INVOICE_DATE")
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	@Transient
	public String getInvoiceType() {
		return invoiceType;
	}
	@Column(name="INVOICE_NUMBER")
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	@Column(name="ISCHECKED")
	public Byte getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Byte isChecked) {
		this.isChecked = isChecked;
	}
	@Column(name="PAY_CONDITION")
	public String getPayCondition() {
		return payCondition;
	}
	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}
	@Column(name="PAY_MODE")
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	@Column(name="PAY_TIME_LIMIT")
	public Date getPayTimeLimit() {
		return payTimeLimit;
	}
	public void setPayTimeLimit(Date payTimeLimit) {
		this.payTimeLimit = payTimeLimit;
	}
	@Column(name="TAX_RATE")
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	@Column(name="TAXS_AMOUNT")
	public Double getTaxsAmount() {
		return taxsAmount;
	}
	public void setTaxsAmount(Double taxsAmount) {
		this.taxsAmount = taxsAmount;
	}
	@Column(name="TOTAL_AMOUNT")
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="AMOUNT")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="INVOICE_BUSINESS_TYPE")
	public PayActionType getM_InvoiceBusinessType() {
		return m_InvoiceBusinessType;
	}
	public void setM_InvoiceBusinessType(PayActionType m_InvoiceBusinessType) {
		this.m_InvoiceBusinessType = m_InvoiceBusinessType;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="INVOICE_TYPE")
	public InvoiceType getM_InvoiceType() {
		return m_InvoiceType;
	}
	public void setM_InvoiceType(InvoiceType m_InvoiceType) {
		this.m_InvoiceType = m_InvoiceType;
	}
	
	

}