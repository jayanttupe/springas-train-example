package com.skynet.spms.persistence.entity.baseSupplyChain.baseRequisitionSheet.baseRequisitionSheetItem;
import java.util.List;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseRequisitionSheet.BaseRequisitionSheet;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;
import com.skynet.spms.persistence.entity.csdd.c.ConditionCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.m.ModelofApplicabilityCode;
import com.skynet.spms.persistence.entity.csdd.p.PackagingCode;
import com.skynet.spms.persistence.entity.csdd.s.ShelfLifeCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.csdd.w.WarrantyTimeCycleReferenceCode;

/**
 * @author Administrator
 * @version 1.0
 * @created 10-三月-2011 11:10:35
 */
public class BaseRequisitionSheetItem extends BaseEntity {

	private int itemNumber;
	private String partNumber;
	private float quantity;
	public List<ModelofApplicabilityCode> m_ModelofApplicabilityCode;
	public UnitOfMeasureCode m_UnitOfMeasureCode;
	public WarrantyTimeCycleReferenceCode m_WarrantyTimeCycleReferenceCode;
	public ManufacturerCode m_ManufacturerCode;
	public ConditionCode m_ConditionCode;
	public PackagingCode m_PackagingCode;
	public ShelfLifeCode m_ShelfLifeCode;
	public List<CertificateType> m_CertificateType;
	//public partNumber m_partNumber;
	public BaseRequisitionSheet m_BaseRequisitionSheet;
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
	public List<ModelofApplicabilityCode> getM_ModelofApplicabilityCode() {
		return m_ModelofApplicabilityCode;
	}
	public void setM_ModelofApplicabilityCode(
			List<ModelofApplicabilityCode> m_ModelofApplicabilityCode) {
		this.m_ModelofApplicabilityCode = m_ModelofApplicabilityCode;
	}
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}
	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}
	public WarrantyTimeCycleReferenceCode getM_WarrantyTimeCycleReferenceCode() {
		return m_WarrantyTimeCycleReferenceCode;
	}
	public void setM_WarrantyTimeCycleReferenceCode(
			WarrantyTimeCycleReferenceCode m_WarrantyTimeCycleReferenceCode) {
		this.m_WarrantyTimeCycleReferenceCode = m_WarrantyTimeCycleReferenceCode;
	}
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}
	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}
	public ConditionCode getM_ConditionCode() {
		return m_ConditionCode;
	}
	public void setM_ConditionCode(ConditionCode m_ConditionCode) {
		this.m_ConditionCode = m_ConditionCode;
	}
	public PackagingCode getM_PackagingCode() {
		return m_PackagingCode;
	}
	public void setM_PackagingCode(PackagingCode m_PackagingCode) {
		this.m_PackagingCode = m_PackagingCode;
	}
	public ShelfLifeCode getM_ShelfLifeCode() {
		return m_ShelfLifeCode;
	}
	public void setM_ShelfLifeCode(ShelfLifeCode m_ShelfLifeCode) {
		this.m_ShelfLifeCode = m_ShelfLifeCode;
	}
	public List<CertificateType> getM_CertificateType() {
		return m_CertificateType;
	}
	public void setM_CertificateType(List<CertificateType> m_CertificateType) {
		this.m_CertificateType = m_CertificateType;
	}
/*	public partNumber getM_partNumber() {
		return m_partNumber;
	}
	public void setM_partNumber(partNumber m_partNumber) {
		this.m_partNumber = m_partNumber;
	}*/
	public BaseRequisitionSheet getM_BaseRequisitionSheet() {
		return m_BaseRequisitionSheet;
	}
	public void setM_BaseRequisitionSheet(
			BaseRequisitionSheet m_BaseRequisitionSheet) {
		this.m_BaseRequisitionSheet = m_BaseRequisitionSheet;
	}
	
	

}