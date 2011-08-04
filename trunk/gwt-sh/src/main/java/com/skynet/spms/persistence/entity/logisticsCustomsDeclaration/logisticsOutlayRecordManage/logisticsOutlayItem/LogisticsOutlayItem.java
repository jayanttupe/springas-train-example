/**
 * 
 */
package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.logisticsOutlayItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.code.CarrierName;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.baseOutlayItem.BaseOutlayItem;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.spmsdd.ClearanceAgent;

/**
 * @author fanyx
 * @version 1.0
 * @created 22-四月-2011 12:33:18
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_LOGISTICS_OUTLAY_ITEM")
public class LogisticsOutlayItem extends BaseEntity {
	/**
	 * 项号
	 */
	private String itemNumber;
	/**
	 * 单据编号
	 */
	private String documentNumber;
	/**
	 * 项目描述
	 */
	private String itemDescription;
	/**
	 * 摘要
	 */
	private String additionalDeclaration;
	/**
	 * 数量
	 */
	private Float quantity;
	/**
	 * 单价
	 */
	private Float unitOfPrice;
	/**
	 * 金额
	 */
	private Float amount;
	/**
	 * 国际货币代码
	 */
	private String internationalCurrencyCode;
	/**
	 * 物流费用记录ID
	 */
	private String logisticsOutlayItemID;
	
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
	@Column(name = "ADDITION_DECLA") 
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
	@Column(name = "INTER_CURRENCY_CODE") 
	public String getInternationalCurrencyCode() {
		return internationalCurrencyCode;
	}
	public void setInternationalCurrencyCode(String internationalCurrencyCode) {
		this.internationalCurrencyCode = internationalCurrencyCode;
	}
	@Column(name = "LOGISTICS_OUTLAY_ITEM_ID") 
	public String getLogisticsOutlayItemID() {
		return logisticsOutlayItemID;
	}
	public void setLogisticsOutlayItemID(String logisticsOutlayItemID) {
		this.logisticsOutlayItemID = logisticsOutlayItemID;
	}
	@Column(name = "ITEM_NUMBER") 
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

}
