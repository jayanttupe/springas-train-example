package com.skynet.spms.client.vo.customerService.salesInquirySheet;

import java.util.Date;
import com.google.gwt.user.client.rpc.IsSerializable;

public class SalesInquirySheetItemVO implements IsSerializable {

	private String id;

	/** 项号* */
	private int itemNumber;

	/** 询价件号* */
	private String partNumber;

	/** 数量* */
	private float quantity;

	/** 备件交货日期* */
	private Date partDeliveryDate;

	/** 备注* */
	private String remark;

	/** 制造商代码* */
	private String m_ManufacturerCode;

	/** 工作任务的优先级* */
	private String m_Priority;

	/** 证书类型* */
	private String m_CertificateType;

	/** 机型适用代码* */
	private String m_ModelofApplicabilityCode;

	/** 国际货币代码* */
	private String m_InternationalCurrencyCode;

	/** 备件状态代码* */
	private String m_ConditionCode;

	/** 单位代码* */
	private String m_UnitOfMeasureCode;

	/** 件号索引 **/
	private String m_partIndex;

	public SalesInquirySheetItemVO() {
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public Date getPartDeliveryDate() {
		return partDeliveryDate;
	}

	public void setPartDeliveryDate(Date partDeliveryDate) {
		this.partDeliveryDate = partDeliveryDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(String m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	public String getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(String m_Priority) {
		this.m_Priority = m_Priority;
	}

	public String getM_CertificateType() {
		return m_CertificateType;
	}

	public void setM_CertificateType(String m_CertificateType) {
		this.m_CertificateType = m_CertificateType;
	}

	public String getM_ModelofApplicabilityCode() {
		return m_ModelofApplicabilityCode;
	}

	public void setM_ModelofApplicabilityCode(String m_ModelofApplicabilityCode) {
		this.m_ModelofApplicabilityCode = m_ModelofApplicabilityCode;
	}

	public String getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			String m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

	public String getM_ConditionCode() {
		return m_ConditionCode;
	}

	public void setM_ConditionCode(String m_ConditionCode) {
		this.m_ConditionCode = m_ConditionCode;
	}

	public String getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(String m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	public String getM_partIndex() {
		return m_partIndex;
	}

	public void setM_partIndex(String m_partIndex) {
		this.m_partIndex = m_partIndex;
	}

}
