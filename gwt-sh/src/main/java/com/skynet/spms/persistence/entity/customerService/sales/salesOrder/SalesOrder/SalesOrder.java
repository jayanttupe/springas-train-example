package com.skynet.spms.persistence.entity.customerService.sales.salesOrder.SalesOrder;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.customerService.sales.salesOrder.SalesOrderItem.SalesOrderItem;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:45
 */
@Entity
@Table(name = "SPMS_SALESORDER")
public class SalesOrder extends BaseEntity {

	/**销售订单编号**/
	private String salesOrderNumber;
	
	/**适用机型**/
	private String m_AircraftModelIdentifier;
	//public SalesContractTemplate m_SalesContractTemplate;
	/**优先级**/
	private Priority m_Priority;
	/**飞机尾号**/
	private String m_AircraftIdentificationNumber;
	/**订单明细**/
	private List<SalesOrderItem> m_SalesOrderItem;
	/**备注**/
	private String remark;
	///public RemarkTextEntity m_RemarkTextEntity;
	
	
	@Column(name = "SALES_ORDER_NUMBER")
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}
	
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}
	public void setM_Priority(Priority priority) {
		m_Priority = priority;
	}
	
	
	@Column(name = "AIRCRAFTIDENTIFICATIONNUMBER")
	public String getM_AircraftIdentificationNumber() {
		return m_AircraftIdentificationNumber;
	}
	public void setM_AircraftIdentificationNumber(
			String aircraftIdentificationNumber) {
		m_AircraftIdentificationNumber = aircraftIdentificationNumber;
	}
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOITEM_ID")
	public List<SalesOrderItem> getM_SalesOrderItem() {
		return m_SalesOrderItem;
	}
	public void setM_SalesOrderItem(List<SalesOrderItem> salesOrderItem) {
		m_SalesOrderItem = salesOrderItem;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "AIRCRAFT_MODEL_IDENTIFIER")
	public String getM_AircraftModelIdentifier() {
		return m_AircraftModelIdentifier;
	}
	public void setM_AircraftModelIdentifier(String aircraftModelIdentifier) {
		m_AircraftModelIdentifier = aircraftModelIdentifier;
	}

}