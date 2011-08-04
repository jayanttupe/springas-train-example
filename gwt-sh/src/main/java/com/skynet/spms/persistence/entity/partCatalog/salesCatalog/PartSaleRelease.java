package com.skynet.spms.persistence.entity.partCatalog.salesCatalog;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.csdd.c.ChangeCode;
import com.skynet.spms.persistence.entity.csdd.e.ExchangeUnitAvailableIndicator;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.discountMatrix.DiscountItem;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 18-四月-2011 14:14:25
 */
/*************************************************************************
 *Update by : Huhf    2011-4-21
 *CHECKED BY:  
 *Confirm by: 
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_PART_SALE_RELEASE")
public class PartSaleRelease extends BaseEntity {
	/**
	 * 版次是在不修改发布版本的情况下，当发生销售信息的修改并发布时的版次修改。版次由0001开始，每次修改递增0001位。
	 */
	private String edition;//版次
	/**
	 * 由创建的版本号1.0开始，可以由业务人员进行手动的修改版本号，版本号缺省情况下执行递增，每次增幅为0.1。
	 */
	private String releaseVersion;//发布版本号
	/*
	 * 备注
	 */
	private String remark;
	/*
	 * 件号索引
	 */
	private PartIndex m_PartIndex;
	
	/*
	 * 销售折扣
	 */
	private List<DiscountItem> m_DiscountItem;
	
	/*
	 * 销售价格
	 */
	private SalesPrice m_SalesPrice;
	/*
	 * 是否可交换指示
	 */
	private ExchangeUnitAvailableIndicator m_ExchangeUnitAvailableIndicator;
	/*
	 * 更换代码
	 */
	private ChangeCode m_ChangeCode;
	/*
	 * 版次信息
	 */
	private EditionsInformation m_EditionsInformation;
	/*
	 * 业务发布状态实体
	 */
	private BussinessPublishStatusEntity m_BussinessPublishStatusEntity;
	
	
	
	@ManyToOne()
	@JoinColumn(name = "PART_INDEX_ID")
	public PartIndex getM_PartIndex() {
		return m_PartIndex;
	}
	public void setM_PartIndex(PartIndex m_PartIndex) {
		this.m_PartIndex = m_PartIndex;
	}
	@Reference(itemCls=DiscountItem.class)
	@OneToMany()
	@JoinColumn(name = "PART_SALE_RELEASE_ID")
	public List<DiscountItem> getM_DiscountItem() {
		return m_DiscountItem;
	}
	public void setM_DiscountItem(List<DiscountItem> m_DiscountItem) {
		this.m_DiscountItem = m_DiscountItem;
	}
	
	@Column(name= "EDITION")
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	@Column(name = "RELEASE_VERSION")
	public String getReleaseVersion() {
		return releaseVersion;
	}
	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SALES_PRICE_ID")
	public SalesPrice getM_SalesPrice() {
		return m_SalesPrice;
	}
	public void setM_SalesPrice(SalesPrice m_SalesPrice) {
		this.m_SalesPrice = m_SalesPrice;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EXCHANGE_UNIT_AI")
	public ExchangeUnitAvailableIndicator getM_ExchangeUnitAvailableIndicator() {
		return m_ExchangeUnitAvailableIndicator;
	}
	public void setM_ExchangeUnitAvailableIndicator(
			ExchangeUnitAvailableIndicator m_ExchangeUnitAvailableIndicator) {
		this.m_ExchangeUnitAvailableIndicator = m_ExchangeUnitAvailableIndicator;
	}
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_PUBLISH_SE_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}
	public void setM_BussinessPublishStatusEntity(
			BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
	}


}