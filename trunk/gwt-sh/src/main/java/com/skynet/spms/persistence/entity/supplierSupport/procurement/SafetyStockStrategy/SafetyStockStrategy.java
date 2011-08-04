package com.skynet.spms.persistence.entity.supplierSupport.procurement.SafetyStockStrategy;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * @author 曹宏炜
 * @version 1.0
 * @category 库存安全策略
 * @created 07-五月-2011 10:33:43
 */
@Entity
@Table(name = "SPMS_SAFETYSTOCKSTRATEGY")
public class SafetyStockStrategy extends BaseEntity {
	/** 策略编号 **/
	private String strategyNumber;
	/** 件号 **/
	private String partNumber;
	/** 备件描述 **/
	private String description;
	/** 库存安全策略描述 **/
	private String strategyDescription;
	/** 有效起始日期 **/
	private Date startDate;
	/** 失效结束日期 **/
	private Date endDate;
	/**
	 * 表示最低安全数量。
	 */
	private float saftyQuantity;
	/**
	 * 再订货点数量
	 */
	private float reorderPoint;
	/**
	 * 表示如果达到再订货点时进行订货的数量。
	 */
	private float reorderQuantity;
	private BussinessStatusEntity m_BussinessStatusEntity;
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	private ManufacturerCode m_ManufacturerCode;
	private List<PartDeliverQuantityAnalisysData> m_PartDeliverQuantityAnalisysData;

	private StockRoom stockRoom;

	@ManyToOne
	@JoinColumn(name = "STOCKROOM_ID")
	public StockRoom getStockRoom() {
		return stockRoom;
	}

	public void setStockRoom(StockRoom stockRoom) {
		this.stockRoom = stockRoom;
	}

	@Column(name = "STRATEGYNUMBER")
	public String getStrategyNumber() {
		return strategyNumber;
	}

	public void setStrategyNumber(String strategyNumber) {
		this.strategyNumber = strategyNumber;
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

	@Column(name = "STRATEGYDESCRIPTION")
	public String getStrategyDescription() {
		return strategyDescription;
	}

	public void setStrategyDescription(String strategyDescription) {
		this.strategyDescription = strategyDescription;
	}

	@Column(name = "STARTDATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "SAFTYQUANTITY")
	public float getSaftyQuantity() {
		return saftyQuantity;
	}

	public void setSaftyQuantity(float saftyQuantity) {
		this.saftyQuantity = saftyQuantity;
	}

	@Column(name = "REORDERPOINT")
	public float getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(float reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	@Column(name = "REORDERQUANTITY")
	public float getReorderQuantity() {
		return reorderQuantity;
	}

	public void setReorderQuantity(float reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}

	@OneToOne
	@JoinColumn(name = "M_BUSSINESSSTATUSENTITY_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}

	public void setM_BussinessStatusEntity(
			BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_UNITOFMEASURECODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	@ManyToOne
	@JoinColumn(name = "M_MANUFACTURERCODE_ID")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	@OneToMany(mappedBy = "safetyStockStrategy", fetch = FetchType.LAZY)
	public List<PartDeliverQuantityAnalisysData> getM_PartDeliverQuantityAnalisysData() {
		return m_PartDeliverQuantityAnalisysData;
	}

	public void setM_PartDeliverQuantityAnalisysData(
			List<PartDeliverQuantityAnalisysData> m_PartDeliverQuantityAnalisysData) {
		this.m_PartDeliverQuantityAnalisysData = m_PartDeliverQuantityAnalisysData;
	}

}