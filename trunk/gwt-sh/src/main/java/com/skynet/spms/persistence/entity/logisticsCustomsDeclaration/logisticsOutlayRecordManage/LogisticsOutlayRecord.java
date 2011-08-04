package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage;
/**
 * 费用记录管理和费用记录管理明细配置一对多关系，关联项-物流费用记录ID
 */
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.BaseOutlayRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.logisticsOutlayItem.LogisticsOutlayItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationGood;

/**
 * @author fanyx
 * @version 1.0
 * @created 22-四月-2011 12:33:18
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_LOGISTICS_OUTLAY_RECORD")
public class LogisticsOutlayRecord extends BaseEntity{
	/**
	 * 指令ID
	 */
	private String orderID;
	/**
	 * 物流任务编号
	 */
	private String logisticsTaskNumber;
	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 指令编号
	 */
	private String orderNumber;
	/**
	 * 总金额 
	 */
	private String amount;
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 费用记录明细
	 */
	private List<LogisticsOutlayItem> logisticsOutlayItem;
	
	@Column(name = "LOGISTICS_TASK_NUMBER") 
	public String getLogisticsTaskNumber() {
		return logisticsTaskNumber;
	}

	public void setLogisticsTaskNumber(String logisticsTaskNumber) {
		this.logisticsTaskNumber = logisticsTaskNumber;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

    @Column(name = "ORDER_NUMBER") 
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "AMOUNT") 
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(targetEntity= LogisticsOutlayItem.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="LOGISTICS_OUTLAY_ITEM_ID")
	public List<LogisticsOutlayItem> getLogisticsOutlayItem() {
		return logisticsOutlayItem;
	}

	public void setLogisticsOutlayItem(List<LogisticsOutlayItem> logisticsOutlayItem) {
		this.logisticsOutlayItem = logisticsOutlayItem;
	}
	
}
