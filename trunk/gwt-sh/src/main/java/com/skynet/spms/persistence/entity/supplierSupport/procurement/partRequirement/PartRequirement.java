package com.skynet.spms.persistence.entity.supplierSupport.procurement.partRequirement;

import java.util.Date;
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

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.spmsdd.ProcurementRequiredType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.StockRoomAreaCode;

/**
 * 备件需求由业务部门人员根据各个业务的来源需求进行新建修改，采购计划人员收到备件需求并进行处理，根据需要制定采购计划。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:31
 */
@Entity
@Table(name = "SPMS_PARTREQUIREMENT")
public class PartRequirement extends BaseEntity {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/** 备件需求编号 **/
	private String partRequirementNumber;
	/** 件号 **/
	private String partNumber;
	/** 备件描述 **/
	private String description;
	/** 数量 **/
	private Float quantity;
	/** 预计交货日期 **/
	private Date expectingDeliveryDate;
	/**
	 * 来源业务编号
	 */
	private String bussinessNumber;
	
	private Priority m_Priority;
	// private List<messageEntity> m_messageEntity;
	/** 备注 **/
	private String remarkText;

	private CustomerIdentificationCode m_CustomerIdentificationCode;
	// private PartRequirementHandle m_PartRequirementHandle;
	private StockRoomAreaCode m_StockRoomAreaCode;
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	private ManufacturerCode m_ManufacturerCode;
	private BussinessStatusEntity m_BussinessStatusEntity;
	private List<Attachment> m_Attachment;
	private ProcurementRequiredType m_ProcurementRequiredType;
	/**最后更新时间**/
	private Date lastTime;
	
	@Column(name="LASTTIME")
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "REMARKTEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Column(name = "PARTREQUIREMENTNUMBER")
	public String getPartRequirementNumber() {
		return partRequirementNumber;
	}

	public void setPartRequirementNumber(String partRequirementNumber) {
		this.partRequirementNumber = partRequirementNumber;
	}

	@Column(name = "PARTNUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "QUANTITY")
	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	@Column(name = "EXPEINGIVEDATE")
	public Date getExpectingDeliveryDate() {
		return expectingDeliveryDate;
	}

	public void setExpectingDeliveryDate(Date expectingDeliveryDate) {
		this.expectingDeliveryDate = expectingDeliveryDate;
	}

	@Column(name = "BUSNESSNBER")
	public String getBussinessNumber() {
		return bussinessNumber;
	}

	public void setBussinessNumber(String bussinessNumber) {
		this.bussinessNumber = bussinessNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}

	@ManyToOne
	@JoinColumn(name = "M_CUODEIFOOE_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}

	// @OneToOne
	// @JoinColumn(name = "M_PARTREQUIREMENTHANDLE_ID")
	// public PartRequirementHandle getM_PartRequirementHandle() {
	// return m_PartRequirementHandle;
	// }
	//
	// public void setM_PartRequirementHandle(
	// PartRequirementHandle m_PartRequirementHandle) {
	// this.m_PartRequirementHandle = m_PartRequirementHandle;
	// }

	@Enumerated(EnumType.STRING)
	@Column(name = "M_STOCROOAACODE")
	public StockRoomAreaCode getM_StockRoomAreaCode() {
		return m_StockRoomAreaCode;
	}

	public void setM_StockRoomAreaCode(StockRoomAreaCode m_StockRoomAreaCode) {
		this.m_StockRoomAreaCode = m_StockRoomAreaCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_UNIFMASRECDE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	@ManyToOne
	@JoinColumn(name = "M_MANUATERCOE_ID")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "M_BUSSIESSTUTITY_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}

	public void setM_BussinessStatusEntity(
			BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}

	@OneToMany
	@JoinColumn(name = "M_ATTACHMENT_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}

	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PROUENTERETYPE")
	public ProcurementRequiredType getM_ProcurementRequiredType() {
		return m_ProcurementRequiredType;
	}

	public void setM_ProcurementRequiredType(
			ProcurementRequiredType m_ProcurementRequiredType) {
		this.m_ProcurementRequiredType = m_ProcurementRequiredType;
	}

}