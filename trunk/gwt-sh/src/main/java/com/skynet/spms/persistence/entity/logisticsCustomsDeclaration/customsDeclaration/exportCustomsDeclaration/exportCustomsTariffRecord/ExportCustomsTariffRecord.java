package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsTariffRecord.BaseCustomsTaiffRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * @author FANYX
 * @version 1.0 出口报关关税
 * @created 
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_EXPORT_TARI_RECORD")
public class ExportCustomsTariffRecord extends BaseCustomsTaiffRecord {
	
	/**
     * 基础提发货指令
     */
	private PickupDeliveryOrder pickupDeliveryOrder;
	/**
	 * 出口报关备件项
	 */
	private List<ExportCustomsDeclarationItems> exportCustomsDeclarationItems;
	
	@Transient
	public PickupDeliveryOrder getPickupDeliveryOrder() {
		return pickupDeliveryOrder;
	}
	public void setPickupDeliveryOrder(PickupDeliveryOrder pickupDeliveryOrder) {
		this.pickupDeliveryOrder = pickupDeliveryOrder;
	}
	
	@Transient
	public List<ExportCustomsDeclarationItems> getExportCustomsDeclarationItems() {
		return exportCustomsDeclarationItems;
	}
	public void setExportCustomsDeclarationItems(
			List<ExportCustomsDeclarationItems> exportCustomsDeclarationItems) {
		this.exportCustomsDeclarationItems = exportCustomsDeclarationItems;
	}
	
}