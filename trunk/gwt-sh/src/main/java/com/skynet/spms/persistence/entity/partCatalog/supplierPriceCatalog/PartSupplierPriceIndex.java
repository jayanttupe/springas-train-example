package com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog;
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

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.csdd.c.ChangeCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.salesPrice.SupplierSalesPrice;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 18-四月-2011 14:14:25
 */

/*************************************************************************
 *Update by : Huhf    2011-4-27
 *CHECKED BY: Shanyf  2011-4-27
 *Confirm by: 
 *Update list:
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_SUPPLIER_PRICE_INDEX")
public class PartSupplierPriceIndex extends BaseEntity {
	/**
	 * 版次是在不修改发布版本的情况下，当发生销售信息的修改并发布时的版次修改。版次由0001开始，每次修改递增0001位。
	 */

	/**
	 * 由创建的版本号1.0开始，可以由业务人员进行手动的修改版本号，版本号缺省情况下执行递增，每次增幅为0.1。
	 */
	private String edition;//版次
	private String releaseVersion;//发布版本号
	private String remark;//备注
//	private ReplacingSourceOfSupplyCode m_ReplacingSourceOfSupplyCode;//可替换供应商源
//	private TraceabilityDataIndicator m_TraceabilityDataIndicator;//可追溯标志
//	private SupplierLeasePrice m_SupplierLeasePrice;//供应商租赁价格
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;//发布状态实体
	private ChangeCode m_ChangeCode;//更改代码
	private SupplierSalesPrice m_SupplierSalesPrice;//供应商销售价格
//	private List<OriginalEquipmentManufacturerData> m_OriginalEquipmentManufacturerData;//原始设备制造商数据
//	private List<Certification> m_Certification;//证书
	private SupplierCode m_SupplierCode;//供应商代码
	private PartIndex m_PartIndex;//件号索引
	private EditionsInformation m_EditionsInformation;//版次信息
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_CODE")
	public ChangeCode getM_ChangeCode() {
		return m_ChangeCode;
	}
	public void setM_ChangeCode(ChangeCode m_ChangeCode) {
		this.m_ChangeCode = m_ChangeCode;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EDITIONS_INFO_ID")
	public EditionsInformation getM_EditionsInformation() {
		return m_EditionsInformation;
	}
	public void setM_EditionsInformation(EditionsInformation m_EditionsInformation) {
		this.m_EditionsInformation = m_EditionsInformation;
	}
	@ManyToOne()
	@JoinColumn(name = "PART_INDEX_ID")
	public PartIndex getM_PartIndex() {
		return m_PartIndex;
	}
	public void setM_PartIndex(PartIndex m_PartIndex) {
		this.m_PartIndex = m_PartIndex;
	}
	@Column(name= "EDITION")
    public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	@Column(name= "RELEASE_VERSION")
	public String getReleaseVersion() {
		return releaseVersion;
	}
	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}
	@Column(name= "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/*@Reference(itemCls=Certification.class)
	@OneToMany()
	@JoinColumn(name = "SUPPLIER_PRICE_INDEX_ID")
	public List<Certification> getM_Certification() {
		return m_Certification;
	}
	public void setM_Certification(List<Certification> m_Certification) {
		this.m_Certification = m_Certification;
	}*/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_PUBLISH_SE_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}
	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}
	/*@Enumerated(EnumType.STRING)
	@Column(name = "CHANGE_CODE")
	public ChangeCode getM_ChangeCode() {
		return m_ChangeCode;
	}
	public void setM_ChangeCode(ChangeCode m_ChangeCode) {
		this.m_ChangeCode = m_ChangeCode;
	}*/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SUPPLIER_SALES_PRICE_ID")
	public SupplierSalesPrice getM_SupplierSalesPrice() {
		return m_SupplierSalesPrice;
	}
	public void setM_SupplierSalesPrice(SupplierSalesPrice m_SupplierSalesPrice) {
		this.m_SupplierSalesPrice = m_SupplierSalesPrice;
	}
	/*@Reference(itemCls=OriginalEquipmentManufacturerData.class)
	@OneToMany()
	@JoinColumn(name = "SUPPLIER_PRICE_INDEX_ID")
	public List<OriginalEquipmentManufacturerData> getM_OriginalEquipmentManufacturerData() {
		return m_OriginalEquipmentManufacturerData;
	}
	public void setM_OriginalEquipmentManufacturerData(
			List<OriginalEquipmentManufacturerData> m_OriginalEquipmentManufacturerData) {
		this.m_OriginalEquipmentManufacturerData = m_OriginalEquipmentManufacturerData;
	}*/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SUPPLIER_CODE_ID")
	public SupplierCode getM_SupplierCode() {
		return m_SupplierCode;
	}
	public void setM_SupplierCode(SupplierCode m_SupplierCode) {
		this.m_SupplierCode = m_SupplierCode;
	}

}