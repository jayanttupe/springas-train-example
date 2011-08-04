package com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.baseOutlayItem;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.AuditStatusEntity;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:34
 */
@MappedSuperclass
public class BaseOutlayItem extends BaseEntity {

	private String itemNumber;
	private String documentNumber;
	private String itemDescription;
	private String additionalDeclaration;
	private Float quantity;
	private Float unitOfPrice;
	private Float amount;
	private String remarkText;
	public InternationalCurrencyCode m_InternationalCurrencyCode;
	public AuditStatusEntity auditStatus;

	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "DOCUMENT_NUMBER")
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	@Column(name = "ITEM_DESCRIPTION")
	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Column(name = "ADDITIONAL_DECLARATION")
	public String getAdditionalDeclaration() {
		return additionalDeclaration;
	}

	public void setAdditionalDeclaration(String additionalDeclaration) {
		this.additionalDeclaration = additionalDeclaration;
	}

	@Column(name = "QUANTITY")
	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Column(name = "UNIT_OF_PRICE")
	public Float getUnitOfPrice() {
		return unitOfPrice;
	}

	public void setUnitOfPrice(Float unitOfPrice) {
		this.unitOfPrice = unitOfPrice;
	}

	@Column(name = "AMOUNT")
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
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

	@OneToOne()
	@JoinColumn(name = "AUDIT_STATUS_ID")
	public AuditStatusEntity getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatusEntity auditStatus) {
		this.auditStatus = auditStatus;
	}

}